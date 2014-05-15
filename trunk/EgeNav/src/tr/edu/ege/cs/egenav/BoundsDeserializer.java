package tr.edu.ege.cs.egenav;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 * @author Özgün Yılmaz
 * Created on 15.May.2014, 16:46:48
 */
public class BoundsDeserializer implements JsonDeserializer<GSMBounds>{

    @Override
    public GSMBounds deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
