package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import controllers.actions.BeforeAfterAction;
import helpers.BooleanResult;
import helpers.Utils;
import javax.inject.Inject;
import models.Login;
import play.db.jpa.Transactional;
import play.mvc.*;
import session.SessionManager;
import views.html.index;
import workers.DbWorkerAPI;
import workers.DbWorkerFactory;

/**
 * Contrôleur pour gérer les logins.
 *
 * @author jcstritt
 */
public class LoginCtrl extends Controller {
  private DbWorkerAPI dbWrk;

  @Inject
  public LoginCtrl(DbWorkerFactory factory) {
    this.dbWrk = factory.getDbWorker();
  }

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
    Login login = dbWrk.rechercherLogin(nom);

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
  public Result status() {
    return Utils.toJson("open", SessionManager.isSessionOpen());
  }

  @With(BeforeAfterAction.class)
  @Transactional
  public Result createLogin() {
    boolean ok = false;

    // on stocke dans un objet Login
    Login login = Utils.toObject(request(), new TypeReference<Login>() {});
    login.setPkLogin(1);

    // si le loginName n'existe pas, on sauve dans la DB
    Login unLogin = dbWrk.rechercherLogin(login.getNom());
    if (unLogin == null) {
      unLogin = dbWrk.ajouterLogin(login);
      ok = unLogin != null && unLogin.getPkLogin() > 0;
    }
    BooleanResult booleanResult = new BooleanResult(ok, login.getNom());
    return Utils.toJson(booleanResult);
  }

}
