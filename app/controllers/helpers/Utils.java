package controllers.helpers;

import ch.jcsinfo.datetime.DateTimeLib;
import ch.jcsinfo.system.StackTracer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Set;
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

  public static void logInfo(long startTime, Object... params) {
    String threadId = String.valueOf(Thread.currentThread().getId());
    String userId = (SessionManager.isSessionOpen()) ? "" + SessionManager.getSessionLoginId() : "?";
    String msg = DateTimeLib.dateToString(DateTimeLib.getDate(), "dd.MM.yy HH:mm:ss");
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

  public static void logInfo(Context ctx) {
    String msg = DateTimeLib.dateToString(DateTimeLib.getDate(), "dd.MM.yy HH:mm:ss");
    String route = ctx.toString();
    route = route.substring(route.indexOf("(") + 1, route.indexOf(")"));
    if (route.contains("login/")) {
      route = route.substring(0, route.lastIndexOf("/"));
    }
    msg += ", " + route;

    if (SessionManager.isSessionOpen()) {
      msg += " (USER: " + SessionManager.getSessionLoginId() + ")";
    }

    long startTime = Long.parseLong(ctx.response().getHeaders().get("logtimestamp"));
    if (startTime >= 0) {
      msg += ", " + (System.currentTimeMillis() - startTime) + " ms";
    }

    Logger.info(msg);
  }

  public static Result toJson(Object object) {
    Result result;
    if (object != null) {
      result = ok(Json.toJson(object)).as("application/json");
    } else {
      result = internalServerError("NULL_OBJECT_ERROR");
    }
    return result;
  }

  public static Result toJson(String key, Object value) {
    ObjectNode jsonObj = Json.newObject();
    jsonObj.putPOJO(key, value);
    return ok(jsonObj).as("application/json");
  }

  public static Result toJson(String key1, Object value1, String key2, Object value2) {
    ObjectNode jsonObj = Json.newObject();
    jsonObj.putPOJO(key1, value1);
    jsonObj.putPOJO(key2, value2);
    return ok(jsonObj).as("application/json");
  }

  public static Result toJson(boolean ok, String message) {
    BooleanResult bResult = new BooleanResult(ok, message);
    return ok(Json.toJson(bResult)).as("application/json");
  }
  
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

  public static String jsonToString(JsonNode node, String prop) {
    String result = "";
    JsonNode obj = node.get(prop);
    if (obj != null) {
      result = obj.textValue();
    }
    return result;
  }

  public static void validCrossDomainContext(Request request, Response response) {
    Set<String> whiteList = Sets.newHashSet(
            "http://localhost:8383",
            "http://localhost:9000",
            "http://192.168.0.4:9000",
            "http://192.168.0.5:9000",
            "http://stritt-jean-claude.emfprod.ch",
            "http://code.emfprod.ch",
            "http://homepage.hispeed.ch",
            "https://stormy-atoll-68012.herokuapp.com");
    String origin = request.getHeader("Origin");
    if (origin != null && whiteList.contains(origin)) {
      response.setHeader("Access-Control-Allow-Origin", origin);
      response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
      response.setHeader("Access-Control-Allow-Credentials", "true");
      response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type");
    }
  }

  public static void validCrossDomainContext(Context ctx) {
    validCrossDomainContext(ctx.request(), ctx.response());
  }

}
