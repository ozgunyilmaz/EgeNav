package tr.edu.ege.cs.egenav;

import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 13.May.2014, 11:34:46
 */
public class GSMWaypoint extends Parameter{
    //todo rename to GSMWaypoint list
    
    
    private ArrayList<Location> waypoints;
    private boolean optimize=false;

    public GSMWaypoint() {
        
        waypoints=new ArrayList<Location>();
        
    }
    
    public GSMWaypoint(ArrayList<Location> waypoints) {
        this.waypoints = waypoints;
    }
    
    public void add(Location c){
        waypoints.add(c);
    }
    
    public void append(ArrayList<Location> p){
        waypoints.addAll(p);
    }
    
    public boolean remove(Location c){
        return waypoints.remove(c);
    }
    
    public Location remove(int i){
        return waypoints.remove(i);
    }
    
    public String getWaypointString(){
        
        if (waypoints.isEmpty()){
            return "";
        }
        
        String w="waypoints=";
        if (optimize){
            w=w+"optimize:true|";
        }
        for (int i=0;i<waypoints.size();i++){
            w=w+waypoints.get(i).toString()+"|";
        }
        while (w.endsWith("|")){
            w=w.substring(0, w.lastIndexOf("|"));
        }
        return w;
    }

    @Override
    public String toString() {
        return getWaypointString();
    }

    public boolean isOptimize() {
        return optimize;
    }

    public void setOptimize(boolean optimize) {
        this.optimize = optimize;
    }
    
}
