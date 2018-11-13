package controllers;

import ch.emf.play.helpers.Utils;
import ch.jcsinfo.util.ConvertLib;
import com.typesafe.config.Config;
import java.util.Map;
import javax.inject.Inject;
import play.Logger;
import play.mvc.*;

public class ApplicationCtrl extends Controller {

  private String appFullName;

  @Inject
  public ApplicationCtrl(Config config) {

    // on prépare un message pour le fichier de log
    String appName = config.getString("application.name");
    String appVersion = config.getString("application.version");

    appFullName = appName + " " + appVersion;

    Logger.info(
      appFullName
      + " is running on Play " + play.core.PlayVersion.current()
      + " with Java " + System.getProperty("java.version")
      + " (" + System.getProperty("sun.arch.data.model") + " bits)");

  }

  public Result index() {
    return redirect("/assets/index.html");
  }

  public Result checkPreFlight(String path) {
    Utils.validCrossDomainContext(request(), response());
    return ok();
  }

//  @With(BeforeAfterAction.class)
  public Result lireVersion() {
    return Utils.toJson("version-app", appFullName, "version-srv", "Play " + play.core.PlayVersion.current());
  }

//  @With(BeforeAfterAction.class)
  public Result convertirTemperature() {
    String s = "";
    Map<String, String[]> map = request().body().asFormUrlEncoded();
    if (map.size() == 3) {
      String temperature = ""; // map.get("Temperature")[0];
      String fromUnit = "";    // map.get("FromUnit")[0];
      String toUnit = "";      // map.get("ToUnit")[0];
      for (Map.Entry<String, String[]> entry : map.entrySet()) {
        if (entry.getKey().equalsIgnoreCase("temperature")) {
          temperature = entry.getValue()[0];
        } else if (entry.getKey().equalsIgnoreCase("fromunit")) {
          fromUnit = entry.getValue()[0];
        } else if (entry.getKey().equalsIgnoreCase("tounit")) {
          toUnit = entry.getValue()[0];
        }
      }
//      System.out.println("temperature: " + temperature + ", fromUnit: " + fromUnit + ", toUnit: " + toUnit);

      // essai de convertion du nombre
      if (!temperature.isEmpty() && !temperature.equals("NaN")) {
        double r = 0d;
        if (fromUnit.equalsIgnoreCase("C") && toUnit.equalsIgnoreCase("F")) {
          double c = ConvertLib.stringToDouble(temperature);
          r = c * 9 / 5 + 32;
        } else if (fromUnit.equalsIgnoreCase("F") && toUnit.equalsIgnoreCase("C")) {
          double f = ConvertLib.stringToDouble(temperature);
          r = (f - 32) * 5 / 9;
        }
        s = ConvertLib.formatNumber(r, "#0.0");
      }
    }

    return ok("<double>" + s + "</double>").as("application/xml");
  }

}
