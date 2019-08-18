package controllers;

import ch.emf.play.helpers.Utils;
import ch.emf.play.models.ReleaseInfo;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.*;

public class ApplicationCtrl extends Controller {
  private ReleaseInfo releaseInfo;
  private final Logger logger;

  @Inject
  public ApplicationCtrl(Config config) {
    releaseInfo = new ReleaseInfo();
    
    // on prépare un message pour le fichier de log
    String appName = config.getString("application.name");
    String appVersion = config.getString("application.version");
    releaseInfo.setApplication(appName + " " + appVersion);
    releaseInfo.setServer("Play " + play.core.PlayVersion.current());
    releaseInfo.setData(config.getString("db.default.data.version"));

    // on ajoute le message dans le fichier de log
    logger = LoggerFactory.getLogger(this.getClass());      
    logger.info(releaseInfo.getApplication()
      + " is running on Play " + play.core.PlayVersion.current()
      + " with Java " + System.getProperty("java.version")
      + " (" + System.getProperty("sun.arch.data.model") + " bits)");
  }

  public Result index() {
    return redirect("/assets/index.html").withNewSession();
  }

  public Result checkPreFlight(Http.Request req, String path) {
    Result result = ok();
    Utils.validCrossDomainRequest(req, result);
    return result;
  }

//  @With(BeforeAfterAction.class)
  public Result lireVersion() {
    return Utils.toJson(releaseInfo);
  }

}
