package controllers.actions;

import ch.emf.play.helpers.Utils;
import static ch.emf.play.helpers.SessionUtils.SESSION_TIMESTAMP;
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
    long sTime = Long.parseLong(req.session().getOptional(SESSION_TIMESTAMP).orElse("0"));
//    System.out.println("cTime: " + cTime + " sTime: " + sTime + " diff: " + (cTime-sTime));
    return (cTime - sTime) >= msTimeout;
  }

  private void before(Http.Request req) {
    req.getHeaders().addHeader("x-logtimestamp", "" + System.currentTimeMillis());
  }

  private void after(Http.Request req, Result result) {
    Utils.validCrossDomainRequest(req, result);
    Utils.logInfo(req);
  }

  @Override
  public CompletionStage<Result> call(Http.Request req) {
    before(req);

    if (isTimeout(req)) {
      return delegate.call(req).thenApply(result -> {
        after(req, result);
        return result.withNewSession();
      });
    } else {
      return delegate.call(req).thenApply(result -> {
        after(req, result);
        return result.addingToSession(req, SESSION_TIMESTAMP, "" + System.currentTimeMillis());
      });
    }

  }

}
