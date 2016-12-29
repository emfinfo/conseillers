package controllers;

import controllers.actions.BeforeAfterAction;
import helpers.Utils;
import java.util.List;
import javax.inject.Inject;
import models.Canton;
import models.Conseil;
import models.Conseiller;
import models.Parti;
import play.Configuration;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.*;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import workers.DbWorkerAPI;
import workers.DbWorkerFactory;

/**
 * Contrôleur pour gérer les appels REST sur les conseillers nationaux.
 *
 * @author jcstritt
 */
public class ConseillerCtrl extends Controller {
  private DbWorkerAPI dbWrk;

  @Inject
  public ConseillerCtrl(Configuration configuration) {
    this.dbWrk = DbWorkerFactory.getInstance().getDbWorker();

    // on prepare un message pour le fichier de log
    String appName = configuration.getString("application.name");
    String appVersion = configuration.getString("application.version");
    Logger.info(
      appName + " " + appVersion
      + " is running on Play " + play.core.PlayVersion.current()
      + " with Java " + System.getProperty("java.version")
      + " (" + System.getProperty("sun.arch.data.model") + " bits)");

  }

  private Result logError(Exception e) {
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
   * Renvoyer une liste des conseils (CE, CF, CN).
   */
  @Transactional
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
   * Renvoyer une liste des partis de Suisse.
   */
  @Transactional
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
   * Renvoyer une liste de conseillers éventuellement filtrée.
   */
  @Transactional
  @With(BeforeAfterAction.class)
  public Result chargerConseillers(String fmt, String canton, String conseil, String parti, String actuels) {
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

    // on récupère la liste des conseillers (filtrée ou non)
    List<Conseiller> conseillers = dbWrk.chargerConseillers(filtreCanton, filtreConseil, filtreParti, filtreActuels);

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

    return httpResult;
  }

}
