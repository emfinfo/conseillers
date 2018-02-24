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
  public final static String SESSION_DB_ID = "db-id";
  public final static String SESSION_LANG = "fr";
  public final static String SESSION_TIMESTAMP = "timestamp";


  /**
   * Méthode de création d'une session par authentification de l'utilisateur.
   *
   * @param httpLogin un objet login lu depuis la requête HTTP
   * @param dbLogin un objet de login lu depuis la base de donnée
   *
   * @return true si l'utilisateur est reconnu
   */
  @SuppressWarnings("null")
  public static boolean create(Login httpLogin, Login dbLogin) {
    boolean ok = false;

    // le cookie de session doit avoir été effacé avant
    if (session().get(SESSION_USER_ID) == null) {

      // teste si l'utilisateur a été trouvé auparavant
      if (dbLogin != null) {

        // si password dans la table ...
        if (dbLogin.getMotDePasse() != null) {
          String dbHash = dbLogin.getMotDePasse().substring(0, 64);
          String dbSalt = dbLogin.getMotDePasse().substring(64);
//          System.out.println("dbHash:  " + dbHash + ", dbSalt:  " + dbSalt);

          // calcul de l'empreinte du mot de passe fourni avec le sel de la DB
          String newHash = Generate.hash(httpLogin.getMotDePasse() + dbSalt, "SHA-256");

          // teste si l'empreinte est correcte
          ok = newHash.equals(dbHash);
//          System.out.println("newHash: " + newHash + ", password ok: " + ok);

          // teste si le timestamp de la requête HTTP est supérieur à celui de la BD
          ok = ok && (httpLogin.getTimestamp().getTime() > dbLogin.getTimestamp().getTime());

          // si oui, on mémorise le timestamp de la requête HTTP dans l'objet de la BD
          if (ok) {
            dbLogin.setTimestamp(new Date(httpLogin.getTimestamp().getTime()+5000));
          }
        } else { // autrement on pourrait tester avec AD
           // ok = authenticateOnActiveDirectory(
           //  httpLogin.getNom(), httpLogin.getDomaine(), htppLogin.getMotDePasse());
          ok = false;
        }
      }

      // enregistrement de la session si identification correcte
      if (ok) {
        long start = System.currentTimeMillis();
        session(SESSION_USER_ID, "" + dbLogin.getPkLogin());
        session(SESSION_DB_ID, "1");
        session(SESSION_TIMESTAMP, "" + start);
      } else {
        session().clear();
      }
    }
    return ok;
  }

  /**
   * Efface le contenu de la session en cours.
   *
   * @return true si la session est maintenant vide
   */
  public static boolean clear() {
    boolean ok = session().get(SESSION_USER_ID) != null;
    if (ok) {
      session().clear();
    }
    return !isOpen();
  }

  /**
   * Teste si la session est ouverte.
   *
   * @return true si une session est ouverte
   */
  public static boolean isOpen() {
    boolean ok = session().get(SESSION_USER_ID) != null;
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
    System.out.println("cTime: " + cTime + " sTime: " + sTime + " diff: " + (cTime-sTime));
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
