package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 15.May.2014, 16:46:31
 */
public class GSMBounds {
    
    private double lat,lon;

    public GSMBounds(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLatitude() {
        return lat;
    }

    public void setLatitude(double lat) {
        this.lat = lat;
    }

    public double getLongitude() {
        return lon;
    }

    public void setLongitude(double lon) {
        this.lon = lon;
    }
    
    
    
}
