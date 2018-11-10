package workers;

import ch.emf.dao.JpaDao;
import ch.emf.dao.JpaDaoAPI;
import ch.emf.dao.conn.impl.ConnectWithPU;
import ch.emf.dao.exceptions.JpaException;
import ch.emf.dao.helpers.Logger;
import ch.emf.info.playdao.DaoRepositoryItf;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Couche "métier" pour gérer les opérations de connexion avec la base de données.<br>
 * Utilise daolayer avec JPA comme sous-couche d'accès aux données.<br>
 * N'est utile que pour des tests unitaires style JUnit.
 *
 * @author Jean-Claude Stritt
 */
@Singleton
public class ConnexionWrk {
  private final JpaDaoAPI dao;

  @Inject
  public ConnexionWrk(DaoRepositoryItf rep) {
    this.dao = rep.getDao();
  }

  public ConnexionWrk() {
    dao = new JpaDao();
  }

  public JpaDaoAPI getDao() {
    return dao;
  }

  public void connecter() throws JpaException {
    dao.setConnection(new ConnectWithPU("testPU"));
  }

  public boolean estConnectee() {
    return dao.isConnected();
  }

  public void deconnecter() {
    try {
      dao.getConnection().disconnect();
    } catch (Exception ex) {
      Logger.debug(ConnexionWrk.class, ex.getLocalizedMessage());
    }
  }

}
