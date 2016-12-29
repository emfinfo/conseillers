import static org.junit.Assert.assertTrue;
import org.junit.Test;
import play.Logger;
import play.api.Play;
import play.db.jpa.JPAApi;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import workers.DbWorker;
import workers.DbWorkerAPI;
import workers.DbWorkerFactory;

/**
 *
 * @author jcstritt
 */
public class DbWorkerTest {

  @Test
  public void testDb1() {
    DbWorkerAPI wrk = new DbWorker("ConseillersPU");
    boolean ok = wrk.bdOuverte();
    Logger.info(">>> BD Ouverte (1): " + ok);
    assertTrue(ok);
  }

  @Test
  public void testDb2() {
    running(fakeApplication(), new Runnable() {

      @Override
      public void run() {
        JPAApi jpa = Play.current().injector().instanceOf(JPAApi.class);
        jpa.withTransaction(() -> {
//          System.out.println("em: " + jpa.em());
          DbWorkerAPI wrk = DbWorkerFactory.getInstance().getDbWorker();
          boolean ok = wrk.bdOuverte();
          Logger.info(">>> BD Ouverte (2): " + ok);
          assertTrue(ok);
        });
      }
    });
  }

}
