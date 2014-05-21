package tr.edu.ege.cs.egenav;

import com.google.gson.annotations.SerializedName;

/**
 * @author Özgün Yılmaz
 * Created on 16.May.2014, 15:14:13
 */
public class GSMStep {
    
    
    private String html_instructions;
    private Polyline polyline;
    @SerializedName("travel_mode")
    private String travelMode;
        
    private Distance distance;
    private Duration duration;
    @SerializedName("end_location")
    private GSMPoint endLocation;
    @SerializedName("start_location")
    private GSMPoint startLocation;

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public GSMPoint getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(GSMPoint endLocation) {
        this.endLocation = endLocation;
    }

    public String getHtml_instructions() {
        return html_instructions;
    }

    public void setHtml_instructions(String html_instructions) {
        this.html_instructions = html_instructions;
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public GSMPoint getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(GSMPoint startLocation) {
        this.startLocation = startLocation;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }
        
    
}
