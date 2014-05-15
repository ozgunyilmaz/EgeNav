package tr.edu.ege.cs.egenav;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 * @author Özgün Yılmaz
 * Created on 15.May.2014, 16:17:58
 */
public class OverviewPolylineDeserializer implements JsonDeserializer<OverviewPolyline> {

    @Override
    public OverviewPolyline deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonObject jo=je.getAsJsonObject();
        JsonElement je2=jo.get("points");
        return new OverviewPolyline(je2.getAsString());
    }
    
}
