package session;

import com.typesafe.config.Config;
import javax.inject.Inject;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Classe pour gérer la sécurité des appels "professeur" dans un contrôleur.
 *
 * @author jcstritt
 */
public class ForTeacherSecurityCtrl extends Security.Authenticator {
  private final int msTimeout;

  @Inject
  public ForTeacherSecurityCtrl(Config config) {
    this.msTimeout = config.getInt("application.msTimeout");
  }

  /**
   * Comme identificateur unique, nous stockons en fait la PK
   * de login dans la session et pas le nom d'utilisateur.
   *
   * @param ctx le contexte de la requête HTTP
   * @return la PK sous la forme d'un string ou null
   */
  @Override
  public String getUsername(Context ctx) {
    String username = null;

    // un timeout de session est-il arrivé ? Si oui, on efface le cookie de session
    if (SessionManager.isTimeout(msTimeout)) {
      SessionManager.clear();
    } else {

      // on récupère le profil de l'utilisateur
      String sessionProfile = SessionManager.getUserProfile();

      // si c'est réellement un prof
      if (sessionProfile.equalsIgnoreCase("prof")) {

        // on fait un reset du timeout (temps à l'heure actuelle)
        SessionManager.resetTimeout();

        // on retourne l'identifiant de session (pk de login)
        username = "" + SessionManager.getUserId();
      }
    }
    return username;
  }

  @Override
  public Result onUnauthorized(Context ctx) {
    return ok("You must be logged as a teacher !");
  }

}
