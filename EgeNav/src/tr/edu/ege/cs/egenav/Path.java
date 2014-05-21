package tr.edu.ege.cs.egenav;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import tr.edu.ege.cs.egenav.ui.LineStyle;

/**
 * @author Özgün Yılmaz
 * Created on 05.May.2014, 13:42:16
 */
public class Path extends Direction{
    
    private ArrayList<Point> points;
    
    public Path(){

        points=new ArrayList<Point>();

    }
    
    public Path(Point po){
        points=new ArrayList<Point>();
        points.add(po);
    }
    
    public Path(ArrayList<Point> p){
        points=p;
    }
    
    public int getLastX(){
        return points.get(points.size()-1).x;
    }
    
    public int getLastY(){
        return points.get(points.size()-1).y;
    }
    
    public ArrayList<Point> getPoints(){
        return points;
    }
    
    public void add(Point p){
        points.add(p);
    }
    
    @Override
    public Path clone(){
		
        Path poi=new Path();

        for (int i=0;i<points.size();i++){
           poi.add(new Point(points.get(i).x,points.get(i).y));
        }
        return poi;
        
    }
    
////    public void drawPath(BufferedImage img, Color c){
////            
////        for (int i = 0; i < points.size(); i++) {
////            int x3, y3;
////            x3 = (int) points.get(i).getX();
////            y3 = (int) points.get(i).getY();
////            img.setRGB(x3, y3, c.getRGB());
////        }
////    }
////    
////    public void drawPath(Graphics2D g2d, Color c){
////            
////        for (int i = 0; i < points.size()-1; i++) {
////            
////            g2d.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y);
////        }
////    }
    
    public void print(){
            
        for(int i=0;i<points.size();i++){
            System.out.println(points.get(i));
        }
            
    }

    @Override
    public String getInstructions(GeoPoint g) {
        //todo makale için yapılabilir.
        return "";
    }

    @Override
    public void refreshPixelCoordinates(MapURL m) {
        points.clear(); //Eğer harita refresh olursa yeni haritada yeniden yol bulunmalı
    }

    @Override
    public void drawPathOnMap(Graphics2D g2d, LineStyle routeLineStyle) {
        if (points.size()>1){
            
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(routeLineStyle.getColor());
            if (routeLineStyle.getStroke()!=null){
                g2d.setStroke(routeLineStyle.getStroke());
            }
            g2d.setComposite(routeLineStyle.getComposite());
            //...
            

            for (int i=0;i<points.size()-1;i++){

                Point p1=points.get(i);
                Point p2=points.get(i+1);

                g2d.drawLine(p1.x,p1.y,p2.x,p2.y);

            }
        }
    }
    
    
}
