package controllers.actions;

import ch.emf.play.helpers.Utils;
import ch.emf.play.helpers.SessionUtils;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import java.lang.reflect.Method;
import java.util.concurrent.CompletionStage;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Permet d'initialiser un timestamp et d'afficher une entrée dans
 * le fichier de log après la réponse HTTP fournie
 * (avec le temps utilisé pour la requête globale).
 *
 * @author jcstritt
 */
public class ActionCreator implements play.http.ActionCreator {
  private final int msTimeout;

  @Inject
  public ActionCreator(Config config) {
    this.msTimeout = config.getInt("application.msTimeout");
  }

  @Override
  public Action<?> createAction(Http.Request request, Method actionMethod) {
    return new Action.Simple() {

      @Override
      public CompletionStage<Result> call(Http.Request req) {
  
        // save a time stamp for the elapsed time in logger
        long timestamp = System.currentTimeMillis();
        
        // did a session timeout occur
        boolean timeout = SessionUtils.isTimeout(req, msTimeout);

        // apply current action 
        return delegate.call(req).thenApply(result -> {

          // change result if session timeout
          if (timeout && req.path().contains("/session/status")) {
            result = Utils.toJson("open", false);
          }

          // valid a cross domain request (add some headers for CORS)
          Utils.validCrossDomainRequest(req, result);

          // display some info into log file
          Utils.logInfo(req, timestamp);

          // return final result
          if (timeout) {
            return result.withNewSession();
          } else if (SessionUtils.isOpen(req)) {
            return result.addingToSession(req, "timestamp", "" + System.currentTimeMillis());
          } else {
            return result;
          }
        });
      }

    };
  }

}
