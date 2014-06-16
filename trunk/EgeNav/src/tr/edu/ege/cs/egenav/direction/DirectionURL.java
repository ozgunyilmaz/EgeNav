package tr.edu.ege.cs.egenav.direction;

import java.util.ArrayList;
import java.util.Collection;
import tr.edu.ege.cs.egenav.Location;
import tr.edu.ege.cs.egenav.Parameter;

/**
 * @author Özgün Yılmaz
 * Created on 12.May.2014, 10:30:33
 */
public abstract class DirectionURL {
    
    private Location org,dest;
    private String tmode;
    private String midURL,separator,clientID;
    private boolean secure=false;
    protected ArrayList<Parameter> parameters=new ArrayList<Parameter>();
    
    
    public abstract String getAbsoluteURLString();
    public abstract Direction getDirections() throws Exception;

    public DirectionURL(Location org, Location dest) {
        this.org = org;
        this.dest = dest;
    }

    public DirectionURL(Location org, Location dest, String travelMode) {
        this.org = org;
        this.dest = dest;
        this.tmode = travelMode;
    }
    
    public Location getDestination() {
        return dest;
    }

    public void setDestination(Location dest) {
        this.dest = dest;
    }

    public Location getOrigin() {
        return org;
    }

    public void setOrigin(Location org) {
        this.org = org;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public String getTravelMode() {
        return tmode;
    }

    public void setTravelMode(String mode) {
        this.tmode = mode;
    }

    public String getMidURL() {
        return midURL;
    }

    public void setMidURL(String midURL) {
        this.midURL = midURL;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
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

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
    
}
