package tr.edu.ege.cs.egenav;

import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 12.May.2014, 10:47:04
 */
public class GSMDirectionURL extends DirectionURL{
    
    private static final String MODE_DRIVING="driving";
    private static final String MODE_WALKING_MODE="walking";
    private static final String MODE_BICYCLING_MODE ="bicycling";
    private static final String MODE_TRANSIT_MODE="transit";
    
    private static final String AVOID_TOLLS="tolls";
    private static final String AVOID_HIGHWAYS="highways";
    private static final String AVOID_FERRIES="ferries";
    
    private static final String UNIT_METRIC="metric";
    private static final String UNIT_IMPERIAL="imperial";
    
    private boolean sensor=false,alternatives=false;
    
    private String apiKey,signature,language,region,avoid,units ;
    
    private long departureTime, arrival_time;
    
    private GSMWaypoint waypoints;
    
    public GSMDirectionURL(Location org, Location dest) {
        super(org,dest);
        setTravelMode(GSMDirectionURL.MODE_DRIVING);
    }

    public GSMDirectionURL(Location org, Location dest, String travelMode) {
        super(org,dest,travelMode);
        setTravelMode(GSMDirectionURL.MODE_DRIVING);
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
