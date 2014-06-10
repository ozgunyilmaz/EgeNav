package tr.edu.ege.cs.egenav.direction;

import java.util.ArrayList;
import tr.edu.ege.cs.egenav.GeoPoint;

/**
 * @author Özgün Yılmaz
 * Created on 15.May.2014, 16:35:56
 */
public class Polyline {
    
    private String overviewPolyline;

    public Polyline(String overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    
    
    public String getOverviewPolyline() {
        return overviewPolyline;
    }

    public void setOverviewPolyline(String overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }
    
    public ArrayList<GeoPoint> getGeoPoints(){
        
        ArrayList<GeoPoint> poly = new ArrayList<GeoPoint>();
	int index = 0, len = overviewPolyline.length();
	int lat = 0, lon = 0;

	while (index < len) {
		int b, shift = 0, result = 0;
		do {
                    b = overviewPolyline.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
		} while (b >= 0x20);
		int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
		lat += dlat;

		shift = 0;
		result = 0;
		do {
                    b = overviewPolyline.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
		} while (b >= 0x20);
		int dlon = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
		lon += dlon;

//		GeoPoint p = new GeoPoint((int) (((double) lat / 1E5) * 1E6),
//			 (int) (((double) lon / 1E5) * 1E6));
                GeoPoint p = new GeoPoint((((double) lat / 1E5)),(((double) lon / 1E5)));
		poly.add(p);
	}

	return poly;
        
    }
    
}
