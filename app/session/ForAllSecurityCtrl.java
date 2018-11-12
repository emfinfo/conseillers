package session;

import com.typesafe.config.Config;
import javax.inject.Inject;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Classe pour gérer la sécurité de tout utilisateur dans un contrôleur.
 *
 * @author jcstritt
 */
public class ForAllSecurityCtrl extends Security.Authenticator {
  private final int msTimeout;

  @Inject
  public ForAllSecurityCtrl(Config config) {
    msTimeout = config.getInt("application.msTimeout");
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

      // autrement on fait un reset du timeout (temps à l'heure actuelle)
      SessionManager.resetTimeout();

      // on retourne l'identifiant de session (avec la pk de login)
      username = "" + SessionManager.getUserId();
    }
    return username;
  }

  @Override
  public Result onUnauthorized(Context ctx) {
    return ok("You must be logged in to use this route !");
  }

}
