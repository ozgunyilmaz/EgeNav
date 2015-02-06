package tr.edu.ege.cs.egenav.direction;

import java.awt.Point;

/**
 * @author Özgün Yılmaz
 * Created on 05.Ara.2014, 16:23:15
 */
public class TurningPoint {
    Point p;
    private double turningAngle;


    public TurningPoint(Point p, double turningAngle) {
        this.p = p;
        this.turningAngle = turningAngle;
        
    }
    
    public TurningPoint(String pt, String ta){
        
        String[] pts=pt.split(",|;");
        this.p=new Point(Integer.parseInt(pts[0].trim()), Integer.parseInt(pts[1].trim()));
        
        turningAngle=new Double(ta);
    }

    public void setPoint(Point p) {
        this.p = p;
    }

    public void setTurningAngle(double turningAngle) {
        this.turningAngle = turningAngle;
    }

    public String getInstructions(double tresAngle) {
        
//        if (turningAngle<(90+tres) || turningAngle>(90-tres)){
//            return "Turn to the right";
//        }else if(turningAngle<(-90+tres) || turningAngle>(-90-tres)){
//            return "Turn to the left";
//        }else if (turningAngle>0){
//            return "Turn "+turningAngle+" degress to the right";
//        }else if (turningAngle<0){
//            return "Turn "+turningAngle+" degress to the left";
//        }
        
        if (turningAngle>0){
            if (turningAngle<(90+tresAngle) && turningAngle>(90-tresAngle)){
                return "turn to the right";
            }else{
                return "turn "+(int)Math.abs(turningAngle)+" degress to the right";
            }
            
        }else{
            if(turningAngle<(-90+tresAngle) && turningAngle>(-90-tresAngle)){
                return "turn to the left";
            }else{
                return "turn "+(int)Math.abs(turningAngle)+" degress to the left";
            }
        }
        
    }
    
    public String getInstructions() {
        
        return getInstructions(5);
    }
    
    public String getDescription() {
        
        return getDescription(5);
    }
    
    public String getDescription(double tresAngle) {
        
        return "Point: "+getPoint()+"; Turning angle: "+Math.round(getTurningAngle())+" degrees; Instructions: "+getInstructions(tresAngle);
    }

    public Point getPoint() {
        return p;
    }

    public double getTurningAngle() {
        return turningAngle;
    }
}
