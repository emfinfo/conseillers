package controllers;

import com.google.inject.Inject;
import helpers.Utils;
import java.util.List;
import models.Conseiller;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.*;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import session.ForTeacherSecurityCtrl;
import workers.ConseillerWrk;

/**
 * Contrôleur pour gérer les appels REST sur les conseillers nationaux.
 *
 * @author jcstritt
 */
@Security.Authenticated(ForTeacherSecurityCtrl.class)
public class ConseillerCtrl extends Controller {
  private final ConseillerWrk consWrk;

  @Inject
  public ConseillerCtrl(ConseillerWrk consWrk) {
    this.consWrk = consWrk;
  }

  /**
   * Renvoyer une liste filtrée de conseillers.
   */
  @Transactional(readOnly=true)
  public Result chargerConseillers(String fmt, String canton, String conseil, String parti, String actif) {
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

    // on récupère le boolean "en activité"
    boolean filtreActif = Boolean.parseBoolean(actif);

    // on récupère la liste des conseillers (filtrée ou non)
    List<Conseiller> conseillers = consWrk.chargerConseillers(filtreCanton, filtreConseil, filtreParti, filtreActif);

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
    } catch (Exception ex) {
      Logger.error(ex.getLocalizedMessage());
      httpResult = badRequest(ex.getLocalizedMessage());
    }

    // on retourne le résultat
    return httpResult;
  }

}
