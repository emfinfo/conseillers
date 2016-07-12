

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import play.api.Play;
import play.db.jpa.JPAApi;
import static play.test.Helpers.fakeApplication;
import workers.DbWorker;
import workers.DbWorkerAPI;
import static play.test.Helpers.running;

/**
 *
 * @author jcstritt
 */
public class DbWorkerTest {

  @Test
  public void testDb1() {
    DbWorkerAPI wrk = new DbWorker("ConseillersPU");
    boolean ok = wrk.bdOuverte();
    System.out.println("bdOuverte1: " + ok);
    assertTrue(ok);
  }

  @Test
  public void testDb2() {
    running(fakeApplication(), new Runnable() {

      @Override
      public void run() {
        JPAApi jpa = Play.current().injector().instanceOf(JPAApi.class);
        jpa.withTransaction(() -> {
          System.out.println("em: " + jpa.em());
          DbWorkerAPI wrk = new DbWorker(jpa.em());
          boolean ok = wrk.bdOuverte();
          System.out.println("bdOuverte2: " + ok);
          assertTrue(ok);
        });
      }
    });
  }

}
