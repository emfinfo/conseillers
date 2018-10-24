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
    String username = ctx.session().get(SessionManager.SESSION_USER_ID);
//    System.out.println("MySecurityCtrl session: "+ ctx.session());
//    if (username == null || username.isEmpty()) {
//      RequestHeader header = ctx._requestHeader();
//      System.out.println("no session, header: " + header.domain());
//      Map<String, String> headers = ctx.response().getHeaders();
//      for (Entry<String, String> e : headers.entrySet()) {
//        String key = e.getKey();
//        String value = e.getValue();
//        System.out.println("key: " + key + ", value: " + value);
//      }
//
//      Headers headers2 = ctx.request().getHeaders();
//      List<String> hds = headers2.getAll("");
//      for (String h : hds) {
//        System.out.println("header: " + h);
//      }
//      String userAgent = ctx.request().getQueryString("q");
//
//    }

    return username;
  }

  @Override
  public Result onUnauthorized(Context ctx) {
    return ok("You must be logged for this route !");
  }

}
