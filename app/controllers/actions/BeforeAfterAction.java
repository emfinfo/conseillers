package controllers.actions;

import ch.jcsinfo.play.helpers.Utils;
import static ch.jcsinfo.play.helpers.SessionUtils.SESSION_TIMESTAMP;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import java.util.concurrent.CompletionStage;
import play.mvc.Action.Simple;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Permet d'initialiser un timestamp et d'afficher une entrée dans
 * le fichier de log après la réponse HTTP fournie
 * (avec le temps utilisé pour la requête globale).
 *
 * @author jcstritt
 */
public class BeforeAfterAction extends Simple {

  private final int msTimeout;

  @Inject
  public BeforeAfterAction(Config config) {
    this.msTimeout = config.getInt("application.msTimeout");
  }

  public boolean isTimeout(Http.Request req) {
    long cTime = System.currentTimeMillis();
    long sTime = Long.parseLong(req.session().get(SESSION_TIMESTAMP).orElse("0"));
//    System.out.println("cTime: " + cTime + " sTime: " + sTime + " diff: " + (cTime-sTime));
    return (cTime - sTime) >= msTimeout;
  }

  private void after(Http.Request req, Result result, long ts) {
    Utils.validCrossDomainRequest(req, result);
    Utils.logInfo(req, ts);
  }

  @Override
  public CompletionStage<Result> call(Http.Request req) {
    
    // save a time stamp to compute the elapsed time of a request answer
    long ts = System.currentTimeMillis();    

    // check if timeout
    if (isTimeout(req)) {
      return delegate.call(req).thenApply(result -> {
        after(req, result, ts);
        return result.withNewSession();
      });
    } else {
      return delegate.call(req).thenApply(result -> {
        after(req, result, ts);
        return result.addingToSession(req, SESSION_TIMESTAMP, "" + System.currentTimeMillis());
      });
    }

  }

}
