package tr.edu.ege.cs.egenav.direction;

import com.google.gson.annotations.SerializedName;

/**
 * @author Özgün Yılmaz
 * Created on 16.May.2014, 14:27:38
 */
public class GSMPoint {
    
    
    private double lat;
    @SerializedName("lng")
    private double lon;

    public GSMPoint(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
    
    
    
}
