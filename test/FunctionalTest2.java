
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
  private JPAApi jpa;
  private DbWorkerAPI dbWrk;

  @Override
  public void startPlay() {
    super.startPlay();
    jpa = app.injector().instanceOf(JPAApi.class);
    dbWrk = app.injector().instanceOf(DbWorkerFactory.class).getDbWorker();
    Logger.info(">>> startPlay !");
  }

  @Override
  public void stopPlay() {
    super.stopPlay();
    Logger.info(">>> stopPlay !");
  }

  @Test
  public void test02_DbOpen() {
//    String cur = StackTracer.getCurrentMethod();

    // exécuter la requête avec une transaction JPA
    jpa.withTransaction(() -> {
      boolean ok = dbWrk.bdOuverte();
      Logger.info(StackTracer.getCurrentClassMethod() + ">>> DB open = " + ok + " <<<");
//      Logger.warn(cur + ">>> DB open = " + ok + " <<<");
      assertTrue(ok);
    });

  }

}
