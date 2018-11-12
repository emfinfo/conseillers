package controllers;

import com.google.inject.Inject;
import helpers.Utils;
import java.util.List;
import models.Canton;
import models.Conseil;
import models.EtatCivil;
import models.Groupe;
import models.Parti;
import play.db.jpa.Transactional;
import play.mvc.*;
import static play.mvc.Results.ok;
import session.ForAllSecurityCtrl;
import workers.AnnexeWrk;

/**
 * Contrôleur pour gérer les appels REST sur les conseillers nationaux.
 *
 * @author jcstritt
 */
@Security.Authenticated(ForAllSecurityCtrl.class)
public class AnnexeCtrl extends Controller {
  private final AnnexeWrk annWrk;

  @Inject
  public AnnexeCtrl(AnnexeWrk annWrk) {
    this.annWrk = annWrk;
  }

  /**
   * Renvoyer une liste des états civils.
   */
  @Transactional(readOnly=true)
  public Result chargerEtatsCivils(String fmt) {
    Result httpResult;

    // on récupère la liste des cantons
    List<EtatCivil> ec = annWrk.chargerEtatsCivils();

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
    } catch (Exception ex) {
      httpResult = Utils.logError(ex);
    }
    return httpResult;
  }

  /**
   * Renvoyer une liste des cantons.
   */
  @Transactional(readOnly=true)
  public Result chargerCantons(String fmt) {
    Result httpResult;

    // on récupère la liste des cantons
    List<Canton> cantons = annWrk.chargerCantons();

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
    } catch (Exception ex) {
      httpResult = Utils.logError(ex);
    }
    return httpResult;
  }

  /**
   * Renvoyer une liste des partis de Suisse.
   */
  @Transactional(readOnly=true)
  public Result chargerPartis(String fmt) {
    Result httpResult;

    // on récupère la liste des partis
    List<Parti> partis = annWrk.chargerPartis();

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
    } catch (Exception ex) {
      httpResult = Utils.logError(ex);
    }
    return httpResult;
  }

  /**
   * Renvoyer une liste de conseils (CE, CF, CN).
   */
  @Transactional(readOnly=true)
  public Result chargerConseils(String fmt) {
    Result httpResult;

    // on récupère la liste des conseils
    List<Conseil> conseils = annWrk.chargerConseils();

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
    } catch (Exception ex) {
      httpResult = Utils.logError(ex);
    }
    return httpResult;
  }

  /**
   * Renvoyer une liste de groupes parlementaires.
   */
  @Transactional(readOnly=true)
  public Result chargerGroupes(String fmt) {
    Result httpResult;

    // on récupère la liste des conseils
    List<Groupe> groupes = annWrk.chargerGroupes();

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
    } catch (Exception ex) {
      httpResult = Utils.logError(ex);
    }
    return httpResult;
  }

}
