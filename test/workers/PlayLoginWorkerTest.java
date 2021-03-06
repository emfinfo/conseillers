package workers;


import ch.emf.dao.helpers.Logger;
import models.Login;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import play.db.jpa.JPAApi;
import play.inject.Injector;
import play.test.WithApplication;

/**
 * Tests de certaines fonctionnalités de la couche métier (DbWorker) en situation
 * réelle d'application "back-end" gérée par le framework Play.
 *
 * ATTENTION, le fait d'étendre WithApplication crée une pseudo-application
 * pour chaque méthode de test !!! Ainsi des objets DaoRepository et JpaDao
 * sont construits à chaque test, contrairement à l'application principale.
 *
 * @author jcstritt
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayLoginWorkerTest extends WithApplication {
  private static JPAApi jpaApi;
  private static LoginWrk logWrk;
  private static Class<?> clazz;
 
 
  @BeforeClass
  public static void setUpClass() {
    clazz = JUnitLoginWorkerTest.class;
  }
 
  @Override
  public void startPlay() {
    super.startPlay();
    Injector inj = app.injector();
    jpaApi = inj.instanceOf(JPAApi.class);
    logWrk = inj.instanceOf(LoginWrk.class);
  }
 
  @Override
  public void stopPlay() {
    super.stopPlay();
  }
 
  @Test
  public void test11_estConnectee() {
    jpaApi.withTransaction(em -> {
      logWrk.memoriser(em);
      boolean ok = logWrk.estConnecte();
      Logger.info(clazz, ok);
      assertTrue(ok);
    });
  }
 
  @Test
  public void test12_ajouterLogin() {
    jpaApi.withTransaction(em -> {
      logWrk.memoriser(em);
      Login login = new Login("tartampionju", "edu", "test", "prof", "tartampionju@edufr.ch", "JT", "fr");
      Login loginAjoute = logWrk.creer(login);
      boolean ok = loginAjoute != null;
      Logger.info(clazz, ok);
      assertTrue(ok);
    });
  }
 
  @Test
  public void test13_rechercherLogin() {
    jpaApi.withTransaction(em -> {
      logWrk.memoriser(em);
      Login login = logWrk.rechercher("tartampionju", "edu");
      boolean ok = login != null;
      Logger.info(clazz, ok);
      assertTrue(ok);
    });
  }
 
  @Test
  public void test14_modifierLogin() {
    jpaApi.withTransaction(em -> {
      logWrk.memoriser(em);
      Login login = logWrk.rechercher("tartampionju", "edu");
      boolean ok = login != null;
      if (ok) {
        login.setDomaine("studentfr");
        ok = logWrk.modifier(login) == 1;
      }
      Logger.info(clazz, ok);
      assertTrue(ok);
    });
  }
 
  @Test
  public void test15_supprimerLogin() {
    jpaApi.withTransaction(em -> {
      logWrk.memoriser(em);
      Login login = logWrk.rechercher("tartampionju", "studentfr");
      boolean ok = login != null;
      if (ok) {
        ok = logWrk.supprimer(login) == 1;
      }
      Logger.info(clazz, ok);
      assertTrue(ok);
    });
  }


}
