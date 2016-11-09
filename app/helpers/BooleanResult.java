package helpers;

import lombok.Data;

/**
 *
 * @author jcstritt
 */
@Data
public class BooleanResult {
  private boolean ok;
  private String message;

  public BooleanResult(boolean ok, String message) {
    this.ok = ok;
    this.message = message;
  }
  
}
