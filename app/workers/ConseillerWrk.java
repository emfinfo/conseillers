package workers;

import ch.emf.dao.JpaDaoAPI;
import ch.emf.dao.filtering.Search2;
import ch.emf.info.playdao.DaoRepositoryItf;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import models.Canton;
import models.Conseil;
import models.Conseiller;
import models.EtatCivil;
import models.Groupe;
import models.Parti;

/**
 * Couche "métier" pour gérer les opérations sur les conseillers
 * et les objets annexes (de et vers la base de données).<br>
 * Utilise daolayer avec JPA comme sous-couche d'accès aux données.
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

  public List<EtatCivil> chargerEtatsCivils() {
    List<EtatCivil> etatsCivils = dao.getList(EtatCivil.class, "abrev");
    etatsCivils.add(new EtatCivil("tous", "Tous les états civils"));
    return etatsCivils;
  }

  public List<Canton> chargerCantons() {
    List<Canton> cantons = dao.getList(Canton.class, "abrev");
    cantons.add(new Canton("CH", "Suisse"));
    return cantons;
  }

  public List<Parti> chargerPartis() {
    List<Parti> partis = dao.getList(Parti.class, "abrev");
    partis.add(new Parti("tous", "Tous les partis"));
    return partis;
  }

  public List<Conseil> chargerConseils() {
    List<Conseil> conseils = dao.getList(Conseil.class, "abrev");
    conseils.add(new Conseil("tous", "Tous les conseils"));
    return conseils;
  }

  public List<Groupe> chargerGroupes() {
    List<Groupe> groupes = dao.getList(Groupe.class, "abrev");
    groupes.add(new Groupe("tous", "Tous les groupes"));
    return groupes;
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

    // pour simuler une requête longue
//    try {
//      Thread.sleep(2000);
//    } catch (InterruptedException ex) {
//    }
//    System.out.println("chargerConseillers jpql: "+search.getJpql());
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
