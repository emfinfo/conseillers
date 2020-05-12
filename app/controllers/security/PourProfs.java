package controllers.security;

import ch.jcsinfo.play.helpers.Utils;
import ch.jcsinfo.play.helpers.SessionUtils;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import java.util.Optional;
import play.i18n.MessagesApi;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Classe pour gérer la sécurité des appels "professeur" dans un contrôleur.
 *
 * @author jcstritt
 */
public class PourProfs extends Security.Authenticator {
  private final int msTimeout;
  private final MessagesApi msgApi;
  private String errorMsg;

  @Inject
  public PourProfs(Config config, MessagesApi msgApi) {
    this.msTimeout = config.getInt("application.msTimeout");
    this.msgApi = msgApi;
    this.errorMsg = "";
  }

  /**
   * Cette classe de sécurité si elle est utilisé dans un contrôleur
   * avec une annotation @Security.Authenticated(PourProfs.class)
   * permet l'accès à toutes les actions du contrôleur pour peu
   * que l'on soit logué comme "prof" (dans le profil de la session)
   * et qu'il n'y pas eu de timeout de session entre temps.<br>
   * <br>
   * Dans le cas contraire, la méthode "onUnauthorized" sera appellée.
   * Elle fermera la session si un timeout est détecté.
   *
   * @param req la requête HTTP avec ses meta-données
   * @return la PK de login sous la forme d'un string ou null
   */
  @Override
  public Optional<String> getUsername(Http.Request req) {
    Optional<String> username = Optional.ofNullable(null);

    // un timeout de session est-il arrivé ? Si oui, on efface le cookie de session
    if (SessionUtils.isTimeout(req, msTimeout)) {
      errorMsg = Utils.getMessage(req, msgApi, "security.TIMEOUT_MSG");
    } else {
      errorMsg = Utils.getMessage(req, msgApi, "security.FORBIDDEN_MSG");

      // on récupère le profil de l'utilisateur
      String sessionProfile = SessionUtils.getUserProfile(req);

      // si c'est réellement un prof
      if (sessionProfile.equalsIgnoreCase("prof")) {

        // on retourne l'identifiant de session (pk de login)
        username = Optional.of("" + SessionUtils.getUserId(req));
      }
    }
    return username;
  }

  @Override
  public Result onUnauthorized(Http.Request req) {
    if (errorMsg.contains("Timeout")) {
      return Utils.toJson("message", errorMsg).withNewSession();
    } else {
      return Utils.toJson("message", errorMsg);      
    }  
  }

}
