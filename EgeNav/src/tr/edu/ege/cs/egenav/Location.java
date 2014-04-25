package tr.edu.ege.cs.egenav;

import java.awt.Point;

/**
 * @author Özgün Yılmaz
 * Created on 21.Nis.2014, 14:14:03
 */
public abstract class Location {
    
    private String description;
    
    public Location() {
        
    }

    public Location(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    @Override
    public abstract Location clone();
    
    public abstract double getDistanceTo(Location location);
    public abstract double getLatitude();
    public abstract double getLongitude();
    
}
