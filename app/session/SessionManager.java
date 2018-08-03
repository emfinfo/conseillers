package session;

import ch.emf.helpers.Generate;
import ch.jcsinfo.util.ConvertLib;
import java.util.Date;
import models.Login;
import static play.mvc.Controller.session;

/**
 * Permet de gérer une session utilisateur. On utilise principalement 2 informations de session
 * (user-id et db-id), ce qui permet une approche "multi-tenant" d'une base de données.
 *
 * @author jcstritt
 */
public class SessionManager {
  public final static String SESSION_USER_ID = "user-id";
  public final static String SESSION_USER_NAME = "user-name";
  public final static String SESSION_DB_ID = "db-id";
  public final static String SESSION_LANG = "fr";
  public final static String SESSION_TIMESTAMP = "timestamp";


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

    // on vérifie si le timestamp est plus haut (pour éviter qu'on réutilise les mêmes infos)
    long diff = clientLogin.getTimestamp().getTime() - dbLogin.getTimestamp().getTime();
    ok = ok && (diff > 0 || Math.abs(diff) > 3600000);

    // on met à jour le timestamp
    if (ok) {
//          dbLogin.setTimestamp(clientLogin.getTimestamp());
//      dbLogin.setTimestamp(new Date(clientLogin.getTimestamp().getTime() + 5000));
      dbLogin.setTimestamp(new Date(clientLogin.getTimestamp().getTime()));
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
        long start = System.currentTimeMillis();
        session(SESSION_USER_ID, "" + dbLogin.getPkLogin());
        session(SESSION_DB_ID, "1");
        session(SESSION_TIMESTAMP, "" + start);
        session(SESSION_USER_NAME, dbLogin.getNom());
//        String uuid = session().get("uuid");
//        if (uuid == null) {
//          uuid = java.util.UUID.randomUUID().toString();
//          session("uuid", uuid);
//        }
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
    boolean ok = session().get(SESSION_USER_ID) != null;
    if (ok) {
      session().clear();
    }
  }

  /**
   * Teste si la session est ouverte.
   *
   * @return true si une session est ouverte
   */
  public static boolean isOpen() {
    System.out.println("isOpen session: "+session().toString());
    String userId = session().get(SESSION_USER_ID);
    boolean ok = userId != null;
    if (ok) {
      session(SESSION_TIMESTAMP, "" + System.currentTimeMillis());
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
   * Récupérer le "user-id" de l'utilisateur logué.
   *
   * @return l'identifiant de la personne loguée ou 0 si non trouvé
   */
  public static int getUserId() {
    String userId = session().get(SESSION_USER_ID);
    return ConvertLib.stringToInt(userId);
  }

  /**
   * Récupérer le "user.name" de l'utilisateur logué.
   *
   * @return le nom de l'utilisateur logué
   */
  public static String getUserName() {
    String name = session().get(SESSION_USER_NAME);
    if (name == null || name.isEmpty()) {
      name = "?name?";
    }
    return name;
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
   * Dans une approche "multi-tenants" de la BD, récupérer l'identifiant mémorisé de cette BD.
   *
   * @return un identifiant de BD
   */
  public static int getDbId() {
    String dbId = session().get(SESSION_DB_ID);
    return ConvertLib.stringToInt(dbId);
  }

  /**
   * Dans une approche "multi-tenants" de la BD, mémoriser un identifiant de cette BD.
   *
   * @param dbId un identifiant de BD
   */
  public static void setDbId(int dbId) {
    session(SESSION_DB_ID, "" + dbId);
  }

}
