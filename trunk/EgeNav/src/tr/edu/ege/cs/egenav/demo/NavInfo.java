package tr.edu.ege.cs.egenav.demo;

/**
 * @author Özgün Yılmaz
 * Created on 06.Haz.2014, 14:35:43
 */
public class NavInfo {
    
    private double lat,lon;
    private long time;

    public NavInfo(double lat, double lon, long time) {
        this.lat = lat;
        this.lon = lon;
        this.time = time;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    
    
    
}
