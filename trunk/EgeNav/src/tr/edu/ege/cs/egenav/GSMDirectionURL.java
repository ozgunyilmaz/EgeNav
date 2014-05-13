package tr.edu.ege.cs.egenav;

import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 12.May.2014, 10:47:04
 */
public class GSMDirectionURL extends DirectionURL{
    
    private static final String DRIVING="driving";
    private static final String WALKING="walking";
    private static final String BICYCLING ="bicycling";
    private static final String TRANSIT="transit";
    
    private boolean sensor=false;
    
    private String apiKey,signature,language,region;
    
    public GSMDirectionURL(Location org, Location dest) {
        super(org,dest);
    }

    public GSMDirectionURL(Location org, Location dest, String travelMode) {
        super(org,dest,travelMode);
    }
    
    public  String getEncodedDirectionPoints(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Location> getDirectionPoints() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getDirectionObject() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
