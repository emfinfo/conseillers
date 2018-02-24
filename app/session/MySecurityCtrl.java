package session;

import com.typesafe.config.Config;
import javax.inject.Inject;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Classe pour gérer la sécurité des appels de méthodes dans un contrôleur.
 *
 * @author jcstritt
 */
public class MySecurityCtrl extends Security.Authenticator {

  private final Config config;

  @Inject
  public MySecurityCtrl(Config config) {
    this.config = config;
  }

  @Override
  public String getUsername(Context ctx) {
    return ctx.session().get(SessionManager.SESSION_USER_ID);
  }

  @Override
  public Result onUnauthorized(Context ctx) {
//    return redirect(controllers.LoginCtrl.unauthorizedAccess());
//    Result r = configuration.
//    return redirect(controllers.LoginCtrl.unauthorizedAccess());
    return ok("You must be logged for this route !");
  }

}
