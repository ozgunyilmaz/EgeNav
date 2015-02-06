package tr.edu.ege.cs.egenav.direction;


import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * @author Özgün Yılmaz
 * Created on 30.Eyl.2014, 13:48:35
 */
public class Util {
    
    public static boolean seeEachOther(Point pt1, Point pt2, BufferedImage img, MapColorModel mcm){
        
        //if (img.getRGB(pt1.x,pt1.y)!=c.getRGB() || img.getRGB(pt2.x,pt2.y)!=c.getRGB()){
        if (!mcm.isOnTrack(img.getRGB(pt1.x,pt1.y)) || !mcm.isOnTrack(img.getRGB(pt2.x,pt2.y))){
            return false;
        }
        
        if (pt1.x!=pt2.x){
        
            double m=(double)(pt1.y-pt2.y)/(double)(pt1.x-pt2.x);

            double step=Math.abs(1/m);
            if (step>1) {step=1;}
            int fark=Math.abs(pt1.x-pt2.x);
            int isaret=(int)Math.signum(pt1.x-pt2.x);
            for (double dx=0;dx<fark;dx=dx+step){

                int newX=(int)Math.round(pt1.x-isaret*dx);
                int newY=(int)Math.round(pt1.y-isaret*m*dx);
                
                //img.setRGB(newX, newY, Color.RED.getRGB());
                //if (img.getRGB(newX,newY)!=c.getRGB()){
                if (!mcm.isOnTrack(img.getRGB(newX,newY))){
                    return false;
                }
            }
        }else{
            int fark=Math.abs(pt1.y-pt2.y);
            int isaret=(int)Math.signum(pt1.y-pt2.y);
            for (int dy=0;dy<fark;dy++){
                int newX=pt1.x;
                int newY=(int)Math.round(pt1.y-isaret*dy);
                //img.setRGB(newX, newY, Color.RED.getRGB());
                
                if (!mcm.isOnTrack(img.getRGB(newX,newY))){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public static double calculateLineAngle(Point p1, Point p2){
        
//        double ang=Math.atan((p1.getY()-p2.getY())/(p1.getX()-p2.getX()));
//        return (ang*180)/Math.PI;
        
         double ang = Math.atan2(p2.getX()-p1.getX(), p2.getY()-p1.getY()) * 180 / Math.PI -90;
//         if (ang<0){
//             ang=ang+360;
//         }//0-360 derece arası
//         if (ang>180){
//             ang=ang-360;
//         }//Yukarıdaki if ile beraber -180 +180 derece arası
         if (ang<-180){
             ang=ang+360;
         }//Sadece bu if ile de -180 +180 derece arası
         return ang;
        
    }
    
    public static double calculateAngle(Point p1, Point p2, Point p3){
        return 0;
    }
    
    public static void iterate(Point pt1, Point pt2, BufferedImage img){
        double m=(double)(pt1.y-pt2.y)/(double)(pt1.x-pt2.x);

            double step=Math.abs(1/m);
            if (step>1) {step=1;}
            int fark=Math.abs(pt1.x-pt2.x);
            int isaret=(int)Math.signum(pt1.x-pt2.x);
            for (double dx=0;dx<fark;dx=dx+step){

                int newX=(int)Math.round(pt1.x-isaret*dx);
                int newY=(int)Math.round(pt1.y-isaret*m*dx);
                
                //img.setRGB(newX, newY, Color.RED.getRGB());
                img.setRGB(newX, newY, Color.red.getRGB());
            }
    }
    
    public static void main(String[] args) {
        
        //System.out.println(calculateLineAngle(new Point(5,5),new Point(5,10)));
        try {
                
                BufferedImage img2 = ImageIO.read(new File("src\\c2.png"));
                //Util.iterate(new Point(120, 318),new Point(152, 269), img2);
                //ImageIO.write(img2, "png", new File("src\\c3_it.png"));
                Path pts=new Path();
                //pts.add(new Point(126,317));
//                pts.normalizeHelper(img2, new Point(126,317), Color.black, 20);
//                pts.normalizeHelper(img2, new Point(314,353), Color.black, 20);
//                pts.normalizeHelper(img2, new Point(77,364), Color.black, 25, 25);
//                pts.normalizeHelper(img2, new Point(281,302), Color.black, 25);
                System.out.println(pts);

            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        
    }
    
}
