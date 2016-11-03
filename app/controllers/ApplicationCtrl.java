package controllers;

import controllers.helpers.Utils;
import static controllers.helpers.Utils.validCrossDomainContext;
import javax.inject.Inject;
import play.Configuration;
import play.mvc.*;
import static play.mvc.Controller.request;
import static play.mvc.Controller.response;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

public class ApplicationCtrl extends Controller {
  private String appFullName;


  @Inject
  public ApplicationCtrl(Configuration configuration) {
    appFullName = configuration.getString("application.name") + " "
      + configuration.getString("application.version");
  }


  public Result index() {
//    Result httpResult;
//    httpResult = redirect("/assets/index.html");
//    return httpResult;
    return redirect("/assets/index.html");
  }

  public Result checkPreFlight(String path) {
    validCrossDomainContext(request(), response());
    return ok();
  }

  public Result lireVersionApplication() {
//    validCrossDomainContext(request(), response());
    return Utils.toJson("version-app", appFullName);
  }

  public Result lireVersionServeur() {
//    validCrossDomainContext(request(), response());
    return Utils.toJson("version-srv", "Play " + play.core.PlayVersion.current());
  }

}
