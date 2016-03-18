package controllers;

import controllers.actions.BeforeAfterAction;
import controllers.helpers.Utils;
import java.util.List;
import models.Canton;
import models.Conseil;
import models.Conseiller;
import models.Parti;
import play.Logger;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import play.mvc.With;
import workers.WorkerDb;
import workers.WorkerDbAPI;

/**
 * Contrôleur pour gérer les appels REST sur les conseillers nationaux.
 * 
 * @author jcstritt
 */
public class ConseillerCtrl extends Controller {

  private static WorkerDbAPI wrk;

  private static Result logError(Exception e) {
    String msg = "Probleme avec la BD : \n" + e.getMessage();
    Logger.error(msg);
    Result httpResult = badRequest(msg);
    return httpResult;
  }

  /**
   * Renvoyer une liste des cantons.
   */
  @Transactional
  @With(BeforeAfterAction.class)
  public static Result chargerCantons(String fmt) {
    Result httpResult;

    // on transforme le paramètre en majuscules
    fmt = fmt.toUpperCase();

    // on crée un worker avec sa sous-couche dao
    wrk = new WorkerDb(JPA.em());

    // on récupère la liste des cantons
    List<Canton> cantons = wrk.chargerCantons();

    // on fait le rendu en xml, json ou html
    try {
      if (fmt.equals("XML")) {
        httpResult = ok(views.xml.cantons.render(cantons)).as("application/xml");
      } else if (fmt.equals("JSON")) {
        httpResult = Utils.toJson(cantons);
      } else {
        httpResult = ok(views.html.cantons.render(cantons));
      }
    } catch (Exception e) {
      httpResult = logError(e);
    }
    return httpResult;
  }

  /**
   * Renvoyer une liste des conseils (CE, CF, CN).
   */
  @Transactional
  @With(BeforeAfterAction.class)
  public static Result chargerConseils(String fmt) {
    Result httpResult;

    // on transforme le paramètre en majuscules
    fmt = fmt.toUpperCase();

    // on crée un worker avec sa sous-couche dao
    wrk = new WorkerDb(JPA.em());

    // on récupère la liste des conseils
    List<Conseil> conseils = wrk.chargerConseils();

    // on fait le rendu en xml, json ou html
    try {
      switch (fmt) {
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
   * Renvoyer une liste des partis de Suisse.
   */
  @Transactional
  @With(BeforeAfterAction.class)
  public static Result chargerPartis(String fmt) {
    Result httpResult;

    // on transforme le paramètre en majuscules
    fmt = fmt.toUpperCase();

    // on crée un worker avec sa sous-couche dao
    wrk = new WorkerDb(JPA.em());

    // on récupère la liste des partis
    List<Parti> partis = wrk.chargerPartis();

    // on fait le rendu en xml, json ou html
    try {
      switch (fmt) {
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
   * Renvoyer une liste de conseillers éventuellement filtrée.
   */
  @Transactional
  @With(BeforeAfterAction.class)
  public static Result chargerConseillers(String fmt, String canton, String conseil, String parti, String actuels) {
    Result httpResult;

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

    // on récupère le boolean "actuels"
    boolean filtreActuels = Boolean.parseBoolean(actuels);    
    
    // on transforme les paramètres en majuscules
    canton = canton.toUpperCase();
    fmt = fmt.toUpperCase(); // + ((fmt.length() == 3) ? " " : "");

    // on crée un worker avec sa sous-couche dao
    wrk = new WorkerDb(JPA.em());

    // on récupère la liste des conseillers (filtrée ou non)
    List<Conseiller> conseillers = wrk.chargerConseillers(filtreCanton, filtreConseil, filtreParti, filtreActuels);

    // on fait le rendu en xml, json ou html)
    try {
      switch (fmt) {
        case "XML":
          httpResult = ok(views.xml.conseillers.render(conseillers)).as("application/xml");
          break;
        case "JSON":
          httpResult = Utils.toJson(conseillers);
          break;
        default:
          httpResult = ok(views.html.conseillers.render(canton, conseillers));
          break;
      }
    } catch (Exception e) {
      httpResult = logError(e);
    }

    return httpResult;
  }

}
