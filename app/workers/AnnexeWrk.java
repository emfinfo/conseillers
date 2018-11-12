package workers;

import ch.emf.dao.JpaDaoAPI;
import ch.emf.dao.play.DaoRepositoryItf;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import models.Canton;
import models.Conseil;
import models.EtatCivil;
import models.Groupe;
import models.Parti;

/**
 * Couche "métier" pour gérer les objets annexes contenus dans la base de données :<br>
 * - etats civils<br>
 * - cantons<br>
 * - partis<br>
 * - conseils<br>
 * - groupes parlementaires<br>
 * Utilise daolayer avec JPA comme sous-couche d'accès aux données.
 *
 * @author Jean-Claude Stritt
 */
@Singleton
public class AnnexeWrk {
  private final JpaDaoAPI dao;

  @Inject
  public AnnexeWrk(DaoRepositoryItf rep) {
    this.dao = rep.getDao();
  }

  public AnnexeWrk(JpaDaoAPI dao) {
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

}
