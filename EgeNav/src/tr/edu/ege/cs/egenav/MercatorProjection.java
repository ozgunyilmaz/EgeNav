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

    public static double LonToX(double lon) {
        return Math.round(OFFSET + preLonToX1 * lon);
    }

    public static double LatToY(double lat) {
        return Math.round(OFFSET - OFFSET_RADIUS * Math.log((1 + Math.sin(lat * MATHPI_180)) / (1 - Math.sin(lat * MATHPI_180))) / 2);
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
    
}
