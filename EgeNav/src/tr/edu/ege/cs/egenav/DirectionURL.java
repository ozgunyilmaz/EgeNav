package tr.edu.ege.cs.egenav;

import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 12.May.2014, 10:30:33
 */
public abstract class DirectionURL {
    
    private Location org,dest;
    private String tmode;
    private boolean secure=false;
    
    
    public abstract ArrayList<Location> getDirectionPoints();
    
    public abstract Object getDirectionObject();

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
    
    
    
    public static ArrayList<GeoPoint> decodePolyline(String encoded) {

	ArrayList<GeoPoint> poly = new ArrayList<GeoPoint>();
	int index = 0, len = encoded.length();
	int lat = 0, lon = 0;

	while (index < len) {
		int b, shift = 0, result = 0;
		do {
			b = encoded.charAt(index++) - 63;
			result |= (b & 0x1f) << shift;
			shift += 5;
		} while (b >= 0x20);
		int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
		lat += dlat;

		shift = 0;
		result = 0;
		do {
			b = encoded.charAt(index++) - 63;
			result |= (b & 0x1f) << shift;
			shift += 5;
		} while (b >= 0x20);
		int dlon = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
		lon += dlon;

		GeoPoint p = new GeoPoint((int) (((double) lat / 1E5) * 1E6),
			 (int) (((double) lon / 1E5) * 1E6));
		poly.add(p);
	}

	return poly;
    }
    
}
