package workers;

import ch.emf.dao.JpaDaoAPI;
import ch.emf.dao.filtering.Search2;
import ch.emf.dao.play.DaoRepositoryItf;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import models.Canton;
import models.Conseil;
import models.Conseiller;
import models.Parti;

/**
 * Couche "métier" pour gérer les objets "Conseiller" et "Activite"
 * contenus dans la base de données.  Utilise daolayer avec JPA comme
 * sous-couche d'accès aux données.
 *
 * @author Jean-Claude Stritt
 */
@Singleton
public class ConseillerWrk {
  private final JpaDaoAPI dao;

  @Inject
  public ConseillerWrk(DaoRepositoryItf rep) {
    this.dao = rep.getDao();
  }

  public ConseillerWrk(JpaDaoAPI dao) {
    this.dao = dao;
  }

  public List<Conseiller> chargerConseillers(String canton, String conseil, String parti, boolean actif) {
    String jpql = "SELECT distinct c FROM Conseiller c LEFT JOIN c.activites a WHERE a.conseiller=c";
    Search2 search = new Search2(jpql);
    if (!canton.isEmpty()) {
      Canton ct = dao.getSingleResult(Canton.class, "abrev", canton);
      search.addFilterEqual("c.canton", ct);
    }
    if (!conseil.isEmpty()) {
      Conseil co = dao.getSingleResult(Conseil.class, "abrev", conseil);
      search.addFilterEqual("a.conseil", co);
    }
    if (!parti.isEmpty()) {
      Parti pa = dao.getSingleResult(Parti.class, "abrev", parti);
      search.addFilterEqual("c.parti", pa);
    }
    if (actif) {
      search.addFilterEqual("c.actif", true);
      search.addFilterIsNull("a.dateSortie");
    }
    search.addSortFields("c.nom", "c.prenom");

    return dao.getList(search);
  }

  public List<Conseiller> chargerConseillers(String nom, boolean actif) {
    String jpql = "SELECT distinct c FROM Conseiller c JOIN c.activite a WHERE a.conseiller=c";
    Search2 search = new Search2(jpql);
    List<Conseiller> conseillers = new ArrayList<>();
    if (!nom.isEmpty()) {
      search.addFilterLike("c.nom", nom + "%");
      if (actif) {
        search.addFilterEqual("c.actif", true);
        search.addFilterIsNull("a.dateSortie");
      }
      search.addSortFields("c.nom", "c.prenom");
      conseillers = dao.getList(search);
    }
    return conseillers;
  }

}
