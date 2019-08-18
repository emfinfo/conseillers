package controllers.security;

import ch.emf.play.helpers.Utils;
import ch.emf.play.helpers.SessionUtils;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import java.util.Optional;
import play.i18n.MessagesApi;
import play.mvc.Http;
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
   * Cette classe de sécurité si elle est utilisé dans un contrôleur
   * avec une annotation @Security.Authenticated(PourTous.class)
   * permet l'accès à toutes les actions du contrôleur pour peu
   * que l'on soit logué et qu'il n'y pas eu de timeout de session
   * entre temps.<br>
   * <br>
   * Dans le cas contraire, la méthode "onUnauthorized" sera appellée.
   * Elle fermera la session dans tous les cas.
   * 
   * @param req la requête HTTP avec ses meta-données
   * @return la PK de login sous la forme d'un string ou null
   */
  @Override
  public Optional<String> getUsername(Http.Request req) {
    Optional<String> username = Optional.ofNullable(null);

    // un timeout de session est-il arrivé ?
    if (SessionUtils.isTimeout(req, msTimeout)) {
      errorMsg = Utils.getMessage(req, msgApi, "security.TIMEOUT_MSG");
    } else {
      username = SessionUtils.getOptionalUserId(req);
      errorMsg = Utils.getMessage(req, msgApi, "security.UNAUTHORIZED_MSG");
    }
    return username;
  }

  @Override
  public Result onUnauthorized(Http.Request req) {
    return Utils.toJson("message", errorMsg).withNewSession();
  }

}
