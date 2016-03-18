package controllers;

import controllers.helpers.Utils;
import static controllers.helpers.Utils.validCrossDomainContext;
import play.mvc.*;
import static play.mvc.Controller.request;
import static play.mvc.Controller.response;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

public class ApplicationCtrl extends Controller {

  public static Result index() {
    Result httpResult;
      httpResult = redirect("/assets/index.html");
    return httpResult;
  }
  
  public static Result checkPreFlight(String path) {
    validCrossDomainContext(request(), response());
    return ok();  
  }  

  public static Result lireVersionServeur() {
    validCrossDomainContext(request(), response());
    return Utils.toJson ("versionServeur", "\"Play " + play.core.PlayVersion.current() + "\"");
  }
  
}
