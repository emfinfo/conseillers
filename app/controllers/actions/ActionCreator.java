package controllers.actions;

import helpers.Utils;
import java.lang.reflect.Method;
import java.util.concurrent.CompletionStage;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class ActionCreator implements play.http.ActionCreator {

  @Override
  public Action createAction(Http.Request request, Method actionMethod) {
    return new Action.Simple() {

      private void before(Http.Context ctx) {
        ctx.response().setHeader("logtimestamp", "" + System.currentTimeMillis());
      }

      private void after(Http.Context ctx) {
        Utils.logInfo(ctx);
      }

      @Override
      public CompletionStage<Result> call(Http.Context ctx) {
        before(ctx);
        Utils.validCrossDomainContext(ctx);
        CompletionStage<Result> result = delegate.call(ctx); // action réelle ici
        after(ctx);
        return result;
      }

    };
  }
}
