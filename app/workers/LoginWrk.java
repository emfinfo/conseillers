package workers;

import ch.emf.dao.JpaDaoAPI;
import ch.emf.dao.filtering.Search;
import ch.emf.info.playdao.DaoRepositoryItf;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Login;

/**
 * Couche "métier" pour gérer les opérations de login avec la base de données.<br>
 * Utilise daolayer avec JPA comme sous-couche d'accès aux données.
 *
 * @author Jean-Claude Stritt
 */
@Singleton
public class LoginWrk {
  private final JpaDaoAPI dao;

  @Inject
  public LoginWrk(DaoRepositoryItf rep) {
    this.dao = rep.getDao();
  }

  public LoginWrk(JpaDaoAPI dao) {
    this.dao = dao;
  }

  public Login ajouter(Login login) {
    if (dao.create(login) == 1) {
      dao.detach(login);
    }
    return login;
  }

  public Login rechercher(String nom, String domaine) {
    Search s = new Search(Login.class);
    s.addFilterEqual("nom", nom);
    s.addFilterAnd();
    s.addFilterEqual("domaine", domaine);
    Login login = dao.getSingleResult(s);
    if (login != null) {
      dao.detach(login);
    }
    return login;
  }

  public int modifier(Login login) {
    return dao.update(login);
  }

  public int supprimer(Login login) {
    return dao.delete(Login.class, login.getPk());
  }

}
