package controllers;

import controllers.actions.BeforeAfterAction;
import helpers.Utils;
import java.util.List;
import javax.inject.Inject;
import models.Canton;
import models.Conseil;
import models.Conseiller;
import models.EtatCivil;
import models.Groupe;
import models.Parti;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.*;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import session.MySecurityCtrl;
import workers.DbWorkerAPI;
import workers.DbWorkerFactory;

/**
 * Contrôleur pour gérer les appels REST sur les conseillers nationaux.
 *
 * @author jcstritt
 */
@Security.Authenticated(MySecurityCtrl.class)
public class ConseillerCtrl extends Controller {
  private DbWorkerAPI dbWrk;

  @Inject
  public ConseillerCtrl(DbWorkerFactory factory) {
    this.dbWrk = factory.getDbWorker();
  }

  /**
   * Affiche une exception dams le fichier de log et retourne un objet HTTP
   * de type "bad request".
   *
   * @param e un objet exception
   * @return un résultat HTTP
   */
  private Result logError(Exception e) {
    String msg = "Probleme avec la BD : \n" + e.getMessage();
    Logger.error(msg);
    Result httpResult = badRequest(msg);
    return httpResult;
  }

  /**
   * Renvoyer une liste des états civils.
   */
  @Transactional(readOnly=true)
  @With(BeforeAfterAction.class)
  public Result chargerEtatsCivils(String fmt) {
    Result httpResult;

    // on récupère la liste des cantons
    List<EtatCivil> ec = dbWrk.chargerEtatsCivils();

    // on fait le rendu en xml, json ou html
    try {
      switch (fmt.toUpperCase()) {
        case "XML":
          httpResult = ok(views.xml.etatscivils.render(ec)).as("application/xml");
          break;
        case "JSON":
          httpResult = Utils.toJson(ec);
          break;
        default:
          httpResult = ok(views.html.etatscivils.render(ec));
          break;
      }
    } catch (Exception e) {
      httpResult = logError(e);
    }
    return httpResult;
  }

  /**
   * Renvoyer une liste des cantons.
   */
  @Transactional(readOnly=true)
  @With(BeforeAfterAction.class)
  public Result chargerCantons(String fmt) {
    Result httpResult;

    // on récupère la liste des cantons
    List<Canton> cantons = dbWrk.chargerCantons();

    // on fait le rendu en xml, json ou html
    try {
      switch (fmt.toUpperCase()) {
        case "XML":
          httpResult = ok(views.xml.cantons.render(cantons)).as("application/xml");
          break;
        case "JSON":
          httpResult = Utils.toJson(cantons);
          break;
        default:
          httpResult = ok(views.html.cantons.render(cantons));
          break;
      }
    } catch (Exception e) {
      httpResult = logError(e);
    }
    return httpResult;
  }

  /**
   * Renvoyer une liste des partis de Suisse.
   */
  @Transactional(readOnly=true)
  @With(BeforeAfterAction.class)
  public Result chargerPartis(String fmt) {
    Result httpResult;

    // on récupère la liste des partis
    List<Parti> partis = dbWrk.chargerPartis();

    // on fait le rendu en xml, json ou html
    try {
      switch (fmt.toUpperCase()) {
        case "XML":
          httpResult = ok(views.xml.partis.render(partis)).as("application/xml");
          break;
        case "JSON":
          httpResult = Utils.toJson(partis);
          break;
        default:
          httpResult = ok(views.html.partis.render(partis));
          break;
      }
    } catch (Exception e) {
      httpResult = logError(e);
    }
    return httpResult;
  }

  /**
   * Renvoyer une liste de conseils (CE, CF, CN).
   */
  @Transactional(readOnly=true)
  @With(BeforeAfterAction.class)
  public Result chargerConseils(String fmt) {
    Result httpResult;

    // on récupère la liste des conseils
    List<Conseil> conseils = dbWrk.chargerConseils();

    // on fait le rendu en xml, json ou html
    try {
      switch (fmt.toUpperCase()) {
        case "XML":
          httpResult = ok(views.xml.conseils.render(conseils)).as("application/xml");
          break;
        case "JSON":
          httpResult = Utils.toJson(conseils);
          break;
        default:
          httpResult = ok(views.html.conseils.render(conseils));
          break;
      }
    } catch (Exception e) {
      httpResult = logError(e);
    }
    return httpResult;
  }

  /**
   * Renvoyer une liste de groupes parlementaires.
   */
  @Transactional(readOnly=true)
  @With(BeforeAfterAction.class)
  public Result chargerGroupes(String fmt) {
    Result httpResult;

    // on récupère la liste des conseils
    List<Groupe> groupes = dbWrk.chargerGroupes();

    // on fait le rendu en xml, json ou html
    try {
      switch (fmt.toUpperCase()) {
        case "XML":
          httpResult = ok(views.xml.groupes.render(groupes)).as("application/xml");
          break;
        case "JSON":
          httpResult = Utils.toJson(groupes);
          break;
        default:
          httpResult = ok(views.html.groupes.render(groupes));
          break;
      }
    } catch (Exception e) {
      httpResult = logError(e);
    }
    return httpResult;
  }

  /**
   * Renvoyer une liste filtrée de conseillers.
   */
  @Transactional(readOnly=true)
  @With(BeforeAfterAction.class)
  public Result chargerConseillers(String fmt, String canton, String conseil, String parti, String actif) {
    Result httpResult;
//    DateTimeLib.chronoReset();

    // on traite le cas du filtre "canton"
    String filtreCanton = canton;
    if (filtreCanton.equalsIgnoreCase("suisse") || canton.equalsIgnoreCase("ch")) {
      filtreCanton = "";
    }

    // on traite le cas du filtre "conseil"
    String filtreConseil = conseil;
    if (filtreConseil.equalsIgnoreCase("tous")) {
      filtreConseil = "";
    }

    // on traite le cas du filtre "parti"
    String filtreParti = parti;
    if (filtreParti.equalsIgnoreCase("tous")) {
      filtreParti = "";
    }

    // on récupère le boolean "en activité"
    boolean filtreActif = Boolean.parseBoolean(actif);

    // on récupère la liste des conseillers (filtrée ou non)
    List<Conseiller> conseillers = dbWrk.chargerConseillers(filtreCanton, filtreConseil, filtreParti, filtreActif);
//    System.out.println("A-obj: " + hashCode() + ", time: " + DateTimeLib.chronoStringElapsedTime());
//    DateTimeLib.chronoReset();

    // on fait le rendu en xml, json ou html)
    try {
      switch (fmt.toUpperCase()) {
        case "XML":
          httpResult = ok(views.xml.conseillers.render(conseillers)).as("application/xml");
          break;
        case "JSON":
          httpResult = Utils.toJson(conseillers);
          break;
        default:
          httpResult = ok(views.html.conseillers.render(canton.toUpperCase(), conseillers));
          break;
      }
    } catch (Exception e) {
      httpResult = logError(e);
    }
//    System.out.println("B-obj: " + hashCode() + ", time: " + DateTimeLib.chronoStringElapsedTime());

    return httpResult;
  }

}
