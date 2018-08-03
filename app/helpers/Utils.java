package helpers;

import ch.jcsinfo.datetime.DateTimeLib;
import ch.jcsinfo.system.StackTracer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.Optional;
import play.Logger;
import play.libs.Json;
import play.mvc.Http.Context;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.mvc.Result;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.ok;
import session.SessionManager;

/**
 * Méthodes statiques d'aide pour les contrôleurs.
 *
 * @author jcstritt
 */
public class Utils {

  /**
   * Permet d'afficher des informations de log dans la console.
   *
   * @param startTime le temps du début de la requête
   * @param params    différents paramètres facultatifs.
   */
  public static void logInfo(long startTime, Object... params) {
    String threadId = String.valueOf(Thread.currentThread().getId());
    String userId = (SessionManager.isOpen()) ? "" + SessionManager.getUserId() : "?";
    String msg = DateTimeLib.dateToString(DateTimeLib.getNow(), "dd.MM.yy HH:mm:ss");
    msg += " - USER: " + userId + ", THREAD: " + threadId;
    msg += ", " + StackTracer.getParentMethod(-1);
    if (params.length > 0) {
      int i = 0;
      for (Object param : params) {
        if (i == 0) {
          msg += "(";
        } else {
          msg += ", ";
        }
        msg += param;
        i++;
      }
      if (i > 0) {
        msg += ")";
      }
    }
    if (startTime >= 0) {
      msg += ", " + (System.currentTimeMillis() - startTime) + " ms";
    }
    Logger.info(msg);
  }

  /**
   * Permet d'afficher des informations de log dans la console.
   *
   * @param ctx le contexte HTTP
   */
  public static void logInfo(Context ctx) {
    //String msg = DateTimeLib.dateToString(DateTimeLib.getDate(), "dd.MM.yy HH:mm:ss");

    // route
    String route = ctx.toString();
    route = route.substring(route.indexOf("(") + 1, route.indexOf(")"));
    if (route.contains("login/")) {
      route = route.substring(0, route.lastIndexOf("/"));
    }
    int p = route.indexOf("?_=");
    if (p >= 0) {
      route = route.substring(0, p);
    }
    if (!route.endsWith("/")) {
//      System.out.println("route: " + route);

//    msg += ", " + route;
      String msg = route;

      // user
//      Map<String, String> map = ctx.response().getHeaders();
//      for (Map.Entry<String, String> entry : map.entrySet()) {
//        System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
//      }
      if (SessionManager.isOpen()) {
//      msg += " (USER: " + SessionManager.getUserId() + ")";
        msg += " (" + SessionManager.getUserName() + ")";
      }

      // elapsed time
//    Map<String, String> headers = ctx.response().getHeaders();
//    for (Entry<String, String> e : headers.entrySet()) {
//      String key = e.getKey();
//      String value = e.getValue();
//    }
      String ts = ctx.response().getHeaders().get("logtimestamp");
      long startTime = (ts == null) ? System.currentTimeMillis() : Long.parseLong(ts);
      if (startTime >= 0) {
        msg += ", " + (System.currentTimeMillis() - startTime) + " ms";
      }
      Logger.info(msg);
    }
  }

  /**
   * Transforme un objet quelconque en JSON.
   *
   * @param object l'objet à serialiser en JSON
   * @return un résultat HTTP avec le JSON
   */
  public static Result toJson(Object object) {
    Result result;
    if (object != null) {
      result = ok(Json.toJson(object)).as("application/json");
    } else {
      result = internalServerError("NULL_OBJECT_ERROR");
    }
    return result;
  }

  /**
   * Transforme une propriété de type clé-valeur en JSON.
   *
   * @param key   la clé de la propriété
   * @param value la valeur de la propriété
   *
   * @return un résultat HTTP avec le JSON
   */
  public static Result toJson(String key, Object value) {
    ObjectNode jsonObj = Json.newObject();
    jsonObj.putPOJO(key, value);
    return ok(jsonObj).as("application/json");
  }

  /**
   * Transforme deux propriétés de type clé-valeur en JSON.
   *
   * @param key1   la clé de la propriété 1
   * @param value1 la valeur de la propriété 1
   * @param key2   la clé de la propriété 2
   * @param value2 la valeur de la propriété 2
   *
   * @return un résultat HTTP avec le JSON
   */
  public static Result toJson(String key1, Object value1, String key2, Object value2) {
    ObjectNode jsonObj = Json.newObject();
    jsonObj.putPOJO(key1, value1);
    jsonObj.putPOJO(key2, value2);
    return ok(jsonObj).as("application/json");
  }

  /**
   * Transform une valeur booléenne en propriété JSON.
   *
   * @param ok      la variable booléenne à transformer
   * @param message
   * @return
   */
  public static Result toJson(boolean ok, String message) {
    BooleanResult bResult = new BooleanResult(ok, message);
    return ok(Json.toJson(bResult)).as("application/json");
  }

  /**
   * Convertit un objet JSON contenu dans un corps de requête POST en objet.
   *
   * @param <T>  le type de l'objet
   * @param req  la requête HTTP de type POST
   * @param type le type de l'objet
   *
   * @return un objet de type T
   */
  public static <T> T toObject(final Request req, final TypeReference<T> type) {
    T result = null;
    ObjectMapper om = new ObjectMapper();
    om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    try {
      String json = req.body().asJson().toString();
//      System.out.println("  >>>toObject json: " + json);
      result = om.<T>readValue(json, type);
    } catch (IOException ex) {
      System.out.println(">>> toObject error: " + ex.getMessage());
    }
    return result;
  }

  /**
   * Convertit un objet JSON en String.
   *
   * @param node un noeud JSON
   * @param prop le nom d'une propriété à extraire
   *
   * @return un string avec le contenu de la propriété
   */
  public static String jsonToString(JsonNode node, String prop) {
    String result = "";
    JsonNode obj = node.get(prop);
    if (obj != null) {
      result = obj.textValue();
    }
    return result;
  }

  /**
   * Valide le contexte "cross-domain" d'une requête.
   *
   * @param request  une requête HTTP
   * @param response une réponse HTTP
   */
  public static void validCrossDomainContext(Request request, Response response) {
//    Set<String> whiteList = Sets.newHashSet(
//            "http://localhost:8383",
//            "http://localhost:9000",
//            "http://192.168.0.4:9000",
//            "http://192.168.0.5:9000",
//            "http://jcstritt.emf-informatique.ch",
//            "http://homepage.hispeed.ch");
    Optional<String> origin = request.header("Origin");
//    if (origin != null && whiteList.contains(origin)) {
    boolean ok = origin.isPresent()
      && (origin.get().contains("localhost")
      || origin.get().contains("192.168")
      || origin.get().contains("emf-informatique.ch")
      || origin.get().contains("homepage.hispeed.ch"));
    System.out.println("  validCrossDomainContext origin: " + origin + ", ok:" + ok);
    if (ok) {
      response.setHeader("Access-Control-Allow-Origin", origin.get());
      response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
      response.setHeader("Access-Control-Allow-Credentials", "true");
      response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type");
    }
  }

  /**
   * Valide le contexte "cross-domain" d'une requête.
   *
   * @param ctx le contexte HTTP
   */
  public static void validCrossDomainContext(Context ctx) {
    validCrossDomainContext(ctx.request(), ctx.response());
  }

}
