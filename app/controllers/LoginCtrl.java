package controllers;

import ch.emf.play.helpers.Utils;
import ch.emf.play.session.SessionUtils;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import models.Login;
import play.db.jpa.Transactional;
import play.mvc.*;
import static play.mvc.Controller.request;
import workers.LoginWrk;

/**
 * Contrôleur pour gérer les logins.
 *
 * @author jcstritt
 */
public class LoginCtrl extends Controller {
  private final int msTimeout;
  private final LoginWrk loginWrk;

  @Inject
  public LoginCtrl(Config config, LoginWrk loginWrk) {
    this.msTimeout = config.getInt("application.msTimeout");
    this.loginWrk = loginWrk;
  }

  private Login createSession(Login clientLogin, Login dbLogin) {
    boolean ok = dbLogin != null;

    // à chaque tentative de login on supprime d'abord la session
    session().clear();

    // teste si l'utilisateur a été trouvé auparavant
    if (ok) {

      // si password dans la table ...
      if (dbLogin.getMotDePasse() != null) {
        ok = loginWrk.comparer(clientLogin, dbLogin);
      } else { // autrement on pourrait tester avec AD
        // ok = authenticateOnActiveDirectory(
        //  httpLogin.getNom(), httpLogin.getDomaine(), httpLogin.getMotDePasse());
        ok = false;
      }
    }

    // enregistrement de la session si l'identification est correcte
    if (ok) {
      SessionUtils.saveUserInfo(dbLogin.getPk(), dbLogin.getNom(), dbLogin.getProfil(), 0);
      loginWrk.modifier(dbLogin); // modifie dans la BD pour le timestamp
    } else {
      dbLogin = new Login();
    }
    return dbLogin;
  }

  @Transactional
  public Result login(String data) {

    // extraction des données client
    Login clientLogin = loginWrk.extraire(data);

    // on recherche l'utilisateur+domaine spécifiés dans la BD
    Login dbLogin = loginWrk.rechercher(clientLogin.getNom(), clientLogin.getDomaine());

    // on essaye de créer la session
    dbLogin = createSession(clientLogin, dbLogin);

    // on retourne le résultat
    return Utils.toJson(dbLogin);
  }

  public Result logout() {
    SessionUtils.clear();
    return Utils.toJson("open", SessionUtils.isOpen(msTimeout));
  }

  public Result status() {
    return Utils.toJson("open", SessionUtils.isOpen(msTimeout));
  }

  @Transactional
  public Result createLogin() {

    // on récupère les infos de l'application cliente
//    JsonNode json = request().body().asJson();
//    String data = json.get("data").textValue();
    String data = request().body().asText();
    Login clientLogin = loginWrk.extraire(data);
//    Logger.info(clientLogin.toString2());

    // on essaye de créer le compte
    Login dbLogin = loginWrk.creerCompte(clientLogin);

    // on retourne le résultat de la création du compte
    return Utils.toJson(dbLogin);
  }

}
