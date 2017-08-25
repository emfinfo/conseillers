package workers;

import java.util.List;
import models.Canton;
import models.Conseil;
import models.Conseiller;
import models.EtatCivil;
import models.Groupe;
import models.Login;
import models.Parti;

/**
 * Interface définissant tous les services métiers vers la BD.
 *
 * @author Jean-Claude Stritt
 */
public interface DbWorkerAPI {

  Login rechercherLogin(String nom);
  Login ajouterLogin(Login login);

  List<EtatCivil> chargerEtatsCivils();
  List<Canton> chargerCantons();
  List<Parti> chargerPartis();
  List<Conseil> chargerConseils();
  List<Groupe> chargerGroupes();

  List<Conseiller> chargerConseillers(String canton, String conseil, String parti, boolean actuels);
  List<Conseiller> chargerConseillers(String nom, boolean actuels);

  boolean bdOuverte();
  boolean fermerBd();

}
