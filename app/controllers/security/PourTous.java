package controllers.security;

import ch.emf.play.helpers.Utils;
import ch.emf.play.session.SessionUtils;
import com.typesafe.config.Config;
import javax.inject.Inject;
import play.i18n.MessagesApi;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Classe pour gérer la sécurité de tout utilisateur dans un contrôleur.
 *
 * @author jcstritt
 */
public class PourTous extends Security.Authenticator {
  private final int msTimeout;
  private final MessagesApi msgApi;
  private String errorMsg;

  @Inject
  public PourTous(Config config, MessagesApi msgApi) {
    this.msTimeout = config.getInt("application.msTimeout");
    this.msgApi = msgApi;
    this.errorMsg = "";
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
    if (SessionUtils.isTimeout(msTimeout)) {
      errorMsg = Utils.getMessage(msgApi, "security.TIMEOUT_MSG");
      SessionUtils.clear();
    } else {
      errorMsg = Utils.getMessage(msgApi, "security.UNAUTHORIZED_MSG");

      // autrement on fait un reset du timeout (temps à l'heure actuelle)
      SessionUtils.resetTimeout();

      // on retourne l'identifiant de session (avec la pk de login)
      username = "" + SessionUtils.getUserId();
    }
    return username;
  }

  @Override
  public Result onUnauthorized(Context ctx) {
    return Utils.toJson("message", errorMsg);
  }

}
