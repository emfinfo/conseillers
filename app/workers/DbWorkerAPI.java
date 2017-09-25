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

  /**
   * Rechercher un objet "Login" d'après un nom d'utilisateur fourni.
   * 
   * @param nom une identification de l'utilisateur
   * @return un objet "Login"
   */
  Login rechercherLogin(String nom);
  
  /**
   * Ajouter un nouvel utilisateur avec un objet Login initialisé.
   * 
   * @param login un objet Login initialisé
   * @return un nouvel objet Login avec la PK (si en autoincrément)
   */
  Login ajouterLogin(Login login);

  
  
  /**
   * Charger une liste globale d'états civils.
   * 
   * @return une liste d'états-civils
   */
  List<EtatCivil> chargerEtatsCivils();
  
  /**
   * Charger une liste globale des cantons.
   * 
   * @return une liste de cantons
   */
  List<Canton> chargerCantons();
  
  /**
   * Charger une liste globale de partis.
   * 
   * @return une liste de partis 
   */
  List<Parti> chargerPartis();
  
  /**
   * Charger une liste globale de conseils (CF, CN, CE, ...).
   * 
   * @return une liste de conseils
   */
  List<Conseil> chargerConseils();
  
  /**
   * Charger une liste globale de groupes parlementaires.
   * 
   * @return une liste de groupes parlementaires
   */
  List<Groupe> chargerGroupes();
  
  /**
   * Charger une liste de conseillers filtrée d'après différents critères.
   * 
   * @param canton un ID de canton ou "CH"
   * @param conseil un ID de conseil ou "tous"
   * @param parti un ID de parti ou "tous"
   * @param actif true si on désire uniquement des conseillers en activité
   * 
   * @return une liste filtrée de conseillers
   */   
  List<Conseiller> chargerConseillers(String canton, String conseil, String parti, boolean actif);
  
  /**
   * Charge une liste de conseillers filtrée d'après une partie du nom et
   * une indication booléenne pour les conseillers en fonction.
   * 
   * @param nom une partie de nom pour filtrer 1 ou plusieurs conseillers
   * @param actif true si on désire uniquement des conseillers en activité
   * 
   * @return une liste filtrée de conseillers
   */
  List<Conseiller> chargerConseillers(String nom, boolean actif);

  
  
  /**
   * Permet de connaitre l'état "ouvert" de la base de données.
   * 
   * @return true si la base de données est ouverte
   */
  boolean bdOuverte();
  
  /**
   * Fermeture de la base de données.<br> 
   * Utile pour un contrôleur avant de quitter l'application.
   * 
   * @return true si la base de données est fermée
   */
  boolean fermerBd();

}
