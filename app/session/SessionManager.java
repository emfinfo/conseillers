package session;

import ch.emf.helpers.Generate;
import ch.jcsinfo.util.ConvertLib;
import java.sql.Timestamp;
import models.Login;
import play.Logger;
import static play.mvc.Controller.session;

/**
 * Permet de gérer une session utilisateur. On utilise principalement 2 informations de session
 * (user-id et db-id), ce qui permet une approche "multi-tenantd" d'une base de données.
 *
 * @author jcstritt
 */
public class SessionManager {
  public final static String SESSION_USER_ID = "user-id";
  public final static String SESSION_USER_NAME = "user-name";
  public final static String SESSION_USER_PROFILE = "user-profile";
  public final static String SESSION_TIMESTAMP = "timestamp";
  public final static String SESSION_LANG = "fr";
  public final static String SESSION_DB_ID = "db-id";

  /**
   * Méthode privée pour comparer les empreintes du mot de passe
   */
  private static boolean compareHash(Login clientLogin, Login dbLogin) {
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

    // on met à jour le timestamp
    if (ok) {
      dbLogin.setTimestamp(clientLogin.getTimestamp());
    }
    return ok;
  }


  /**
   * Méthode de création d'une session par authentification de l'utilisateur.
   *
   * @param httpLogin un objet login lu depuis la requête HTTP
   * @param dbLogin un objet de login lu depuis la base de donnée
   *
   * @return true si l'utilisateur est reconnu
   */
  @SuppressWarnings("null")
  public static boolean create(Login clientLogin, Login dbLogin) {
    boolean ok = false;

    // teste si l'utilisateur a été trouvé auparavant
    if (dbLogin != null) {

      // si password dans la table ...
      if (dbLogin.getMotDePasse() != null) {
        ok = compareHash(clientLogin, dbLogin);
      } else { // autrement on pourrait tester avec AD
        // ok = authenticateOnActiveDirectory(
        //  httpLogin.getNom(), httpLogin.getDomaine(), httpLogin.getMotDePasse());
        ok = false;
      }

      // enregistrement de la session si identification correcte
      if (ok) {
        session(SESSION_USER_ID, "" + dbLogin.getPk());
        session(SESSION_USER_NAME, dbLogin.getNom());
        session(SESSION_USER_PROFILE, dbLogin.getProfil());
        session(SESSION_TIMESTAMP, "" + System.currentTimeMillis());
      } else {
        session().clear();
      }
    }
    return ok;
  }

  /**
   * Efface le contenu de la session en cours.
   */
  public static void clear() {
    String name = getUserName();
    session().clear();
    if (!name.equals("?name?")) {
      Logger.info("SESSION CLEAR (" + name + ")");
    }
  }

  /**
   * Teste si la session est ouverte.
   *
   * @return true si une session est ouverte
   */
  public static boolean isOpen() {
    return session().get(SESSION_USER_ID) != null;
  }

  /**
   * Teste si la session est ouverte et en même temps l'efface
   * si le timeout de session est dépassé.
   *
   * @return true si une session est ouverte
   */
  public static boolean isOpen(int msTimeout) {
    boolean ok = isOpen();
    if (ok && isTimeout(msTimeout)) {
      clear();
      ok = false;
    }
    return ok;
  }

  /**
   * Teste si un timeout de session doit intervenir.
   *
   * @param ms le temps en [ms] pour qu'un timeout intervienne
   * @return true si la session peut être fermée
   */
  public static boolean isTimeout(int ms) {
    long cTime = System.currentTimeMillis();
    long sTime = ConvertLib.stringToLong(session().get(SESSION_TIMESTAMP));
//    System.out.println("cTime: " + cTime + " sTime: " + sTime + " diff: " + (cTime-sTime));
    return (cTime-sTime) >= ms;
  }

  /**
   * Reset la propriété "timestamp" dans le cookie avec le temps actuel.
   */
  public static void resetTimeout() {
    boolean ok = session().get(SESSION_USER_ID) != null;
    if (ok) {
      session(SESSION_TIMESTAMP, "" + System.currentTimeMillis());
    }
  }

  /**
   * Récupérer l'indetifiant (pk) de l'utilisateur logué.
   *
   * @return l'identifiant de la personne loguée ou 0 si non trouvé
   */
  public static int getUserId() {
    String userId = session().get(SESSION_USER_ID);
    return ConvertLib.stringToInt(userId);
  }

  /**
   * Récupérer le nom d'utilisateur de l'utilisateur logué.
   *
   * @return le nom en question
   */
  public static String getUserName() {
    String name = session().get(SESSION_USER_NAME);
    if (name == null || name.isEmpty()) {
      name = "?name?";
    }
    return name;
  }

  /**
   * Récupérer le profil de l'utilisateur logué.
   *
   * @return le profil en question
   */
  public static String getUserProfile() {
    String profile = session().get(SESSION_USER_PROFILE);
    if (profile == null || profile.isEmpty()) {
      profile = "?profil?";
    }
    return profile;
  }

  /**
   * Récupérer la langue de l'utilisateur logué.
   *
   * @return une langue sur 2 caractères (ex: "fr" pour le français)
   */
  public static String getLang() {
    String lang = session().get(SESSION_LANG);
    if (lang == null || lang.isEmpty()) {
      lang = "fr";
    }
    return lang;
  }

  /**
   * Récupérer l'identifiant de la compta en cours.
   *
   * @return l'identifiant de la compta en cours ou 0 si non trouvé
   */
  public static int getDbId() {
    String tenantId = session().get(SESSION_DB_ID);
    return ConvertLib.stringToInt(tenantId);
  }

  /**
   * Mémoriser l'identifiant de la compta en cours.
   *
   * @param comptaId un identifiant numérique
   */
  public static void setDbId(int comptaId) {
    session(SESSION_DB_ID, "" + comptaId);
  }

}
