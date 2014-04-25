package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 25.Nis.2014, 14:29:25
 */
public class MercatorProjection {
    
    static final double OFFSET = 268435456;
    static final double OFFSET_RADIUS = OFFSET / Math.PI;
    static final double MATHPI_180 = Math.PI / 180;
    static private final double preLonToX1 = OFFSET_RADIUS * (Math.PI / 180);
    
    public static double bound(double value, double opt_min, double opt_max){
        value = Math.max(value, opt_min);
        value = Math.min(value, opt_max);
        return value;
    }
    
    public static double LonToX(double lon) {
        return Math.round(OFFSET + preLonToX1 * lon);
    }

    public static double LatToY(double lat) {
        return Math.round(OFFSET - OFFSET_RADIUS * Math.log((1 + Math.sin(lat * MATHPI_180)) / (1 - Math.sin(lat * MATHPI_180))) / 2);
        //return Math.round(OFFSET - OFFSET_RADIUS * Math.log((1 + bound(Math.sin(lat * MATHPI_180),-0.9999,0.9999)) / (1 - bound(Math.sin(lat * MATHPI_180),-0.9999,0.9999))) / 2);
    }

    public static double XToLon(double x) {
        return ((Math.round(x) - OFFSET) / OFFSET_RADIUS) * 180 / Math.PI;
    }

    public static double YToLat(double y) {
        return (Math.PI / 2 - 2 * Math.atan(Math.exp((Math.round(y) - OFFSET) / OFFSET_RADIUS))) * 180 / Math.PI;
    }

    public static double adjustLonByPixels(double lon, int delta, int zoom) {
        return XToLon(LonToX(lon) + (delta << (21 - zoom)));
    }

    public static double adjustLatByPixels(double lat, int delta, int zoom) {
        return YToLat(LatToY(lat) + (delta << (21 - zoom)));
    }
    
    public static int getDeltaByLons(double lon1, double lon2, int zoom) {
        return (int)(LonToX(lon2)-LonToX(lon1))>>(21 - zoom);
        
    }
    
    public static int getDeltaByLats(double lat1, double lat2, int zoom) {
        return (int)(LatToY(lat2)-LatToY(lat1))>>(21 - zoom);
    }
    
    public static void main(String args[]) {
        System.out.println(adjustLatByPixels(30,500,6));
        System.out.println(getDeltaByLats(30,28.079109647043285,6));
        
        System.out.println(adjustLonByPixels(30,250,6));
        System.out.println(getDeltaByLons(30,32.19726584851742,6));
        
        
    }
    
}
