package controllers;

import ch.emf.play.helpers.DatabaseExecutionContext;
import ch.emf.play.helpers.Utils;
import ch.emf.play.helpers.SessionUtils;
import com.google.inject.Inject;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import javax.persistence.EntityManager;
import models.Login;
import play.db.jpa.JPAApi;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import workers.LoginWrk;

/**
 * Contrôleur pour gérer les logins.
 *
 * @author jcstritt
 */
public class LoginCtrl extends Controller {
  private final HttpExecutionContext httpExCtx;
  private final DatabaseExecutionContext dbExCtx;
  private final JPAApi jpaApi;
  private final LoginWrk loginWrk;
  
  @Inject
  public LoginCtrl(HttpExecutionContext httpExCtx, DatabaseExecutionContext dbExCtx, JPAApi jpaApi, LoginWrk loginWrk) {
    this.httpExCtx = httpExCtx;
    this.dbExCtx = dbExCtx;    
    this.jpaApi = jpaApi;
    this.loginWrk = loginWrk;
  }
  
  private <T> T wrap(Function<EntityManager, T> function) {
    return jpaApi.withTransaction(function);
  }  

  public CompletionStage<Result> login(Http.Request req, String data) {
 
    // opération asynchrone
    return CompletableFuture.supplyAsync(() -> wrap(em -> {
      
      // mémorisation de l'entity manager
      loginWrk.memoriser(em);

      // extraction des données client (provenant du navigateur)
      Login clientLogin = loginWrk.extraire(data);

      // on recherche l'utilisateur+domaine spécifiés dans la BD
      Login dbLogin = loginWrk.rechercher(clientLogin.getNom(), clientLogin.getDomaine());

      // l'objet doit exister 
      boolean ok = dbLogin != null;
      if (ok) {
        
        // si le mot de passe est dans la table, on compare les empreintes
        if (dbLogin.getMotDePasse() != null) {
          ok = loginWrk.comparer(clientLogin, dbLogin);
        } else { // autrement on pourrait tester avec AD
          ok = false;
          // ok = authenticateOnActiveDirectory(
          //  httpLogin.getNom(), httpLogin.getDomaine(), httpLogin.getMotDePasse());
        }

        // si ok, on met à jour dans la bd l'objet dbLogin à cause du timestamp
        ok = ok && loginWrk.modifier(dbLogin) == 1;
      }
      
      // en cas de problème, on retourne un objet Login vide      
      if (!ok) {
        dbLogin = new Login();
      }
      return dbLogin;

    }), dbExCtx).thenApplyAsync(dbLogin -> {
      
      // on sauve les infos de l'utilisateur dans la session
      if (dbLogin.getPk() > 0) {
        Map<String, String> map = SessionUtils.getUserInfo(dbLogin.getPk(), dbLogin.getNom(), dbLogin.getProfil(), 0);
        return Utils.toJson(dbLogin).withSession(map);
      } else {
        return Utils.toJson(dbLogin).withNewSession();
      }
      
    }, httpExCtx.current());
  }
  
  public CompletionStage<Result> createLogin(Http.Request req) {

    // opération asynchrone
    return CompletableFuture.supplyAsync(() -> wrap(em -> {

      // mémorisation de l'entity manager
      loginWrk.memoriser(em);

      // objet login par défaut pour le résultat
      Login dbLogin = new Login();

      // infos de l'application cliente
      String data = req.body().asText();
      Login clientLogin = loginWrk.extraire(data);

      // on essaye de créer le compte
      if (!clientLogin.getNom().isEmpty()) {
        dbLogin = loginWrk.creerCompte(clientLogin);
      }
      return dbLogin;
      
    }), dbExCtx).thenApplyAsync(dbLogin -> {

      // retourner le résultat
      return Utils.toJson(dbLogin);
    }, httpExCtx.current());
  }
  
//  @With(BeforeAfterAction.class)
  public Result status(Http.Request req) {
    return Utils.toJson("open", SessionUtils.isOpen(req));
  }

  public Result logout(Http.Request req) {
    return Utils.toJson("open", false).withNewSession();
  }
  
  
}
