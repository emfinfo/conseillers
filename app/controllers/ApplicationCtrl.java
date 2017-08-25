package controllers;

import com.typesafe.config.Config;
import controllers.actions.BeforeAfterAction;
import helpers.Utils;
import javax.inject.Inject;
import play.Logger;
import play.mvc.*;

public class ApplicationCtrl extends Controller {
  private String appFullName;

  @Inject
  public ApplicationCtrl(Config config) {

    // on prépare un message pour le fichier de log
    String appName = config.getString("application.name");
    String appVersion = config.getString("application.version");

    appFullName = appName + " " + appVersion;

    Logger.info(
      appFullName
      + " is running on Play " + play.core.PlayVersion.current()
      + " with Java " + System.getProperty("java.version")
      + " (" + System.getProperty("sun.arch.data.model") + " bits)");

  }

  public Result index() {
//    Result httpResult;
//    httpResult = redirect("/assets/index.html");
//    return httpResult;
    return redirect("/assets/index.html");
  }

  public Result checkPreFlight(String path) {
    Utils.validCrossDomainContext(request(), response());
    return ok();
  }

  @With(BeforeAfterAction.class)
  public Result lireVersion() {
    return Utils.toJson("version-app", appFullName, "version-srv", "Play " + play.core.PlayVersion.current());
  }

}
