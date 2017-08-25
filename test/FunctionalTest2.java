
import ch.jcsinfo.system.StackTracer;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPAApi;
import play.test.WithApplication;
import workers.DbWorkerAPI;
import workers.DbWorkerFactory;

/**
 * Tests de certaines fonctionnalités de la couche métier (DbWorker) en situation
 * réelle d'application "back-end" gérée par le framework Play.
 *
 * @author jcstritt
 */
public class FunctionalTest2 extends WithApplication {

  @Test
  public void test02_DbOpen() {

    // éléments injectés
//    ExecutionContext ctx = app.injector().instanceOf(ExecutionContext.class);
    JPAApi jpa = app.injector().instanceOf(JPAApi.class);
    DbWorkerFactory factory = app.injector().instanceOf(DbWorkerFactory.class);

    // récupération du worker
    DbWorkerAPI dbWrk = factory.getDbWorker();
    jpa.withTransaction(() -> {
      boolean ok = dbWrk.bdOuverte();
      Logger.info(StackTracer.getCurrentClassMethod() + ">>> DB open = " + ok + " <<<");
      assertTrue(ok);
    });

  }

}
