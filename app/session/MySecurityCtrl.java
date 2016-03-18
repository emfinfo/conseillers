package session;

import controllers.routes;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

/**
 *
 * @author jcstritt
 */
public class MySecurityCtrl extends Security.Authenticator {
  
  @Override
  public String getUsername(Context ctx) {
    return ctx.session().get(SessionManager.SESSION_LOGIN_ID);
  }
  
  @Override
  public Result onUnauthorized(Context ctx) {
    return redirect(routes.LoginCtrl.unauthorizedAccess());
  }
  
}
