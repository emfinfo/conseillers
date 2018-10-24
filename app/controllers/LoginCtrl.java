package controllers;

import ch.emf.cypher.AesUtil;
import ch.emf.dao.helpers.Logger;
import ch.emf.helpers.Convert;
import ch.emf.helpers.Generate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import helpers.BooleanResult;
import helpers.Utils;
import java.util.Date;
import models.Login;
import play.db.jpa.Transactional;
import play.mvc.*;
import static play.mvc.Controller.request;
import session.SessionManager;
import workers.DbWorkerItf;

/**
 * Contrôleur pour gérer les logins.
 *
 * @author jcstritt
 */
public class LoginCtrl extends Controller {
  private DbWorkerItf dbWrk;

  @Inject
  public LoginCtrl(DbWorkerItf dbWrk) {
    this.dbWrk = dbWrk;
  }
  
//  public Result index() {
//    return ok(index.render("Vous devez vous loguer !"));
//  }
//
//  public Result unauthorizedAccess() {
//    return ok(index.render("Accès non autorisé !"));
//  }
//  private DbWorkerItf getDbWorker() {
//    Injector inj = Guice.createInjector();
//    return inj.getInstance(DbWorkerItf.class);
//  }

  // méthode pour créer un objet de login pour les données reçus
  private Login extractHttpLogin(String data) {
    Login login = new Login();
//    System.out.println("data: " + data + ", len=" + data.length());

    // on ne traite que si les données contiennent plus de 64 caractères
    if (data.length() > 64) {

      // récupération du sel et du vecteur d'initialisation
      String salt = data.substring(0, 32);
      String iv = data.substring(32, 64);
//      System.out.println("salt: " + salt + ", len=" + salt.length());
//      System.out.println("iv:   " + iv + ", len=" + iv.length());

      // génère la même clé que le client doit générer
      String passPhrase = Generate.passPhrase();
//      System.out.println("passPhrase: "+passPhrase+" ("+new String(Convert.toBase64(passPhrase))+")");

      // crée l'utilitaire pour le décryptage
      AesUtil aesUtil = new AesUtil(128, 1000);

      // convertit les données hexadécimales en Base64 (nécessaire pour le décryptage)
      byte[] bytes = Convert.toHex(data.substring(64));
      String b64 = Convert.toBase64(bytes);

      // décrypte les données
      String decrypted = aesUtil.decrypt(salt, iv, passPhrase, b64);
//      System.out.println("decrypted: " + decrypted);

      // extraction des données du login
      String t[] = decrypted.split("/");
      if (t.length >= 4) {
        login.setNom(t[0]);
        login.setDomaine(t[1]);
        login.setMotDePasse(t[2]);
        login.setTimestamp(new Date(Long.parseLong(t[3])));
        login.setProfil((t.length >= 5) ? t[4] : null);
        login.setEmail((t.length >= 6) ? t[5] : null);
        login.setInitiales((t.length >= 7) ? t[6] : null);
        login.setLangue((t.length >= 8) ? t[7] : null);
      }
    }

    return login;
  }


  @Transactional
  public Result login(String data) {
//    Map<String, String> headers = response().getHeaders();
//    for (Map.Entry<String, String> entry : headers.entrySet()) {
//      System.out.println("  - " + entry.getKey() + ": " + entry.getValue());
//    }

    // extraction des données
    Login httpLogin = extractHttpLogin(data);
    Logger.debug(this.getClass(), "http: " + httpLogin.toString2());

    // on recherche l'utilisateur+domaine spécifiés
    Login dbLogin = dbWrk.rechercherLogin(httpLogin.getNom(), httpLogin.getDomaine());
    Logger.debug(this.getClass(), "db: " + ((dbLogin != null) ? dbLogin.toString2() : "?"));

    // on supprime la session en cours
    SessionManager.clear();

    // si le login est correct on modifie le login pour le timestamp
    if (SessionManager.create(httpLogin, dbLogin)) {
      int nb = dbWrk.modifierLogin(dbLogin);
//      login.setMotDePasse("");
    } else {
      dbLogin = new Login();
    }

    // on retourne le résultat
    return Utils.toJson(dbLogin);
  }


  public Result logout() {
    SessionManager.clear();
    return Utils.toJson("open", SessionManager.isOpen());
  }


  public Result status() {
    return Utils.toJson("open", SessionManager.isOpen());
  }


  @Transactional
  public Result createLogin() {
    boolean ok = false;

    // on récupère les infos du json
    JsonNode json = request().body().asJson();
    String data = json.get("data").textValue();

    // extraction des données
    Login httpLogin = extractHttpLogin(data);

    // on crée une empreinte du mot de passe
    String dbSalt = Generate.randomHex(32);
    String dbHash = Generate.hash(httpLogin.getMotDePasse()+ dbSalt, "SHA-256");
    httpLogin.setMotDePasse(dbHash + dbSalt);

    // on stocke dans un objet Login
    Login login = Utils.toObject(request(), new TypeReference<Login>() {});
    login.setPkLogin(1);

    // si le compte n'existe pas, on le sauve dans la BD
    Login dbLogin = dbWrk.rechercherLogin(httpLogin.getNom(), httpLogin.getDomaine());
    if (dbLogin == null) {
      dbLogin = dbWrk.ajouterLogin(httpLogin);
      ok = dbLogin != null && dbLogin.getPkLogin() > 0;
    }

    // on retourne le résultat de la création du compte
    BooleanResult booleanResult = new BooleanResult(ok, login.getNom());
    return Utils.toJson(booleanResult);
  }

}
