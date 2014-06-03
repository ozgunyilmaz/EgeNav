package tr.edu.ege.cs.egenav.direction;

import com.google.gson.annotations.SerializedName;

/**
 * @author Özgün Yılmaz
 * Created on 12.May.2014, 16:04:31
 */
public class GSMRoute {
    
    private String summary,copyrights;
    private String[] warnings;
    @SerializedName("waypoint_order")
    private int[] waypointOrder;
    private GSMBounds bounds;
    private GSMLeg[] legs;
    @SerializedName("overview_polyline")
    private Polyline overviewPolyline;

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public Polyline getOverviewPolyline() {
        return overviewPolyline;
    }
    
    public String getOverviewPolylineString() {
        return overviewPolyline.getOverviewPolyline();
    }

    public void setOverviewPolyline(Polyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String[] getWarnings() {
        return warnings;
    }

    public void setWarnings(String[] warnings) {
        this.warnings = warnings;
    }

    public int[] getWaypointOrder() {
        return waypointOrder;
    }

    public void setWaypointOrder(int[] waypointOrder) {
        this.waypointOrder = waypointOrder;
    }

    public GSMBounds getBounds() {
        return bounds;
    }

    public void setBounds(GSMBounds bounds) {
        this.bounds = bounds;
    }

    public GSMLeg[] getLegs() {
        return legs;
    }

    public void setLegs(GSMLeg[] legs) {
        this.legs = legs;
    }
    
    
}
