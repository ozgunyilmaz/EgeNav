package tr.edu.ege.cs.egenav;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 13:28:15
 */
public abstract class MapURL {
    
    private boolean secure=false;
    private String midURL,clientID,mapType,format;
    private String separator;
    protected ArrayList<Parameter> parameters=new ArrayList<Parameter>();
    private MapSize mapSize;
    private int zoom=-1;
    private Location location=null;
    
    public MapURL(){
        
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String seperator) {
        this.separator = seperator;
    }
    
    public abstract String getAbsoluteURLString();
    
    public void setSecure(boolean b){
        secure=b;
    }
    
    public boolean isSecure(){
        return secure;
    }

    public String getMiddleURL() {
        return midURL;
    }

    public void setMiddleURL(String midURL) {
        this.midURL = midURL;
    }
    
    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
    
    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public MapSize getMapSize() {
        return mapSize;
    }

    public void setMapSize(MapSize mapSize) {
        this.mapSize = mapSize;
    }

    public void addParameter(Parameter p){
        parameters.add(p);
    }
    
    public boolean removeParameter(Parameter p){
        return parameters.remove(p);
    }
    
    public Object removeParameter(int i){
        return parameters.remove(i);
    }
    
    public void appendParameters(Collection c){
        parameters.addAll(c);
    }
    
}
