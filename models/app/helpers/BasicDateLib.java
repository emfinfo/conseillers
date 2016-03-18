package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Formatage basique d'une date.
 * 
 * @author jcstritt
 */
public class BasicDateLib {
  public final static String DATE_FORMAT_STANDARD = "d.M.yyyy";

  /**
   * Retourne le format locale d'une date avec ou sans heure.
   *
   * @param format un format de date et/ou heure
   * @return le format local d'une date
   */
  private static SimpleDateFormat getLocaleFormat(String format) {
    Locale locale = Locale.getDefault();
    return new SimpleDateFormat(format, locale);
  }  
  
  /**
   * Convertit une date (java.util.Date) vers une représentation String construite avec le
   * format demandé.
   *
   * @param date une date de la classe java.util.Date
   * @param format un format de date (ou de temps) sous la forme d'un String
   * @return la même date au format String
   */
  public static String dateToString(Date date, String format) {
    String sDate = "...";
    if (date != null) {
      SimpleDateFormat ldf = getLocaleFormat(format);
      sDate = ldf.format(date);
    }
    return sDate;
  }

  /**
   * Convertit une date (java.util.Date) vers une représentation String standard.
   *
   * @param date une date de la classe java.util.Date
   * @return la même date au format String
   */
  public static String dateToString(Date date) {
    return dateToString(date, DATE_FORMAT_STANDARD);
  }  
}
