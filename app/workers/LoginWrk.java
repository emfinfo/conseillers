package workers;

import ch.emf.cypher.AesUtil;
import ch.emf.dao.JpaDaoAPI;
import ch.emf.dao.filtering.Search;
import ch.emf.dao.play.DaoRepositoryItf;
import ch.emf.helpers.Convert;
import ch.emf.helpers.Generate;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.sql.Timestamp;
import java.util.Date;
import models.Login;

/**
 * Couche "métier" pour gérer les opérations de login avec la base de données.<br>
 * Utilise daolayer avec JPA comme sous-couche d'accès aux données.
 *
 * @author Jean-Claude Stritt
 */
@Singleton
public class LoginWrk {
  private final JpaDaoAPI dao;
  private final String SEPARATOR = "♂♥♀";

  @Inject
  public LoginWrk(DaoRepositoryItf rep) {
    this.dao = rep.getDao();
  }

  public LoginWrk(JpaDaoAPI dao) {
    this.dao = dao;
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

  /**
   * Extraire un objet de login d'après les données reçues
   * encryptées depuis une application cliente.
   *
   * @param data les données encryptées reçues depuis le client
   * @return un objet Login
   */
  public Login extraire(String data) {
    Login login = new Login();
//    System.out.println("data: " + data + ", len=" + data.length());

    // on ne traite que si les données contiennent plus de 64 caractères
    if (data.length() > 64) {

      // récupération du sel et du vecteur d'initialisation
      String salt = data.substring(0, 32);
      String iv = data.substring(32, 64);
//      System.out.println("salt: " + salt + ", len=" + salt.length());
//      System.out.println("iv:   " + iv + ", len=" + iv.length());

      // génère la même clé que le client doit générer
      String passPhrase = Generate.passPhrase();
//      System.out.println("passPhrase: "+passPhrase+" ("+new String(Convert.toBase64(passPhrase))+")");

      // crée l'utilitaire pour le décryptage
      AesUtil aesUtil = new AesUtil(128, 1000);

      // convertit les données hexadécimales en Base64 (nécessaire pour le décryptage)
      byte[] bytes = Convert.toHex(data.substring(64));
      String b64 = Convert.toBase64(bytes);

      // décrypte les données
      String decrypted = aesUtil.decrypt(salt, iv, passPhrase, b64);

      // séparateur "/" pour les demandes de l'API
      String sep = decrypted.contains(SEPARATOR) ? SEPARATOR : "/";

      // extraction des données du login
      String t[] = decrypted.split(sep);
      if (t.length >= 4) {
        login.setNom(t[0]);
        login.setDomaine(t[1]);
        login.setMotDePasse(t[2]);
        login.setTimestamp(new Date(Long.parseLong(t[3])));
        login.setProfil((t.length >= 5) ? t[4] : null);
        login.setEmail((t.length >= 6) ? t[5] : null);
        login.setInitiales((t.length >= 7) ? t[6] : null);
        login.setLangue((t.length >= 8) ? t[7] : null);
      } else {

      }
    }
    return login;
  }

  /**
   * Comparer les empreintes du mot de passe entre les données
   * reçues d'une application cliente et les données dans la BD.
   *
   * @param clientLogin un objet Login provenant de l'application cliente
   * @param dbLogin un objet Login provenant de la base de données
   */
  public boolean comparer(Login clientLogin, Login dbLogin) {
    boolean ok = false;

    // empreinte et sel sont récupérés depuis les infos dans la BD
    String pwd = dbLogin.getMotDePasse();
    String dbHash = pwd.substring(0, 64);
    String dbSalt = pwd.substring(64);

    // on génère une nouvelle empreinte
    String newHash = Generate.hash(clientLogin.getMotDePasse() + dbSalt, "SHA-256");

    // on vérifie si les deux empreintes correspondent
    ok = newHash.equals(dbHash);

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

  /**
   * Crée un nouveau compte utilisateur dans la base de données
   * avec les données fournies depuis l'application cliente.
   *
   * @param clientLogin un objet Login provenant de l'application cliente
   * @return un objet Login rempli ou vide
   */
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

}
