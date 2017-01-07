package workers;

import ch.emf.dao.JpaDao;
import ch.emf.dao.JpaDaoAPI;
import ch.emf.dao.filtering.Search2;
import java.util.ArrayList;
import java.util.List;
import models.Canton;
import models.Conseil;
import models.Conseiller;
import models.EtatCivil;
import models.Groupe;
import models.Login;
import models.Parti;
import play.Logger;

/**
 * Couche "métier" pour gérer les demandes vers la base de données.
 * Utilise daolayer avec JPA.
 *
 * @author Jean-Claude Stritt
 */
public class DbWorker implements DbWorkerAPI {
  private final JpaDaoAPI dao;

  public DbWorker(JpaDaoAPI dao) {
    Logger.debug("DbWorker is  loaded as a Singleton !");
    this.dao = dao;
  }

  public DbWorker(String pu) {
    dao = new JpaDao();
    dao.open(pu);
  }

  @Override
  public Login rechercherLogin(String nom) {
    Login login = dao.getSingleResult(Login.class, "nom", nom);
    if (login != null) {
      dao.detach(login);
    }
    return login;
  }

  @Override
  public Login ajouterLogin(Login login) {
    if (dao.create(login) == 1) {
      dao.detach(login);
    }
    return login;
  }

  @Override
  public List<EtatCivil> chargerEtatsCivils() {
    List<EtatCivil> etatsCivils = dao.getList(EtatCivil.class, "abrev");
    EtatCivil ec = new EtatCivil();
    ec.setPkEtatCivil(-1);
    ec.setAbrev("tous");
    ec.setNom("Tous les états civils");
    etatsCivils.add(ec);
    return etatsCivils;
  }

  @Override
  public List<Canton> chargerCantons() {
    List<Canton> cantons = dao.getList(Canton.class, "abrev");
    Canton canton = new Canton();
    canton.setPkCanton(-1);
    canton.setAbrev("CH");
    canton.setNom("Suisse");
    cantons.add(canton);
    return cantons;
  }

  @Override
  public List<Parti> chargerPartis() {
    List<Parti> partis = dao.getList(Parti.class, "abrev");
    Parti parti = new Parti();
    parti.setPkParti(-1);
    parti.setAbrev("tous");
    parti.setNom("Tous les partis");
    partis.add(parti);
    return partis;
  }

  @Override
  public List<Conseil> chargerConseils() {
    List<Conseil> conseils = dao.getList(Conseil.class, "abrev");
    Conseil conseil = new Conseil();
    conseil.setPkConseil(-1);
    conseil.setAbrev("tous");
    conseil.setNom("Tous les conseils");
    conseils.add(conseil);
    return conseils;
  }

  @Override
  public List<Groupe> chargerGroupes() {
    List<Groupe> groupes = dao.getList(Groupe.class, "abrev");
    Groupe groupe = new Groupe();
    groupe.setPkGroupe(-1);
    groupe.setAbrev("tous");
    groupe.setNom("Tous les groupes");
    groupes.add(groupe);
    return groupes;
  }

  @Override
  public List<Conseiller> chargerConseillers(String canton, String conseil, String parti, boolean actuels) {
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
    if (actuels) {
      search.addFilterEqual("c.actif", true);
      search.addFilterIsNull("a.dateSortie");
    }
    search.addSortFields("c.nom", "c.prenom");
    List<Conseiller> conseillers = dao.getList(search);
    return conseillers;
  }

  @Override
  public List<Conseiller> chargerConseillers(String nom, boolean actuels) {
    String jpql = "SELECT distinct c FROM Conseiller c JOIN c.activite a WHERE a.conseiller=c";
    Search2 search = new Search2(jpql);
    List<Conseiller> conseillers = new ArrayList<>();
    if (!nom.isEmpty()) {
      search.addFilterLike("c.nom", nom + "%");
      if (actuels) {
        search.addFilterEqual("c.actif", true);
        search.addFilterIsNull("a.dateSortie");
      }
      search.addSortFields("c.nom", "c.prenom");
      conseillers = dao.getList(search);
    }
    return conseillers;
  }

  @Override
  public boolean bdOuverte() {
    return dao.isOpen();
  }

  @Override
  public boolean fermerBD() {
    dao.close();
    return !dao.isOpen();
  }

}
