package modules;

import javax.inject.Inject;
import javax.inject.Singleton;
import play.Environment;

/**
 * Premier traitement "utilisateur" possible lors du démarrage de l'application.
 *
 * @author jcstritt
 */
@Singleton
public class AppStart {

  @Inject
  public AppStart(Environment environment) {
//    Logger.info("Application has started !");
    if (environment.isTest()) {
      // your code
    } else {
      // your code
    }

    // you can use yourInjectedService here
  }
}
