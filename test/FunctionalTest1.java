
import ch.jcsinfo.system.StackTracer;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import play.Logger;
import workers.DbWorker;
import workers.DbWorkerAPI;

/**
 * Tests de certaines fonctionnalités de la couche métier (DbWorker) sans utiliser
 * aucune spécialité du framework Play.
 *
 * @author jcstritt
 */
public class FunctionalTest1 {

  @Test
  public void test01_DbOpen() {
    DbWorkerAPI dbWrk = new DbWorker("testPU");
    boolean ok = dbWrk.bdOuverte();
    Logger.info(StackTracer.getCurrentClassMethod() + ">>> DB open = " + ok + " <<<");
    assertTrue(ok);
    dbWrk.fermerBd();
  }
}
