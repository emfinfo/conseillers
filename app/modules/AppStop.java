package modules;

import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;
import javax.inject.Singleton;
import play.inject.ApplicationLifecycle;

/**
 * Dernier traitement possible lors de l'arrêt de l'application.
 *
 * @author jcstritt
 */
@Singleton
public class AppStop {

  @Inject
  public AppStop(ApplicationLifecycle lifecycle) {

    lifecycle.addStopHook(() -> {
//      Logger.info("Application shutdown...");

      return CompletableFuture.completedFuture(null);
    });

  } 
}
