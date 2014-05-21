package tr.edu.ege.cs.egenav;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import tr.edu.ege.cs.egenav.ui.LineStyle;

/**
 * @author Özgün Yılmaz
 * Created on 15.May.2014, 14:58:19
 */
public class GSMDirectionResponse extends Direction{
    
    private double treshold;
    private String status;
    private GSMRoute[] routes;
    ArrayList<Point> p;

    public GSMRoute[] getRoutes() {
        return routes;
    }

    public void setRoutes(GSMRoute[] routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String getInstructions(GeoPoint g) {
        //todo bir önceki bulunan yer akılda tutulup oradan başlanabilir.
        GSMRoute r=routes[0];

        double dis=1000000;
        int[] index={-1,-1,-1};
        
        
        for (int i=0;i<r.getLegs().length;i++){
            
            GSMLeg leg=r.getLegs()[i];
            for (int j=0;j<leg.getSteps().length;j++){
                
                GSMStep step=leg.getSteps()[j];
                ArrayList<GeoPoint> a=step.getPolyline().getGeoPoints();
                
                for (int k=0;k<a.size();k++){
                    
                    GeoPoint gp=a.get(k);
                    double d=gp.getDistanceTo(g);
                    if (d<dis){
                        dis=d;
                        index[0]=i;
                        index[1]=j;
                        index[2]=k;
                    }
                    
                }
                
            }
            
        }
        
        if (index[0]<0){
            return "";
        }else if (dis<0.2){
            GSMStep step=r.getLegs()[index[0]].getSteps()[index[1]];
            return step.getHtml_instructions();
        }
        return "";
        
    }

    @Override
    public void refreshPixelCoordinates(MapURL m) {
        
        p=new ArrayList<Point>();
        GSMRoute r=routes[0];

        
        for (int i=0;i<r.getLegs().length;i++){
            
            GSMLeg leg=r.getLegs()[i];
            for (int j=0;j<leg.getSteps().length;j++){
                
                GSMStep step=leg.getSteps()[j];
                ArrayList<GeoPoint> a=step.getPolyline().getGeoPoints();
                
                for (int k=0;k<a.size();k++){
                    
                    GeoPoint gp=a.get(k);
                    p.add(m.getPixelOnMap(gp.getLatitude(), gp.getLongitude()));
                    
                }
                
            }
            
        }
    }

    @Override
    public void drawPathOnMap(Graphics2D g2d, LineStyle routeLineStyle) {
        
        if (p.size()>1){
            
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(routeLineStyle.getColor());
            if (routeLineStyle.getStroke()!=null){
                g2d.setStroke(routeLineStyle.getStroke());
            }
            g2d.setComposite(routeLineStyle.getComposite());
            //...
            

            for (int i=0;i<p.size()-1;i++){

                Point p1=p.get(i);
                Point p2=p.get(i+1);

                g2d.drawLine(p1.x,p1.y,p2.x,p2.y);

            }
        }
    
    }
}
