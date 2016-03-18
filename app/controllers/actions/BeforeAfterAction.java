package controllers.actions;

import controllers.helpers.Utils;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

/**
 * Permet d'initialiser un timestamp et d'afficher une entrée dans
 * le fichier de log après la réponse HTTP fournie.
 * 
 * @author jcstritt
 */
public class BeforeAfterAction extends Action.Simple {

  private void before(Context ctx) {
//    ctx.flash().put("timestamp", "" + System.currentTimeMillis());
    ctx.response().setHeader("logtimestamp",  "" + System.currentTimeMillis());
  }

  private void after(Context ctx) {
    Utils.logInfo(ctx);    
  }

  @Override
  public F.Promise<Result> call( Context ctx ) throws Throwable  {
    try {
      before(ctx);
      Utils.validCrossDomainContext(ctx);
      F.Promise<Result> result = delegate.call(ctx); // This part calls your real action method
      after(ctx);
      return result;
    } catch (RuntimeException e) {
      System.out.println("***RuntimeException: "+ e.getMessage());
      throw e;
    } catch (Throwable t) {
      System.out.println("***Throwable: "+ t.getMessage());
      throw new RuntimeException(t);
    }
  }
  

}
