package session;

import ch.jcsinfo.util.ConvertLib;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import models.Login;
import play.Configuration;
import static play.mvc.Controller.session;

/**
 * Permet de gérer une session utilisateur.
 *
 * @author jcstritt
 */
public class SessionManager {
  public final static String SESSION_LOGIN_ID = "login-id";
  public final static String SESSION_COMPTA_ID = "compta-id";
  public final static String SESSION_LANG = "fr";
  public final static String SESSION_TIMESTAMP = "timestamp";


  /**
   * Controle si une authentification est possible sur ActiveDirectory.
   *
   * @param userName un nom d'utilisateur unique
   * @param psw un mot de passe
   * @param domain un nom de domaine "Active Directory"
   *
   * @return TRUE si'laccès à ActiveDirectory a été un succès
   */
  @SuppressWarnings("UseOfObsoleteCollectionType")
  @Inject
  private static boolean authenticateOnActiveDirectory(Configuration configuration, String userName, String psw, String domain) {
    final String AD_PROTOCOLE = "ldap://";
    final int AD_PORT = 389;
//    String contextFactory = play.Play.application().configuration().getString("ad.context_factory");
//    String adServer = AD_PROTOCOLE
//            + play.Play.application().configuration().getString("ad.server")
//            + ":" + AD_PORT;
    String contextFactory = configuration.getString("ad.context_factory");
    String adServer = AD_PROTOCOLE + configuration.getString("ad.server") + ":" + AD_PORT;

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
  public static boolean createSession(String userName, String pwd, String domain, Login login) {
    boolean ok = false;
    if (session().get(SESSION_LOGIN_ID) == null) {

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
        session(SESSION_LOGIN_ID, "" + login.getPkLogin());
        session(SESSION_COMPTA_ID, "1");
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
  public static boolean clearSession() {
    boolean ok = session().get(SESSION_LOGIN_ID) != null;
    if (ok) {
      session().clear();
    }
    return !isSessionOpen();
  }


  /**
   * Teste si la session est ouverte.
   *
   * @return true si une session est ouverte
   */
  public static boolean isSessionOpen() {
    boolean ok = session().get(SESSION_LOGIN_ID) != null;
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
  public static boolean isSessionTimeout(int ms) {
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
  public static int getSessionLoginId() {
    String userId = session().get(SESSION_LOGIN_ID);
    return ConvertLib.stringToInt(userId);
  }

  /**
   * Récupérer la langue de l'utilisateur logué.
   *
   * @return une langue sur 2 caractères (ex: "fr" pour le français)
   */
  public static String getSessionLang() {
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
  public static int getSessionComptaId() {
    String tenantId = session().get(SESSION_COMPTA_ID);
    return ConvertLib.stringToInt(tenantId);
  }

  /**
   * Mémoriser l'identifiant de la compta en cours.
   *
   * @param comptaId un identifiant numérique
   */
  public static void setSessionComptaId(int comptaId) {
    session(SESSION_COMPTA_ID, "" + comptaId);
  }

}
