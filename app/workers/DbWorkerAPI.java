package workers;

import java.util.List;
import models.Canton;
import models.Conseil;
import models.Conseiller;
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

  List<Canton> chargerCantons();
  List<Conseil> chargerConseils();
  List<Parti> chargerPartis();

  List<Conseiller> chargerConseillers(String canton, String conseil, String parti, boolean actuels);
  List<Conseiller> chargerConseillers(String nom, boolean actuels);

  boolean bdOuverte();
  boolean fermerBD();

}
