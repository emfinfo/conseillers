package workers;

import ch.emf.dao.JpaDaoAPI;
import ch.emf.dao.exceptions.JpaException;
import javax.persistence.EntityManager;

/**
 * Basic abstract worker.
 * 
 * @author jcstritt
 */
public class Worker {
  protected JpaDaoAPI dao;
  
  public Worker(JpaDaoAPI dao) {
    this.dao = dao;
  }
 
  public void connecter(String pu) throws JpaException {
    dao.connect(pu);
  }
  
  public boolean estConnecte() {
    return dao.isConnected();
  }
      
  public void deconnecter() {
    dao.disconnect();
  }  

  public void memoriser(EntityManager em) {
    dao.setEntityManager(em);
  }
  
}
