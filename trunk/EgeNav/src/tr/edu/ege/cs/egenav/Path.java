package tr.edu.ege.cs.egenav;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 05.May.2014, 13:42:16
 */
public class Path {
    
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
    
    public void drawPath(BufferedImage img, Color c){
            
        for (int i = 0; i < points.size(); i++) {
            int x3, y3;
            x3 = (int) points.get(i).getX();
            y3 = (int) points.get(i).getY();
            img.setRGB(x3, y3, c.getRGB());
        }
    }
    
    public void print(){
            
        for(int i=0;i<points.size();i++){
            System.out.println(points.get(i));
        }
            
    }
    
    
}
