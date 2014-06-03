package tr.edu.ege.cs.egenav.direction;

import com.google.gson.annotations.SerializedName;

/**
 * @author Özgün Yılmaz
 * Created on 16.May.2014, 14:59:55
 */
public class GSMLeg {
    
    private Distance distance;
    private Duration duration;
    @SerializedName("end_address")
    private String endAddress;
    @SerializedName("end_location")
    private GSMPoint endLocation;
    @SerializedName("start_address")
    private String startAddress;
    @SerializedName("start_location")
    private GSMPoint startLocation;
    private GSMStep[] steps;
    

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

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public GSMPoint getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(GSMPoint endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public GSMPoint getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(GSMPoint startLocation) {
        this.startLocation = startLocation;
    }

    public GSMStep[] getSteps() {
        return steps;
    }

    public void setSteps(GSMStep[] steps) {
        this.steps = steps;
    }

    
}
