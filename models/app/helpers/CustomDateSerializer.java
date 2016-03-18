package helpers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jcstritt
 */
public class CustomDateSerializer extends JsonSerializer<Date> {
    private SimpleDateFormat formatter;

    public CustomDateSerializer() {
      formatter = new SimpleDateFormat("yyyy-MM-dd");
    }
 
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider sp) 
      throws IOException, JsonProcessingException {
        gen.writeString(formatter.format(value));
    }
  } 
