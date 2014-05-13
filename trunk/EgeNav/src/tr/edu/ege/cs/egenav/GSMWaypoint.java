package tr.edu.ege.cs.egenav;

import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 13.May.2014, 11:34:46
 */
public class GSMWaypoint {
    
    private ArrayList<GSMLocation> waypoints;

    public GSMWaypoint() {
        
        waypoints=new ArrayList<GSMLocation>();
        
    }
    
    public GSMWaypoint(ArrayList<GSMLocation> waypoints) {
        this.waypoints = waypoints;
    }
    
    public void add(GSMLocation c){
        waypoints.add(c);
    }
    
    public void append(ArrayList<GSMLocation> p){
        waypoints.addAll(p);
    }
    
    public boolean remove(GSMLocation c){
        return waypoints.remove(c);
    }
    
    public GSMLocation remove(int i){
        return waypoints.remove(i);
    }
    
//    public String getWaypointString(){
//        
//        
//        
//    }
    
}
