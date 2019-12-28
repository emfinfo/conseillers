package controllers;

import ch.emf.play.helpers.DatabaseExecutionContext;
import ch.emf.play.helpers.Utils;
import com.google.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import javax.persistence.EntityManager;
import play.db.jpa.JPAApi;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import workers.ConseillerWrk;

/**
 * Contrôleur pour gérer les appels REST sur les conseillers nationaux.
 *
 * @author jcstritt
 */
//@Security.Authenticated(PourProfs.class)
public class ConseillerCtrl extends Controller {
  
  private final HttpExecutionContext httpExCtx;
  private final DatabaseExecutionContext dbExCtx;
  private final JPAApi jpaApi;
  private final ConseillerWrk consWrk;

  @Inject
  public ConseillerCtrl(HttpExecutionContext httpExCtx, DatabaseExecutionContext dbExCtx, JPAApi jpaApi, ConseillerWrk consWrk) {
    this.httpExCtx = httpExCtx;
    this.dbExCtx = dbExCtx;
    this.jpaApi = jpaApi;
    this.consWrk = consWrk;
  }

  private <T> T wrap(Function<EntityManager, T> function) {
    return jpaApi.withTransaction(function);
  }

  /**
   * Renvoyer une liste filtrée de conseillers.
   *
   * @param fmt le format des données pour le retour
   * @param canton un canton pour un éventuel filtrage
   * @param conseil le type de conseil pour un éventuel filtrage
   * @param parti un parti pour un éventuel filtrage
   * @param actif TRUE pour le filtrages des conseillers actifs uniquement
   * @return une liste des conseillers recherchés
   */
  public CompletionStage<Result> chargerConseillers(String fmt, String canton, String conseil, String parti, String actif) {
 
    // opération asynchrone
    return CompletableFuture.supplyAsync(() -> wrap(em -> {

      // mémorisation de l'entity manager
      consWrk.memoriser(em);

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
      return consWrk.chargerConseillers(filtreCanton, filtreConseil, filtreParti, filtreActif);
      
    }), dbExCtx).thenApplyAsync(conseillers -> {
      
      // on fait le rendu en xml, json ou html)
      Result httpResult;
      try {
        switch (fmt.toUpperCase()) {
          case "XML":
            httpResult = Results.ok(views.xml.conseillers.render(conseillers)).as("application/xml");
            break;
          case "JSON":
            httpResult = Utils.toJson(conseillers);
            break;
          default:
            httpResult = Results.ok(views.html.conseillers.render(canton.toUpperCase(), conseillers));
            break;
        }
      } catch (Exception ex) {
        httpResult = Utils.logError(ex);
      }

      // on retourne le résultat
      return httpResult;
    }, httpExCtx.current());
  }

}
