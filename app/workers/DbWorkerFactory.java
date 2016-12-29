package workers;

import ch.emf.dao.JpaDao;
import ch.emf.dao.JpaDaoAPI;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.inject.Inject;
import javax.inject.Singleton;
import play.api.Play;
import play.db.jpa.JPAApi;

/**
 * Objet (usine) à créer le worker vers la BD. Cela permet d'injecter dans la couche dao
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

    @Inject
    public DbWorkerInvocationHandler(DbWorker dbWrk) {
      this.dbWrk = dbWrk;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      boolean okClass = method.getDeclaringClass() == DbWorkerAPI.class;
      boolean noAnn = !method.isAnnotationPresent(NoTransaction.class);
      JPAApi jpaApi = Play.current().injector().instanceOf(JPAApi.class);
//      System.out.println("getDeclaringClass: " + method.getDeclaringClass().getSimpleName() + ", method: " + method.getName() + ", ok class: " + okClass + ", ok trans.: " + noAnn + ", jpaApi: " + jpaApi);
      if (okClass && noAnn && jpaApi != null) {
        dao.open(jpaApi.em());
        return method.invoke(this.dbWrk, args);
      } else {
        return null;
      }
    }
  }


}
