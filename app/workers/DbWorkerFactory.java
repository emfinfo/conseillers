package workers;

import ch.emf.dao.JpaDao;
import ch.emf.dao.JpaDaoAPI;
import ch.emf.dao.NoJpaTransaction;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import play.db.jpa.JPAApi;

/**
 * Usine pour créer une seule fois le worker de la BD et sa couche dao.
 *
 * @author Jean-Claude Stritt
 */
@Singleton
public class DbWorkerFactory {

  private final JpaDaoAPI dao;
  private final DbWorkerAPI dbWrk;

  @Inject
  private DbWorkerFactory(JPAApi jpa) {
//    Logger.info("DbWorkerFactory started");
    dao = new JpaDao();
    dbWrk = (DbWorkerAPI) Proxy.newProxyInstance(
      this.getClass().getClassLoader(),
      new Class<?>[]{DbWorkerAPI.class},
      new DbWorkerInvocationHandler(jpa, new DbWorker(dao)));
  }

  /**
   * Retourne une instance sur DbWorker.
   *
   * @return l'imstance sur DbWorker
   */
  public DbWorkerAPI getDbWorker() {
    return dbWrk;
  }

  /**
   * Classe privée pour invoquer les méthodes de DBWorker tout y injectant l'entity manager géré par Play.
   */
  private class DbWorkerInvocationHandler implements InvocationHandler {

    private JPAApi jpa;
    private DbWorkerAPI dbWrk;

    public DbWorkerInvocationHandler(JPAApi jpa, DbWorker dbWrk) {
      this.jpa = jpa;
      this.dbWrk = dbWrk;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      boolean okClass = method.getDeclaringClass() == DbWorkerAPI.class;

      if (okClass && jpa != null) {
        if (!method.isAnnotationPresent(NoJpaTransaction.class)) {

          // récupération de l'entity-manager de Play
          EntityManager em = jpa.em();

          // exemple multi-tenant
//          int userId = SessionManager.getUserId();
//          int dbId = SessionManager.getDbId();
//          if (userId > 0 && dbId > 0) {
//            em.setProperty("eclipselink.session-name", "multitenant-session-" + userId + "-" + dbId);
//            em.setProperty("eclipselink.tenant-id1", "" + userId);
//            em.setProperty("eclipselink.tenant-id2", "" + dbId);
//          }

          // injection de l'entity-manager de Play dans la couche dao
          dao.setEntityManager(em);
        }
        return method.invoke(dbWrk, args);
      } else {
        return null;
      }
    }
  }

}
