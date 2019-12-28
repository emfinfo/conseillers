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
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import workers.AnnexeWrk;

/**
 * Contrôleur pour gérer les appels REST sur les demandes annexes aux
 * conseillers nationaux.
 *
 * @author jcstritt
 */
//@Security.Authenticated(PourTous.class)
public class AnnexeCtrl extends Controller {

  private final HttpExecutionContext httpExCtx;
  private final DatabaseExecutionContext dbExCtx;
  private final JPAApi jpaApi;
  private final AnnexeWrk annWrk;

  @Inject
  public AnnexeCtrl(HttpExecutionContext httpExCtx, DatabaseExecutionContext dbExCtx, JPAApi jpaApi, AnnexeWrk annWrk) {
    this.httpExCtx = httpExCtx;
    this.dbExCtx = dbExCtx;
    this.jpaApi = jpaApi;
    this.annWrk = annWrk;
  }

  private <T> T wrap(Function<EntityManager, T> function) {
    return jpaApi.withTransaction(function);
  }

  /**
   * Renvoyer une liste des états civils.
   *
   * @param fmt le format des données pour le retour
   * @return une liste des états-civils existants
   */
  public CompletionStage<Result> chargerEtatsCivils(String fmt) {

    // opération asynchrone
    return CompletableFuture.supplyAsync(() -> wrap(em -> {

      // mémoriser l'objet EntitytManager
      annWrk.memoriser(em);

      // récupérer la liste des états civils
      return annWrk.chargerEtatsCivils();

    }), dbExCtx).thenApplyAsync(etatsCivils -> {

      // faire le rendu en xml, json ou html
      Result httpResult;
      try {
        switch (fmt.toUpperCase()) {
          case "XML":
            httpResult = Results.ok(views.xml.etatscivils.render(etatsCivils)).as("application/xml");
            break;
          case "JSON":
            httpResult = Utils.toJson(etatsCivils);
            break;
          default:
            httpResult = Results.ok(views.html.etatscivils.render(etatsCivils));
            break;
        }
      } catch (Exception ex) {
        httpResult = Utils.logError(ex);
      }

      // retourner le résultat
      return httpResult;
    }, httpExCtx.current());

  }

  /**
   * Renvoyer une liste des cantons.
   *
   * @param fmt le format des données pour le retour
   * @return une liste de cantons existants
   */
  public CompletionStage<Result> chargerCantons(String fmt) {

    // opération asynchrone
    return CompletableFuture.supplyAsync(() -> wrap(em -> {

      // mémoriser l'objet EntitytManager
      annWrk.memoriser(em);

      // récupérer la liste des cantons
      return annWrk.chargerCantons();

    }), dbExCtx).thenApplyAsync(cantons -> {

      // faire le rendu en xml, json ou html
      Result httpResult;
      try {
        switch (fmt.toUpperCase()) {
          case "XML":
            httpResult = Results.ok(views.xml.cantons.render(cantons)).as("application/xml");
            break;
          case "JSON":
            httpResult = Utils.toJson(cantons);
            break;
          default:
            httpResult = Results.ok(views.html.cantons.render(cantons));
            break;
        }
      } catch (Exception ex) {
        httpResult = Utils.logError(ex);
      }

      // retourner le résultat
      return httpResult;
    }, httpExCtx.current());
  }

  /**
   * Renvoyer une liste des partis de Suisse.
   *
   * @param fmt le format des données pour le retour
   * @return une liste des partis existants
   */
  public CompletionStage<Result> chargerPartis(String fmt) {

    // opération asynchrone
    return CompletableFuture.supplyAsync(() -> wrap(em -> {

      // mémoriser l'objet EntitytManager
      annWrk.memoriser(em);

      // récupérer la liste des partis
      return annWrk.chargerPartis();

    }), dbExCtx).thenApplyAsync(partis -> {

      // faire le rendu en xml, json ou html
      Result httpResult;
      try {
        switch (fmt.toUpperCase()) {
          case "XML":
            httpResult = Results.ok(views.xml.partis.render(partis)).as("application/xml");
            break;
          case "JSON":
            httpResult = Utils.toJson(partis);
            break;
          default:
            httpResult = Results.ok(views.html.partis.render(partis));
            break;
        }
      } catch (Exception ex) {
        httpResult = Utils.logError(ex);
      }

      // retourner le résultat
      return httpResult;
    }, httpExCtx.current());
  }

  /**
   * Renvoyer une liste des conseils existants (CE, CF, CN).
   *
   * @param fmt le format des données pour le retour
   * @return une liste des conseils existants
   */
  public CompletionStage<Result> chargerConseils(String fmt) {

    // opération asynchrone
    return CompletableFuture.supplyAsync(() -> wrap(em -> {

      // mémoriser l'objet EntitytManager
      annWrk.memoriser(em);

      // récupérer la liste des conseils
      return annWrk.chargerConseils();

    }), dbExCtx).thenApplyAsync(conseils -> {

      // faire le rendu en xml, json ou html
      Result httpResult;
      try {
        switch (fmt.toUpperCase()) {
          case "XML":
            httpResult = Results.ok(views.xml.conseils.render(conseils)).as("application/xml");
            break;
          case "JSON":
            httpResult = Utils.toJson(conseils);
            break;
          default:
            httpResult = Results.ok(views.html.conseils.render(conseils));
            break;
        }
      } catch (Exception ex) {
        httpResult = Utils.logError(ex);
      }

      // retourner le résultat
      return httpResult;
    }, httpExCtx.current());
  }

  /**
   * Renvoyer une liste de groupes parlementaires.
   *
   * @param fmt le format des données pour le retour
   * @return une liste des groupes parlementaires existants
   */
  public CompletionStage<Result> chargerGroupes(String fmt) {

    // opération asynchrone
    return CompletableFuture.supplyAsync(() -> wrap(em -> {

      // mémoriser l'objet EntityManager
      annWrk.memoriser(em);

      // récupérer la liste des conseils
      return annWrk.chargerGroupes();

    }), dbExCtx).thenApplyAsync(groupes -> {

      // faire le rendu en xml, json ou html
      Result httpResult;
      try {
        switch (fmt.toUpperCase()) {
          case "XML":
            httpResult = Results.ok(views.xml.groupes.render(groupes)).as("application/xml");
            break;
          case "JSON":
            httpResult = Utils.toJson(groupes);
            break;
          default:
            httpResult = Results.ok(views.html.groupes.render(groupes));
            break;
        }
      } catch (Exception ex) {
        httpResult = Utils.logError(ex);
      }

      // retourner le résultat
      return httpResult;
    }, httpExCtx.current());
  }

}
