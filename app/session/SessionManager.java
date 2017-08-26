package session;

import ch.jcsinfo.util.ConvertLib;
import com.typesafe.config.Config;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
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
   * Contrôle si une authentification est possible sur ActiveDirectory.
   *
   * @param userName un nom d'utilisateur unique
   * @param psw un mot de passe
   * @param domain un nom de domaine "Active Directory"
   *
   * @return TRUE si l'accès à ActiveDirectory a été un succès
   */
  @SuppressWarnings("UseOfObsoleteCollectionType")
  @Inject
  private static boolean authenticateOnActiveDirectory(Config config, String userName, String psw, String domain) {
    final String AD_PROTOCOLE = "ldap://";
    final int AD_PORT = 389;
//    String contextFactory = play.Play.application().configuration().getString("ad.context_factory");
//    String adServer = AD_PROTOCOLE
//            + play.Play.application().configuration().getString("ad.server")
//            + ":" + AD_PORT;
    String contextFactory = config.getString("ad.context_factory");
    String adServer = AD_PROTOCOLE + config.getString("ad.server") + ":" + AD_PORT;

    Map<String, String> env = new HashMap<>();
    env.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory);
    env.put(Context.PROVIDER_URL, adServer);
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, domain + "\\" + userName);
    env.put(Context.SECURITY_CREDENTIALS, psw);

    boolean ok = false;
    try {
      DirContext ctx = new InitialDirContext((Hashtable<?, ?>)env);
      ok = true;
    } catch (NamingException ex) {
      // Logger.error("authenticateOnActiveDirectory error : " + ex.getMessage());
    }
    return ok;
  }

  /**
   * Méthode de création d'une session par authentification de l'utilisateur.
   *
   * @param userName un nom d'utilisateur unique
   * @param pwd un mot de passe
   * @param domain un nom de domaine "Active Directory"
   * @param login un objet de login lu dans la base de donnée
   * @return true si l'utilisateur est reconnu
   */
  @SuppressWarnings("null")
  public static boolean create(String userName, String pwd, String domain, Login login) {
    boolean ok = false;
    if (session().get(SESSION_USER_ID) == null) {

      // teste si l'utilisateur a été trouvé auparavant
      if (login != null) {

        // si password dans la table ...
        if (login.getMotDePasse() != null) {
//          String result = ConvertLib.rehashKeyWithSalt(pwd, login.getMotDePasse());
//          System.out.println("mdp BD:     " + login.getMotDePasse());
//          System.out.println("mdp fourni: " + pwd);
          ok = pwd != null && login.getMotDePasse().equals(pwd);
        } else {  // autrement on teste avec AD
           // ok = authenticateOnActiveDirectory(userName, pwd, domain);
          ok = false;
        }
      }

      // enregistrement de la session si identification correcte
      if (ok) {
        long start = System.currentTimeMillis();
        session(SESSION_USER_ID, "" + login.getPkLogin());
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
