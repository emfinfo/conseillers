package controllers.actions;

import controllers.helpers.Utils;
import java.util.concurrent.CompletionStage;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;

/**
 * Permet d'initialiser un timestamp et d'afficher une entrée dans
 * le fichier de log après la réponse HTTP fournie
 * (avec le temps utilisé pour la requête globale).
 *
 * @author jcstritt
 */
public class BeforeAfterAction extends play.mvc.Action.Simple {

  private void before(Context ctx) {
    ctx.response().setHeader("logtimestamp", "" + System.currentTimeMillis());
  }

  private void after(Context ctx) {
    Utils.logInfo(ctx);
  }

  public CompletionStage<Result> call(Http.Context ctx) {
    before(ctx);
    Utils.validCrossDomainContext(ctx);
    CompletionStage<Result> result = delegate.call(ctx); // This part calls your real action method
    after(ctx);
    return result;
  }

}
