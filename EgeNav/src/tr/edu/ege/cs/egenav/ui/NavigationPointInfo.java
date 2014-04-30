package tr.edu.ege.cs.egenav.ui;

import java.awt.Point;
import tr.edu.ege.cs.egenav.Location;

/**
 * @author Özgün Yılmaz
 * Created on 24.Nis.2014, 15:04:11
 */
public class NavigationPointInfo {
    
    private Location location;
    private long timeStamp;
    private double distanceToPreviousLocation;
    private Point point;

    public NavigationPointInfo(Location location, long timeStamp, double distanceToPreviousLocation, Point point) {
        this.location = location;
        this.timeStamp = timeStamp;
        this.distanceToPreviousLocation = distanceToPreviousLocation;
        this.point=point;
    }

    public double getDistanceToPreviousLocation() {
        return distanceToPreviousLocation;
    }

    public void setDistanceToPreviousLocation(double distanceToPreviousLocation) {
        this.distanceToPreviousLocation = distanceToPreviousLocation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    
    public int getX(){
        return (int)point.getX();
    }
    
    public int getY(){
        return (int)point.getY();
    }
    
}
