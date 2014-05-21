package tr.edu.ege.cs.egenav;

import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.IOUtils;

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
    
    private String apiKey,signature,language,region,avoid,units,output;
    
    private long departureTime=-1, arrivalTime=-1;
    
    private GSMWaypoint waypoints;
    
    public GSMDirectionURL(GSMLocation org, GSMLocation dest) {
        super(org,dest);
        setTravelMode(GSMDirectionURL.MODE_DRIVING);
        output="json";
        setSeparator("&");
        setMidURL("http://maps.googleapis.com/maps/api/directions/");
    }

    public GSMDirectionURL(GSMLocation org, GSMLocation dest, String travelMode) {
        super(org,dest,travelMode);
        output="json";
        setSeparator("&");
        setMidURL("http://maps.googleapis.com/maps/api/directions/");
        
    }
    
    @Override
    public String getAbsoluteURLString() {
        String url;
        
        if (isSecure()){
            url="https://";
        }else{
            url="http://";
        }
        
        url=url+getMidURL()+getOutput()+"?";
        
        url=url+"origin="+getOrigin().toString()+getSeparator()+"destination="+getDestination().toString()+getSeparator()+
                "sensor="+isSensor()+getSeparator();
        
        if (getLanguage()!=null && !getLanguage().isEmpty()){
            url=url+"language="+getLanguage()+getSeparator();
        }
                
        if (getRegion()!=null && !getRegion().isEmpty()){
            url=url+"region="+getRegion()+getSeparator();
        }
        
        if (getUnits()!=null && !getUnits().isEmpty()){
            url=url+"units="+getUnits()+getSeparator();
        }
        
        if (getAvoid()!=null && !getAvoid().isEmpty()){
            url=url+"avoid="+getAvoid()+getSeparator();
        }
        
        if (getTravelMode()!=null && !getTravelMode().isEmpty()){
            url=url+"mode="+getTravelMode()+getSeparator();
        }
        
        if (getDepartureTime()>0){
            url=url+"departure_time="+getDepartureTime()+getSeparator();
        }
        
        if (getArrivalTime()>0){
            url=url+"arrival_time="+getArrivalTime()+getSeparator();
        }
        
        if (getWaypoints()!=null){
            url=url+getWaypoints().getWaypointString()+getSeparator();
        }
        
        for (int i=0;i<parameters.size();i++){
            url=url+parameters.get(i).toString()+getSeparator();
        }
        
        if (getApiKey()!=null && !getApiKey().isEmpty()){
            
            url=url+"key="+getApiKey()+getSeparator();
        }
        
        if (getClientID()!=null && !getClientID().isEmpty()){
            
            url=url+"client="+getClientID()+getSeparator();
        }
        
        if (getSignature()!=null && !getSignature().isEmpty()){
            
            url=url+"signature="+getSignature()+getSeparator();
        }
        
        while (url.endsWith(getSeparator())){
            url=url.substring(0, url.lastIndexOf(getSeparator()));
        }
        
        
        
        return url;
    }
    
    @Override
    public GSMDirectionResponse getDirectionObject() throws MalformedURLException, IOException{
        String url = getAbsoluteURLString();
        // Get the contents of json as a string using commons IO IOUTils class.
        
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Polyline.class, new PolylineDeserializer());
        //gson.registerTypeAdapter(GSMBounds.class, new BoundsDeserializer());
        GSMDirectionResponse res = gson.create().fromJson(IOUtils.toString(new URL(url)), GSMDirectionResponse.class);
        return res;
    }

    public boolean isAlternatives() {
        return alternatives;
    }

    public void setAlternatives(boolean alternatives) {
        this.alternatives = alternatives;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrival_time(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isSensor() {
        return sensor;
    }

    public void setSensor(boolean sensor) {
        this.sensor = sensor;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public GSMWaypoint getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(GSMWaypoint waypoints) {
        this.waypoints = waypoints;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
    
    

}
