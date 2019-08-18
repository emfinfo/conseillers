package workers;

import ch.emf.dao.exceptions.JpaException;
import ch.emf.dao.helpers.Logger;
import com.google.inject.Guice;
import com.google.inject.Injector;
import models.Login;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Tests de certaines fonctionnalités de la couche métier (DbWorker) sans utiliser
 * aucune spécialité du framework Play.
 *
 * @author jcstritt
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitLoginWorkerTest {
  private static LoginWrk logWrk;
  private static Class<?> clazz;
 
  @BeforeClass
  public static void setUpClass() {
    Injector inj = Guice.createInjector(new GuiceModule());
    logWrk = inj.getInstance(LoginWrk.class);
    clazz = JUnitLoginWorkerTest.class;
  }
 
  @AfterClass
  public static void tearDownClass() {
    logWrk.deconnecter();
  }
 
  @Test
  public void test01_connecter() {
    boolean ok = false;
    try {
      logWrk.connecter("testPU");
      ok = logWrk.estConnecte();
    } catch (JpaException ex) {
      Logger.error(clazz, ex.getLocalizedMessage());
    }
    Logger.info(clazz, ok);
    assertTrue(ok);
  }
 
  @Test
  public void test02_ajouterLogin() {
    Login login = new Login("tartampionju", "edu", "test", "prof", "tartampionju@edufr.ch", "JT", "fr");
    Login loginAjoute = logWrk.creer(login);
    boolean ok = loginAjoute != null;
    Logger.info(clazz, ok);
    assertTrue(ok);
  }
 
  @Test
  public void test03_rechercherLogin() {
    Login login = logWrk.rechercher("tartampionju", "edu");
    boolean ok = login != null;
    Logger.info(clazz, ok);
    assertTrue(ok);
  }
 
  @Test
  public void test04_modifierLogin() {
    Login login = logWrk.rechercher("tartampionju", "edu");
    boolean ok = login != null;
    if (ok) {
      login.setDomaine("studentfr");
      ok = logWrk.modifier(login) == 1;
    }
    Logger.info(clazz, ok);
    assertTrue(ok);
  }
 
  @Test
  public void test05_supprimerLogin() {
    Login login = logWrk.rechercher("tartampionju", "studentfr");
    boolean ok = login != null;
    if (ok) {
      ok = logWrk.supprimer(login) == 1;
    }
    Logger.info(clazz, ok);
    assertTrue(ok);
  }

}
