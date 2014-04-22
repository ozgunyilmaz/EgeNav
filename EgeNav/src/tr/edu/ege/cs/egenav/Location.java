package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 21.Nis.2014, 14:14:03
 */
public class Location {
    
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
    public Location clone(){
        return new Location(description);
    }
    
}
