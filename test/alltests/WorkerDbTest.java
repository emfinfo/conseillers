package alltests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import play.db.jpa.JPA;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import workers.WorkerDb;
import workers.WorkerDbAPI;

/**
 *
 * @author jcstritt
 */
public class WorkerDbTest {
  
  @Test
  public void testDb1() {
    WorkerDbAPI wrk = new WorkerDb("ConseillersPU");
    boolean ok = wrk.bdOuverte();
    System.out.println("bdOuverte1: " + ok);    
    assertTrue(ok);
  }

  @Test
  public void testDb2() {
    running(fakeApplication(), new Runnable() {
      
      @Override
      public void run() {
        JPA.withTransaction(new play.libs.F.Callback0() {
          @Override
          public void invoke() {

            WorkerDbAPI wrk = new WorkerDb(JPA.em());
            boolean ok = wrk.bdOuverte();
            System.out.println("bdOuverte2: " + ok);
            assertTrue(ok);
          }
        });
      }
    });
  }

}


