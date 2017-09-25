
import javax.inject.Inject;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPAApi;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import workers.DbWorker;
import workers.DbWorkerAPI;

/**
 *
 * @author jcstritt
 */
public class DbWorkerTest {

//  @Test
  public void testDb1() {
    DbWorkerAPI dbWrk = new DbWorker("ConseillersPU");
    boolean ok = dbWrk.bdOuverte();
    Logger.info(">>> BD Ouverte (1): " + ok);
    assertTrue(ok);
  }

//  @Test
//  public void testDb2() {
//
//    running(fakeApplication(), new Runnable() {
//
//      @Override
//      public void run() {
//
//        JPAApi jpaApi = Play.current().injector().instanceOf(JPAApi.class);
//
//        System.out.println("jpa: " + jpaApi);
////      System.out.println("factory: " + factory);
//        jpaApi.withTransaction(() -> {
////        DbWorkerAPI dbWrk = factory.getDbWorker();
////        boolean ok = dbWrk.bdOuverte();
//          boolean ok = true;
//          Logger.info(">>> BD Ouverte (2): " + ok);
//          assertTrue(ok);
//        });
//      }
//
//    }
//    );
//  }

//  @Test
  public void testDb2() {
    JPAApi jpaApi = null;

    running(fakeApplication(), new MyRunnable(jpaApi));

  }

  private class MyRunnable implements Runnable {
    private JPAApi jpaApi;

    @Inject
    public MyRunnable(JPAApi api) {
      this.jpaApi = api;
    }

     @Override
     public void run() {
      System.out.println("jpa: " + jpaApi);
//    System.out.println("factory: " + factory);

      jpaApi.withTransaction(() -> {
//        DbWorkerAPI dbWrk = factory.getDbWorker();
//        boolean ok = dbWrk.bdOuverte();
        boolean ok = true;
        Logger.info(">>> BD Ouverte (2): " + ok);
        assertTrue(ok);
      });
    }

  }


}
