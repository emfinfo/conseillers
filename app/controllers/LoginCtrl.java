package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import controllers.actions.BeforeAfterAction;
import controllers.helpers.BooleanResult;
import controllers.helpers.Utils;
import models.Login;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.*;
import static play.mvc.Controller.request;
import session.SessionManager;
import views.html.index;
import workers.DbWorker;
import workers.DbWorkerAPI;
import static play.mvc.Results.ok;

/**
 * Contrôleur pour gérer les logins.
 *
 * @author jcstritt
 */
public class LoginCtrl extends Controller {

  public Result index() {
    return ok(index.render("Vous devez vous loguer !"));
  }

  public Result unauthorizedAccess() {
    return ok(index.render("Accès non autorisé !"));
  }

  @With(BeforeAfterAction.class)
  @Transactional
  public Result login(String nom, String motDePasse) {

    // on exécute la requête demandée (recherche de l'utilisateur)
    DbWorkerAPI wrk = new DbWorker(JPA.em());
    Login login = wrk.rechercherLogin(nom);

    // on essaye d'ouvrir la session
    SessionManager.clearSession();
    if (!SessionManager.createSession(nom, motDePasse, "", login)) {
      login = new Login();
    }

    // on retourne le résultat
    return Utils.toJson(login);
  }

  @With(BeforeAfterAction.class)
  public Result logout() {
    SessionManager.clearSession();
    return Utils.toJson("ok", !SessionManager.isSessionOpen());
  }

  @With(BeforeAfterAction.class)
  public Result sessionStatus() {
    return Utils.toJson("open", SessionManager.isSessionOpen());
  }

  @With(BeforeAfterAction.class)
  @Transactional
  public Result createLogin() {
    boolean ok = false;

    // on stocke dans un objet Login
    Login login = Utils.toObject(request(), new TypeReference<Login>() {
    });
    login.setPkLogin(1);

    // si le loginName n'existe pas, on sauve dans la DB
    DbWorkerAPI wrk = new DbWorker(JPA.em());
    Login unLogin = wrk.rechercherLogin(login.getNom());
    if (unLogin == null) {
      unLogin = wrk.ajouterLogin(login);
      ok = unLogin != null && unLogin.getPkLogin() > 0;
    }
    BooleanResult booleanResult = new BooleanResult(ok, login.getNom());
    return Utils.toJson(booleanResult);
  }

}
