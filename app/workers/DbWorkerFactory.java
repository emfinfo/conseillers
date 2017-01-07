package workers;

import ch.emf.dao.JpaDao;
import ch.emf.dao.JpaDaoAPI;
import ch.emf.dao.NoJpaTransaction;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import play.api.Play;
import play.db.jpa.JPAApi;

/**
 * Usine pour créer le worker de la BD. Cela permet d'injecter dans la couche dao
 * l'entity-manager géré par Play, ceci avant chaque appel de méthode.
 *
 * @author Jean-Claude Stritt
 */
@Singleton
public class DbWorkerFactory  {
  private static DbWorkerFactory instance = null;
  private final JpaDaoAPI dao;
  private final DbWorkerAPI dbWrk;

  private DbWorkerFactory() {
    dao = new JpaDao();
    dbWrk = (DbWorkerAPI) Proxy.newProxyInstance(
      this.getClass().getClassLoader(),
      new Class<?>[]{DbWorkerAPI.class},
      new DbWorkerInvocationHandler(new DbWorker(dao)));
  }

  public synchronized static DbWorkerFactory getInstance() {
    if (instance == null) {
      instance = new DbWorkerFactory();
    }
    return instance;
  }

  public DbWorkerAPI getDbWorker() {
    return dbWrk;
  }



  private class DbWorkerInvocationHandler implements InvocationHandler {
    private DbWorkerAPI dbWrk;

    public DbWorkerInvocationHandler(DbWorker dbWrk) {
      this.dbWrk = dbWrk;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      boolean okClass = method.getDeclaringClass() == DbWorkerAPI.class;
      JPAApi jpaApi = Play.current().injector().instanceOf(JPAApi.class);
      if (okClass && jpaApi != null) {
        if (!method.isAnnotationPresent(NoJpaTransaction.class)) {

          // récupération de l'entity-manager de Play
          EntityManager em = jpaApi.em();

          // multi-tenant example
//        int loginId = SessionManager.getSessionLoginId();
//        int comptaId = SessionManager.getSessionComptaId();
//        if (loginId > 0 && comptaId > 0) {
//          em.setProperty("eclipselink.session-name", "multitenant-session-" + loginId + "-" + comptaId);
//          em.setProperty("eclipselink.tenant-id1", "" + loginId);
//          em.setProperty("eclipselink.tenant-id2", "" + comptaId);
//        }

          // injection de l'entity-manager de Play dans la couche dao
          dao.setEntityManager(em);
        }
        return method.invoke(this.dbWrk, args);
      } else {
        return null;
      }
    }
  }

}
