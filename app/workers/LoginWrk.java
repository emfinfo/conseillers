package workers;

import ch.emf.dao.JpaDaoAPI;
import ch.emf.dao.filtering.Search;
import ch.jcsinfo.cypher.helpers.Generate;
import ch.jcsinfo.cypher.AesUtil;
import java.sql.Timestamp;
import java.util.Date;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Login;

/**
 * Couche "métier" pour gérer les opérations de login avec la base de données.<br>
 * Utilise daolayer avec JPA comme sous-couche d'accès aux données.
 *
 * @author Jean-Claude Stritt
 */
@Singleton
public class LoginWrk extends Worker {

  @Inject
  public LoginWrk(JpaDaoAPI dao) {
    super(dao);
  }

  public Login rechercher(String nom, String domaine) {
    Search s = new Search(Login.class);
    s.addFilterEqual("nom", nom);
    s.addFilterAnd();
    s.addFilterEqual("domaine", domaine);
    Login login = dao.getSingleResult(s);
    if (login != null) {
      dao.detach(login);
    }
    return login;
  }

  public Login creer(Login login) {
    if (dao.create(login) == 1) {
      dao.detach(login);
    }
    return login;
  }

  public Login lire(int id) {
    Login login = dao.read(Login.class, id, true, true);
    dao.detach(login);
    return login;
  }

  public int modifier(Login login) {
    return dao.update(login);
  }

  public int supprimer(Login login) {
    return dao.delete(Login.class, login.getPk());
  }

  public Login creerCompte(Login clientLogin) {

    // on crée une empreinte du mot de passe
    String dbSalt = Generate.randomHex(32);
    String dbHash = Generate.hash(clientLogin.getMotDePasse() + dbSalt, "SHA-256");
    clientLogin.setMotDePasse(dbHash + dbSalt);

    // si le compte n'existe pas, on le sauve dans la BD
    Login dbLogin = rechercher(clientLogin.getNom(), clientLogin.getDomaine());
    if (dbLogin == null) {
      dbLogin = creer(clientLogin);
      if (dbLogin == null || dbLogin.getPk() <= 0) {
        dbLogin = new Login();
      }
    } else {
      dbLogin = new Login();
    }
    return dbLogin;
  }

  /**
   * Extraire un objet de login d'après les données reçues
   * encryptées depuis une application cliente.
   *
   * @param data les données encryptées reçues depuis le client
   * @return un objet Login
   */
  public Login extraire(String data) {
    Login login = new Login();
    String[] t = AesUtil.extractParameters(data);
    if (t.length >= 4) {
      login.setNom(t[0]);
      login.setDomaine(t[1]);
      login.setMotDePasse(t[2]);
      login.setTimestamp(new Date(Long.parseLong(t[3])));
      login.setProfil((t.length >= 5) ? t[4] : null);
      login.setEmail((t.length >= 6) ? t[5] : null);
      login.setInitiales((t.length >= 7) ? t[6] : null);
      login.setLangue((t.length >= 8) ? t[7] : null);
    }
    return login;
  }

  /**
   * Comparer les empreintes du mot de passe entre les données
   * reçues d'une application cliente et les données dans la BD.
   *
   * @param clientLogin un objet Login provenant de l'application cliente
   * @param dbLogin     un objet Login provenant de la base de données
   */
  public boolean comparer(Login clientLogin, Login dbLogin) {

    // empreinte et sel sont récupérés depuis les infos dans la BD
    String pwd = dbLogin.getMotDePasse();
    String dbHash = pwd.substring(0, 64);
    String dbSalt = pwd.substring(64);

    // on génère une nouvelle empreinte
    String newHash = Generate.hash(clientLogin.getMotDePasse() + dbSalt, "SHA-256");

    // on vérifie si les deux empreintes correspondent
    boolean ok = newHash.equals(dbHash);

    // si le timestamp est null dans la BD, on le corrige
    if (dbLogin.getTimestamp() == null) {
      dbLogin.setTimestamp(new Timestamp(clientLogin.getTimestamp().getTime() - 1));
    }

    // on vérifie si le timestamp dans la requête est récent (pour éviter qu'on réutilise les mêmes infos)
    long diff = clientLogin.getTimestamp().getTime() - dbLogin.getTimestamp().getTime();
    ok = ok && (diff > 0);

    // on met à jour le timestamp dans l'objet dbLogin
    if (ok) {
      dbLogin.setTimestamp(clientLogin.getTimestamp());
    }

    return ok;
  }

}
