package tr.edu.ege.cs.egenav.ui;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import tr.edu.ege.cs.egenav.Location;
import tr.edu.ege.cs.egenav.MapURL;
import tr.edu.ege.cs.egenav.MercatorProjection;

/**
 * @author Özgün Yılmaz
 * Created on 30.Nis.2014, 13:56:16
 */
public class Navigation {
    
    private ArrayList<NavigationPointInfo> history=new ArrayList<NavigationPointInfo>();
    private double heading,speed,averageSpeed,timeElapsed,totalDistance;
    
    public void add(NavigationPointInfo np){
        history.add(np);
        totalDistance=totalDistance+np.getDistanceToPreviousLocation();
        updateNavigationInfo();
        
    }
    
//    public boolean remove(NavigationPointInfo np){
//        updateNavigationInfo();
//        return history.remove(np);
//    }
//    
//    public NavigationPointInfo remove(int i){
//        updateNavigationInfo();
//        return history.remove(i);
//    }
    
    public void updateNavigationInfo(){
        
        if (history.size()>1){
            
            //heading=calcArrowDegree(history.get(history.size()-2).getPoint(),getLastElement().getPoint())+Math.PI/2;
            heading=calcArrowDegree2(history.get(history.size()-2).getLocation(),getLastElement().getLocation())+Math.PI/2;

            double deltad=getLastElement().getDistanceToPreviousLocation();
            long last=getLastElement().getTimeStamp();
            long prelast=history.get(history.size()-2).getTimeStamp();
            
            double deltat=(double)(last-prelast)/(60*60*1000);
            speed=deltad/deltat;    //  in km/hr
            
            timeElapsed=(double)(getLastElement().getTimeStamp()-getFirstElement().getTimeStamp())/1000;   //in seconds
            averageSpeed=totalDistance/(timeElapsed/60/60);    //  in km/hr
            
            
        }
        
    }
    
    public void clearHistory(){
        history.clear();
        heading=0;
        speed=0;
        averageSpeed=0;
        timeElapsed=0;
        totalDistance=0;
    }
    
    public boolean isEmpty(){
        return history.isEmpty();
    }
    
    public NavigationPointInfo getLastElement(){
        return history.get(history.size()-1);
    }
    
    public NavigationPointInfo getFirstElement(){
        return history.get(0);
    }

    public void drawRoute(Graphics2D g2d, LineStyle routeLineStyle) {
        if (history.size()>1){
            
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(routeLineStyle.getColor());
            if (routeLineStyle.getStroke()!=null){
                g2d.setStroke(routeLineStyle.getStroke());
            }
            g2d.setComposite(routeLineStyle.getComposite());
            //...
            NavigationPointInfo ng1,ng2;

            for (int i=0;i<history.size()-1;i++){

                ng1=history.get(i);
                ng2=history.get(i+1);

                g2d.drawLine(ng1.getX(),ng1.getY(),ng2.getX(),ng2.getY());

            }
        }
    }
    
    public void drawDirectionArrow(Graphics2D g2d, Arrow arrow) {
        
        if (history.size()>1){
            g2d.setColor(arrow.getLineStyle().getColor());
            if (arrow.getLineStyle().getStroke()!=null){
                g2d.setStroke(arrow.getLineStyle().getStroke());
            }
            g2d.setComposite(arrow.getLineStyle().getComposite());
            //...


            g2d.translate(getLastElement().getX(), getLastElement().getY());
//*************
//            heading=calcArrowDegree(history.get(history.size()-2).getPoint(),getLastElement().getPoint())+Math.PI/2;
//**************
            g2d.rotate(heading);

            g2d.fill(arrow.getShape());
        }
    }
    
    private double calcArrowDegree(Point p1, Point p2){
        double x1=p1.getX(),y1=p1.getY(),x2=p2.getX(),y2=p2.getY();
        double degree;


        if (x1==x2){
            degree=Math.toRadians(90);
            if (y2>y1){
                degree=Math.toRadians(90);
                //System.out.print(degree);
            }else{
                degree=Math.toRadians(90)+Math.toRadians(180);
            }
        }else{
            if (x2>x1){
                degree=Math.atan((y2-y1)/(x2-x1));
                //System.out.print(degree);
            }else{
                degree=Math.atan((y2-y1)/(x2-x1))+Math.toRadians(180);
            }

        }
        return degree;
    }
    
    private double calcArrowDegree2(Location p1, Location p2){
        
        double x1=MercatorProjection.LonToX(p1.getLongitude());
        double y1=MercatorProjection.LatToY(p1.getLatitude());
        double x2=MercatorProjection.LonToX(p2.getLongitude());
        double y2=MercatorProjection.LatToY(p2.getLatitude());
        double degree;


        if (x1==x2){
            degree=Math.toRadians(90);
            if (y2>y1){
                degree=Math.toRadians(90);
                //System.out.print(degree);
            }else{
                degree=Math.toRadians(90)+Math.toRadians(180);
            }
        }else{
            if (x2>x1){
                degree=Math.atan((y2-y1)/(x2-x1));
                //System.out.print(degree);
            }else{
                degree=Math.atan((y2-y1)/(x2-x1))+Math.toRadians(180);
            }

        }
        return degree;
    }
    
    public void refreshPixelCoordinates(MapURL m){
        
        for (int i=0;i<history.size();i++){
            NavigationPointInfo np=history.get(i);
            Point p=m.getPixelOnMap(np.getLocation().getLatitude(),np.getLocation().getLongitude());
            np.setPoint(p);
        }
        
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public double getHeading() {
        return heading;
    }

    public double getSpeed() {
        return speed;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public double getTotalDistance() {
        return totalDistance;
    }
    
    
}
