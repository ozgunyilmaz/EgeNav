package tr.edu.ege.cs.egenav.direction;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 05.Ara.2014, 16:24:59
 */
public class Junction {
    Point point;
    ArrayList<Double> degrees=new ArrayList<Double>();
    String description;

    public Junction(Point point, String description) {
        this.point = point;
        this.description = description;
    }
    
    public Junction(String pt, String angles){
        
        String[] pts=pt.split(",|;");
        this.point=new Point(Integer.parseInt(pts[0].trim()), Integer.parseInt(pts[1].trim()));
        
        String[] ang=angles.split(",|;");
        for (int i=0;i<ang.length;i++){
            degrees.add(new Double(ang[i].trim()));
        }
    }
    
    public Junction(Point point) {
        this.point = point;
    }

    public ArrayList<Double> getRouteDegrees() {
        return degrees;
    }

    public void addRouteDegree(double degree) {
        degrees.add(degree);
    }
    
    public void getRouteDegree(int index) {
        degrees.get(index);
    }
    
    public int getNumberOfRoutes(){
        return degrees.size();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    
    @Override
    public String toString(){
        
        return point.toString()+": Angles"+degrees;
        
    }
    
    public String toString2(){
        
        String s="";
        
        for (int i=0;i<degrees.size();i++){
            
            s=s+Math.round(degrees.get(i))+", ";
            
        }
        if (!s.isEmpty()){
            return s.substring(0, s.length()-2);
        }
        
        return s;
    }
}
