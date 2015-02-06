package tr.edu.ege.cs.egenav.direction;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import tr.edu.ege.cs.egenav.GeoPoint;
import tr.edu.ege.cs.egenav.MapURL;
import tr.edu.ege.cs.egenav.ui.LineStyle;

/**
 * @author Özgün Yılmaz
 * Created on 05.May.2014, 13:42:16
 */
public class Path extends Direction{
    
    private ArrayList<Point> points;
    private ArrayList<TurningPoint> tp;
    //private ArrayList<Junction> junction=new ArrayList<Junction>();
    private MapURL mapurl;
    
    public Path(){

        points=new ArrayList<Point>();
        
    }
    
    public Path(MapURL mapurl){

        points=new ArrayList<Point>();
        this.mapurl=mapurl;

    }
    
    public Path(Point po){
        points=new ArrayList<Point>();
        points.add(po);
    }
    
    public Path(MapURL mapurl, Point po){
        this.mapurl=mapurl;
        points=new ArrayList<Point>();
        points.add(po);
    }
    
    public Path(MapURL mapurl, ArrayList<Point> p){
        this.mapurl=mapurl;
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
		
        Path poi=new Path(mapurl);

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
    
    public void recomputeTurningPoints(){
        tp=getTurningPoints();
    }

    @Override
    public String getInstructions(GeoPoint g) {
        //todo makale için yapılabilir.
        double thres=0.2;
        if (tp==null){
            recomputeTurningPoints();
        }
        
        for (int i=0;i<tp.size();i++){
            TurningPoint t=tp.get(i);
            GeoPoint gp=mapurl.getCordinatesOnMap(t.getPoint());
            double dist=gp.getDistanceTo(g);
            
            if (dist<=thres){
                return "After 200 meters, "+t.getInstructions();
            }
            //tp için pikselden koordinatları elde et.
            //uzaklığı kontrol et, tresden küçükse
        }
        return "Go straight in this direction";
        //Hiç bir şey döndürülmezse "dosdoğru git" mesajı döndürülmeli
        
    }

    @Override
    public void refreshPixelCoordinates(MapURL m) {
        points.clear(); //Eğer harita refresh olursa yeni haritada yeniden yol bulunmalı
        
        for (int k=0;k<points.size();k++){
                    
            Point p=points.get(k);
            GeoPoint gp=mapurl.getCordinatesOnMap(p);
            points.add(m.getPixelOnMap(gp.getLatitude(), gp.getLongitude()));
                    
        }
        mapurl=m;
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
    
//    public int getX(){
//        return ((int)(points.get(points.size() - 1).getX()));
//    }
//
//    public int getY(){
//        return (int)(p.lastElement().getY());
//    }

//    public Path clone(){
//            return new Path((ArrayList<Point>)(points.clone()));
////		Points poi=new Points();
////
////		for (int i=0;i<p.size();i++){
////			poi.add((Point)(p.elementAt(i)).clone());
////		}
////		return poi;
//    }

    public Path shift(int x, int y){
        //tüm yolu kaydırma
        Path newPath=new Path(mapurl);
        for(int i=0;i<points.size();i++){
            Point pt=points.get(i);
            Point newP=new Point(pt.x+x,pt.y+y);
            newPath.add(newP);
        }

        return newPath;

    }

    public void drawPath(BufferedImage img){

        for (int i = 0; i < points.size(); i++) {
            int x3, y3;
            x3 = (int) points.get(i).getX();
            y3 = (int) points.get(i).getY();
            img.setRGB(x3, y3, Color.RED.getRGB());
        }
    }

    public void drawLinePath(BufferedImage img){

        Graphics2D g2d=img.createGraphics();
        for (int i = 0; i < points.size()-1; i++) {
            int x3, y3, x4, y4;
            x3 =  points.get(i).x;
            y3 =  points.get(i).y;
            x4 =  points.get(i+1).x;
            y4 =  points.get(i+1).y;       
            g2d.setColor(Color.red);
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHints(rh);
            g2d.setStroke(new BasicStroke(3));

            g2d.drawLine(x3, y3, x4, y4);

        }
    }

    public void drawShift(BufferedImage img, int shift){

        for (int i=0;i<shift;i++){
            Path temp=shift(i+1, 0);
            temp.drawPath(img);
        }

    }

    public Path shiftDrawPath(BufferedImage img, MapColorModel mcm){
        //pixel walking, tarama yapar, sınır rengine rastladı mı kaydırma işlemini orada bırakır
        drawPath(img);
        int deltax=getDeltaX();
        int deltay=getDeltaY();
        //System.out.println(deltay);
        int x=0;
        int shift=0;
        int s1,s2,s3,s4;
        while (x<deltay*3/4){

            x=0;
            shift++;
            for(int i=0;i<points.size();i++){
                Point pt=points.get(i);

                //if (pt.x+shift>=0 && pt.x+shift<img.getWidth() && img.getRGB(pt.x+shift, pt.y)==Color.BLACK.getRGB()){
                if (pt.x+shift>=0 && pt.x+shift<img.getWidth() && !mcm.isOnTrack(img.getRGB(pt.x+shift, pt.y))){
                    x++;
                }

            }
//                System.out.println(shift);
//                System.out.println(x);
        }
        //////////////
        s1=shift;
        for (int i=0;i<shift;i++){
            Path temp2=shift(i+1, 0);
            temp2.drawPath(img);
        }

        //////////////
//            System.out.println(shift);
        x=0;
        shift=0;
        while (x<deltay*3/4){
            x=0;
            shift--;
            for(int i=0;i<points.size();i++){
                Point pt=points.get(i);

                //if (pt.x+shift>=0 && pt.x+shift<img.getWidth() && img.getRGB(pt.x+shift, pt.y)==Color.BLACK.getRGB()){
                if (pt.x+shift>=0 && pt.x+shift<img.getWidth() && !mcm.isOnTrack(img.getRGB(pt.x+shift, pt.y))){
                    x++;
                }

            }
        }
        ///////////
        s2=shift;
        for (int i=0;i>shift;i--){
            Path temp2=shift(i-1, 0);
            temp2.drawPath(img);
        }
        ///////////////
//            System.out.print(shift);

        x=0;
        shift=0;
        while (x<deltax*3/4){

            x=0;
            shift++;
            for(int i=0;i<points.size();i++){
                Point pt=points.get(i);

                //if (pt.y+shift>=0 && pt.y+shift<img.getHeight() && img.getRGB(pt.x, pt.y+shift)==Color.BLACK.getRGB()){
                if (pt.y+shift>=0 && pt.y+shift<img.getHeight() && !mcm.isOnTrack(img.getRGB(pt.x, pt.y+shift))){
                    x++;
                }

            }
//                System.out.println(shift);
//                System.out.println(x);
        }
        //////////////
        s3=shift;
        for (int i=0;i<shift;i++){
            Path temp2=shift(0, i+1);
            temp2.drawPath(img);
        }
        //////////////


        x=0;
        shift=0;
        while (x<deltax*3/4){

            x=0;
            shift--;
            for(int i=0;i<points.size();i++){
                Point pt=points.get(i);

                if (pt.y+shift>=0 && pt.y+shift<img.getHeight() && !mcm.isOnTrack(img.getRGB(pt.x, pt.y+shift))){
                    x++;
                }

            }
//                System.out.println(shift);
//                System.out.println(x);
        }
        //////////////
        s4=shift;
        for (int i=0;i>shift;i--){
            Path temp2=shift(0, i-1);
            temp2.drawPath(img);
        }
        //////////////
        Path temp2=shift(s1, 0);



        Path temp3=shift(s2, 0);
        Path temp4=shift(0, s3);
        Path temp5=shift(0, s4);

        temp2.getPoints().addAll(temp3.getPoints());
        temp2.getPoints().addAll(temp4.getPoints());
        temp2.getPoints().addAll(temp5.getPoints());


        //temp2.drawPath(img);
        return temp2;
    }
    
    public Path toLine(BufferedImage img, MapColorModel mcm){
        
        ArrayList<Point> pNew=(ArrayList<Point>)points.clone();
        Path pts=new Path(mapurl);

        Point ilk=pNew.get(0);
        //normalizeHelper(img,ilk,Color.BLACK, tres);
        pts.add(ilk);

        for(int i=1;i<pNew.size();i++){

            //////normalizeHelper(img,pNew.get(i),Color.BLACK, tres);
            //boolean b=Util.seeEachOther(ilk, pNew.get(i), img, Color.WHITE);
            boolean b=Util.seeEachOther(ilk, pNew.get(i), img, mcm);
            if (!b){
                pts.add(pNew.get(i-1));
                ilk=pNew.get(i-1);

            }

        }

        pts.add(new Point(pNew.get(pNew.size()-1).x,pNew.get(pNew.size()-1).y));

        return pts;
    }

    public ArrayList<TurningPoint> getTurningPoints(double threshold){

        ArrayList tp=new ArrayList();

        for (int i=0;i<points.size()-2;i++){
            Point p1=points.get(i);
            Point p2=points.get(i+1);
            Point p3=points.get(i+2);
            double deg1=Util.calculateLineAngle(p1, p2);
            double deg2=Util.calculateLineAngle(p2, p3);

            double dd=deg1-deg2;
            if (dd>180){
                dd=dd-360;
            }else if (dd<-180){
                dd=dd+360;
            }
            if (Math.abs(dd)>threshold){
                TurningPoint tpt=new TurningPoint(p2, dd);
                tp.add(tpt);
            }
        }
        return tp;
    }

    public ArrayList<TurningPoint> getTurningPoints(){

        return getTurningPoints(5);
    }

    public Threshold getThreshold(BufferedImage img, MapColorModel mcm){
//            System.out.println("****************** "+img.getWidth()+"-"+img.getHeight());
//            img.getRGB(img.getWidth()-1, img.getHeight()-1);
        int maxx=1000,maxy=1000;
        for(int i=0;i<points.size();i++){

            int x=points.get(i).x;
            int y=points.get(i).y;
            int left=0,right=0,up=0,down=0;
//                boolean b1=false,b2=false,b3=false,b4=false;
            //System.out.print(x+","+y+"->");
            for (int k=0;true;k++){

                if (x>=k && mcm.isOnTrack(img.getRGB(x-k, y))){
                    left++;
                }else{
                    //b1=true;
                    break;
                }

            }

            for (int k=1;true;k++){

                if (x+k<img.getWidth() &&  mcm.isOnTrack(img.getRGB(x+k, y))){
                    left++;
                }else{
                    //b1=true;
                    break;
                }

            }
            if (maxx>(left+right)){
                maxx=left+right;
            }
            //System.out.print(x+","+y+"->"+(left+right));

            for (int k=0;true;k++){

                if (y>=k && mcm.isOnTrack(img.getRGB(x, y-k))){
                    up++;
                }else{
                    //b1=true;
                    break;
                }

            }

            for (int k=1;true;k++){

                if (y+k<img.getHeight() && mcm.isOnTrack(img.getRGB(x, y+k))){
                    down++;
                }else{
                    //b1=true;
                    break;
                }

            }
            if (maxy>(up+down)){
                maxy=up+down;
            }
            //System.out.println("---"+(up+down));

        }
        //System.out.println("Tres: "+maxx+" "+maxy);

        double carpan;
        if (maxx>10 && maxy>10){
            carpan=1.5;
        }else{
            carpan=1.6;
        }
        int horT=(int)(maxx*carpan);
        int verT=(int)(maxy*carpan);

        if (horT>(verT*2)){
            return new Threshold(verT, verT);
        }else if (verT>(horT*2)){
            return new Threshold(horT, horT);
        }



        return new Threshold(horT, verT);
    }

    public Path toNormalizedLine(BufferedImage img, MapColorModel mcm, int horizontalThreshold, int verticalThreshold){
        return toNormalizedLine(img, mcm, new Threshold(horizontalThreshold, verticalThreshold));
    }

    public Path toNormalizedLine(BufferedImage img, MapColorModel mcm, Threshold threshold){
        normalizeWithInterpolation(img, mcm, threshold);
        ArrayList<Point> pNew=(ArrayList<Point>)points.clone();
        Path pts=new Path(mapurl);

        Point ilk=pNew.get(0);
        //normalizeHelper(img,ilk,Color.BLACK, threshold);
        normalizeHelper(img, ilk, mcm, threshold);
        pts.add(ilk);

        for(int i=1;i<pNew.size();i++){

            Point check=pNew.get(i);
            
            //boolean b=Util.seeEachOther(ilk, check, img, Color.WHITE);
            boolean b=Util.seeEachOther(ilk, check, img, mcm);
            if (!b){
                pts.add(pNew.get(i-1));
                ilk=pNew.get(i-1);
            }
            
        }

        pts.add(new Point(pNew.get(pNew.size()-1).x,pNew.get(pNew.size()-1).y));

        return pts;
    }

    public Path toNormalizedLine2(BufferedImage img, MapColorModel mcm, int horizontalThreshold, int verticalThreshold){
        return toNormalizedLine2(img, mcm, new Threshold(horizontalThreshold, verticalThreshold));
    }

    public Path toNormalizedLine2(BufferedImage img, MapColorModel mcm, Threshold threshold){

        ArrayList<Point> pNew=(ArrayList<Point>)points.clone();
        Path pts=new Path(mapurl);
        boolean eklendi=false;
        for(int i=0;i<pNew.size();i++){
            //todo son noktayı eklemeyi unutma
            boolean b=normalizeHelper(img,pNew.get(i),mcm, threshold);
            if (b){
                //if (i>0 && !pts.getPoints().isEmpty() && Util.seeEachOther(pNew.get(i), pts.getPoints().get(pts.getPoints().size()-1), img, Color.white)){
                if (i>0 && !pts.getPoints().isEmpty() && Util.seeEachOther(pNew.get(i), pts.getPoints().get(pts.getPoints().size()-1), img, mcm)){
                    if (!eklendi){
                        pts.add(new Point(pNew.get(i).x,pNew.get(i).y));
                        eklendi=true;
                    }
                }else{
                    if (i>0){
                        pts.add(new Point(pNew.get(i-1).x,pNew.get(i-1).y));
                    }

                }

            }else{
                if (eklendi){
                    pts.add(new Point(pNew.get(i-1).x,pNew.get(i-1).y));
                    eklendi=false;
                }
            }

        }

        pts.add(new Point(pNew.get(pNew.size()-1).x,pNew.get(pNew.size()-1).y));

        //pts.print();

        return pts;
    }

    public Path toNormalizedLine3(BufferedImage img, MapColorModel mcm, int horizontalThreshold, int verticalThreshold){
        return toNormalizedLine3(img, mcm, new Threshold(horizontalThreshold, verticalThreshold));
    }

    public Path toNormalizedLine3(BufferedImage img, MapColorModel mcm, Threshold threshold){

        Path pts=toNormalizedLine2(img, mcm, threshold);

        pts.simplify(img,mcm);

        return pts;

    }

    public void simplify(BufferedImage img, MapColorModel mcm){

        double angle=-1,newAngle=0;

        for (int i=0;i<points.size()-1;i++){

            newAngle=Util.calculateLineAngle(points.get(i), points.get(i+1));
            double dangle=Math.abs(angle-newAngle);
            //if (Util.seeEachOther(points.get(i), points.get(i+1), img, Color.white) && (dangle<30)){
            if (Util.seeEachOther(points.get(i), points.get(i+1), img, mcm) && (dangle<30)){
                points.remove(i);
                i--;
            }
            angle=newAngle;
        }

    }

    public void printAngles(){

        for (int i=0;i<points.size()-1;i++){

            System.out.println(points.get(i).x+","+points.get(i).y+";"+points.get(i+1).x+","+points.get(i+1).y+"->"+Util.calculateLineAngle(points.get(i), points.get(i+1)));

        }

    }
    
    public double getLineAngle(Point p){
        
        double ilk=100000;
        int index=0;
        for (int i=0;i<points.size();i++){
            
            int dx=p.x-points.get(i).x;
            int dy=p.y-points.get(i).y;
            double d=Math.sqrt(dx*dx+dy*dy);
            if (d<ilk){
                ilk=d;
                index=i;
            }
        }
        return Util.calculateLineAngle(points.get(index-1), p);
    }

    public void printSlopes(){

        for (int i=0;i<points.size()-1;i++){
            double dx,dy;
            dx=points.get(i+1).getX() - points.get(i).getX();
            dy=points.get(i+1).getY() - points.get(i).getY();
            if (dx!=0){
                double m=dy/dx;
                System.out.println(m);
            }else{
                System.out.println("inf");
            }


        }

    }

    public ArrayList<Junction> findJunctions(BufferedImage img, MapColorModel mcm, int horizontalThreshold, int verticalThreshold){
        return findJunctions(img, mcm, new Threshold(horizontalThreshold, verticalThreshold));
    }

    public ArrayList<Junction> findJunctions(BufferedImage img, MapColorModel mcm, Threshold threshold){  //yarım daire tarama ile

        Threshold newTre=threshold.multiply(1.2, 1.2);

        ArrayList<Junction> a=new ArrayList<Junction>();

        for(int i=0;i<points.size()-1;i++){

            Point pt1=points.get(i);
            Point pt2=points.get(i+1);
            double ang=Util.calculateLineAngle(pt1, pt2)+90;

            if (pt1.x==pt2.x){

                int fark=Math.abs(pt1.y-pt2.y);
                int isaret=(int)Math.signum(pt1.y-pt2.y);
                for (int dx=0;dx<fark;dx++){
                    int newX=pt1.x;
                    int newY=(int)Math.round(pt1.y-isaret*dx);
                    //Dikme çekiliyor
                    Junction j=getJunctions(img, mcm, newX, newY, ang, newTre);
                    if (j.getNumberOfRoutes()>1){
                        //System.out.println(a.size()+":"+j);
                        a.add(j);
                    }
                }

            }else if (pt1.y==pt2.y){

                int fark=Math.abs(pt1.x-pt2.x);
                int isaret=(int)Math.signum(pt1.x-pt2.x);
                for (int dx=0;dx<fark;dx++){
                    int newX=(int)Math.round(pt1.x-isaret*dx);
                    int newY=pt1.y;
                    //Dikme çekiliyor
                    Junction j=getJunctions(img, mcm, newX, newY, ang, newTre);
                    if (j.getNumberOfRoutes()>1){
                        //System.out.println(a.size()+":"+j);
                        a.add(j);
                    }
                }

            }else{

                double m=(double)(pt1.y-pt2.y)/(double)(pt1.x-pt2.x);
                double step=Math.abs(1/m);

                if (step>1) {step=1;}
                int fark=Math.abs(pt1.x-pt2.x);
                int isaret=(int)Math.signum(pt1.x-pt2.x);
                for (double dx=0;dx<fark;dx=dx+step){

                    int newX=(int)Math.round(pt1.x-isaret*dx);
                    int newY=(int)Math.round(pt1.y-isaret*m*dx);
                    Junction j=getJunctions(img, mcm, newX, newY, ang, newTre);
                    if (j.getNumberOfRoutes()>1){
                        //System.out.println(a.size()+":"+j);
                        a.add(j);
                    }

                }
            }

        }

        eliminateNoise(a);
        return a;
    }

    private void eliminateNoise(ArrayList<Junction> a){

        int ilk=-1,son=-1;
        ArrayList<Junction> temp=new ArrayList<Junction>();
        for(int i=0;i<a.size()-1;i++){

            Point pt1=a.get(i).getPoint();
            Point pt2=a.get(i+1).getPoint();
            int dx=Math.abs(pt1.x-pt2.x);
            int dy=Math.abs(pt1.y-pt2.y);

            if (dx<=2 && dy<=2){
                if (ilk<0){
                    ilk=i;
                }
            }else{
                if (ilk<0 && son<0){
                    //temp.add(a.get(i));
                    //System.out.println(i);
                }else if (son<0){
                    son=i;
                    temp.add(a.get((ilk+son)/2));
                    //System.out.println((ilk+son)/2);
                    ilk=-1;
                    son=-1;

                }
            }
        }

        if (!a.isEmpty() && ilk>=0){
            son=a.size()-1;
            temp.add(a.get((ilk+son)/2));
            //System.out.println((ilk+son)/2);
        }
        a.clear();
        a.addAll(temp);

    }

    public Junction getJunctions(BufferedImage img, MapColorModel mcm, int x, int y, double ang, int horizontalThreshold, int verticalThreshold){
        return getJunctions(img, mcm, x, y, ang, new Threshold(horizontalThreshold, verticalThreshold));
    }

    public Junction getJunctions(BufferedImage img, MapColorModel mcm, int x, int y, double ang, Threshold threshold){

        Junction j=new Junction(new Point (x,y));

        int deg=0;
        int ilk=-1,son=-1;

        for (double i=ang;i>=ang-180;i--){
            double rad=Math.toRadians(i);
            double m=Math.tan(rad);
            boolean b=halfCircleJunctionCheck(img, mcm, x, y, m, threshold);
            if (b){ //açıklık varsa
                //temp.add(new Integer(deg));
                if (ilk<0){
                    ilk=deg;
                }
                son=deg;
            }else{
                if (ilk>=0 /*&& son>=0*/){
                    if (ilk==0){
                        //sıfırı yolun açısı olarak belirle
                        j.addRouteDegree(180);
                    }else{
                        //(ilk+son)/2 yi yolun açısı olarak belirle
                        //if (Math.abs(ilk-son)>12){
                            j.addRouteDegree(180-((ilk+son)/2));
                        //}

                    }
                    ilk=-1;
                    son=-1;
                }
            }
            deg++;
        }

        if (son==180){
            j.addRouteDegree(0);
        }

        return j;

    }

    private boolean halfCircleJunctionCheck(BufferedImage img, MapColorModel mcm, int x, int y, double m, int horizontalThreshold, int verticalThreshold){
        return halfCircleJunctionCheck(img, mcm, x, y, m, new Threshold(horizontalThreshold, verticalThreshold));
    }

    private boolean halfCircleJunctionCheck(BufferedImage img, MapColorModel mcm, int x, int y, double m, Threshold threshold){

        double step=Math.abs(1/m);

        if (step>1) {step=1;}
        int fark=(threshold.getHorizontal()+threshold.getVertical())/2;
        //int fark=(int)((threshold.getHorizontal()*2+threshold.getVertical()*2)/Math.sqrt(2));

        for (double dx=0;dx<fark;dx=dx+step){

            int newX=(int)Math.round(x-dx);
            int newY=(int)Math.round(y+m*dx);

            if (newX<0){
                newX=0;
            }else if (newX>=img.getWidth()){
                newX=img.getWidth()-1;
            }

            if (newY<0){
                newY=0;
            }else if (newY>=img.getHeight()){
                newY=img.getHeight()-1;
            }

            Point p=new Point(newX,newY);

            //if (img.getRGB(newX, newY)==Color.BLACK.getRGB()){
            if (!mcm.isOnTrack(img.getRGB(newX, newY))){
                return false;
            }
            //if (p.distance(x,y)>tres){
            if (p.distance(x,y)>fark){
                break;
            }

        }
        return true;
    }

    public int getDeltaY(){

        int deltay=0;
        for(int i=0;i<points.size()-1;i++){

            Point pt1=points.get(i);
            Point pt2=points.get(i+1);

            if (pt1.x==pt2.x){
                deltay++;
            }

        }

        return deltay;
    }

    public int getDeltaX(){

        int deltax=0;
        for(int i=0;i<points.size()-1;i++){

            Point pt1=points.get(i);
            Point pt2=points.get(i+1);

            if (pt1.y==pt2.y){
                deltax++;
            }

        }

        return deltax;
    }

    public void normalize(BufferedImage img, MapColorModel mcm, int horizontalThreshold, int verticalThreshold){
        normalize(img, mcm, new Threshold(horizontalThreshold, verticalThreshold));
    }

    public void normalize(BufferedImage img, MapColorModel mcm, Threshold threshold){

        for(int i=0;i<points.size();i++){

            //boolean b=normalizeHelper(img,points.get(i),Color.BLACK, threshold);
            boolean b=normalizeHelper(img,points.get(i),mcm, threshold);
            if (!b){
                points.remove(i);
                i--;
            }

        }

        if (points.size()>1){
            for(int i=0;i<points.size()-1;i++){
                Point pt1=points.get(i);
                Point pt2=points.get(i+1);
                if (pt1.x==pt2.x && pt1.y==pt2.y){
                    points.remove(i+1);
                    i--;

                }
            }
        }

    }

    public void normalizeWithInterpolation(BufferedImage img, MapColorModel mcm, int horizontalThreshold, int verticalThreshold){
        normalizeWithInterpolation(img, mcm, new Threshold(horizontalThreshold, verticalThreshold));
    }

    public void normalizeWithInterpolation(BufferedImage img, MapColorModel mcm, Threshold threshold){
        //yaz();
        for(int i=0;i<points.size();i++){

            //boolean b=normalizeHelper(img,points.get(i),Color.BLACK, threshold);
            boolean b=normalizeHelper(img,points.get(i),mcm, threshold);
            if (!b){
                points.remove(i);
                i--;
            }

        }

        for(int i=0;i<points.size()-1;i++){
            Point pt1=points.get(i);
            Point pt2=points.get(i+1);

            if (Math.abs(pt1.x-pt2.x)>1 || Math.abs(pt1.y-pt2.y)>1){
                double m=(double)(pt1.y-pt2.y)/(double)(pt1.x-pt2.x);

                double step=Math.abs(1/m);
                if (step>1) {step=1;}
                int fark=Math.abs(pt1.x-pt2.x);
                int isaret=(int)Math.signum(pt1.x-pt2.x);
                for (double dx=0;dx<fark;dx=dx+step){
                    i++;
                    int newX=(int)Math.round(pt1.x-isaret*dx);
                    int newY=(int)Math.round(pt1.y-isaret*m*dx);
                    points.add(i, new Point(newX,newY));
                    //System.out.println(newX+" "+newY+" "+dx+" "+fark);
                }
                if(pt1.x==pt2.x && Math.abs(pt1.y-pt2.y)>1){
                    fark=Math.abs(pt1.y-pt2.y);
                    isaret=(int)Math.signum(pt1.y-pt2.y);
                    for (int dx=0;dx<fark;dx++){
                        i++;
                        int newX=pt1.x;
                        int newY=(int)Math.round(pt1.y-isaret*dx);
                        points.add(i, new Point(newX,newY));
                    }
                }

            }

////               todo aynı noktaları çıkar
        }
        if (points.size()>1){
            for(int i=0;i<points.size()-1;i++){
                Point pt1=points.get(i);
                Point pt2=points.get(i+1);
                if (pt1.x==pt2.x && pt1.y==pt2.y){
                    points.remove(i+1);
                    i--;

                }
            }
        }


    }

    public void centerPath(BufferedImage img, MapColorModel mcm, int horizontalThreshold, int verticalThreshold){
        centerPath(img, mcm, new Threshold(horizontalThreshold, verticalThreshold));
    }

    public void centerPath(BufferedImage img, MapColorModel mcm, Threshold threshold){

        for(int i=0;i<points.size()-1;i++){
            Point pt1=points.get(i);
            Point pt2=points.get(i+1);

            if (pt1.x != pt2.x){
                double m=(pt1.getY()-pt2.getY())/(pt1.getX()-pt2.getX());
                double step=Math.abs(1/m);
                //int c=0;
                if (step>1) {step=1;}
                int fark=Math.abs(pt1.x-pt2.x);
                int isaret=(int)Math.signum(pt1.x-pt2.x);
                for (double dx=0;dx<fark;dx=dx+step){
                    //c++;
                    int newX=(int)Math.round(pt1.x-isaret*dx);
                    int newY=(int)Math.round(pt1.y-isaret*m*dx);
                    Point pt=new Point(newX,newY);

                    //boolean b=normalizeHelper2(img, pt, Color.BLACK, threshold);
                    boolean b=normalizeHelper2(img, pt, mcm, threshold);
                    //if (c>=1){
                        if (b){
                            pt1.translate(pt.x-newX, pt.y-newY);
                            break;
                        }
                    //}
                }

            }else{
                //int c=0;
                int fark=Math.abs(pt1.y-pt2.y);
                int isaret=(int)Math.signum(pt1.y-pt2.y);
                for (int dx=0;dx<fark;dx++){
                    //c++;
                    int newX=pt1.x;
                    int newY=(int)Math.round(pt1.y-isaret*dx);
                    Point pt=new Point(newX,newY);
                    //boolean b=normalizeHelper2(img, pt, Color.BLACK, threshold);
                    boolean b=normalizeHelper2(img, pt, mcm, threshold);
                    //if (c>=1){
                        if (b){
                            pt1.translate(pt.x-newX, pt.y-newY);
                            break;
                        }
                    //}
                }

            }

            if (pt1.x != pt2.x){
                double m=(pt1.getY()-pt2.getY())/(pt1.getX()-pt2.getX());
                double step=Math.abs(1/m);
                //int c=0;
                if (step>1) {step=1;}
                int fark=Math.abs(pt1.x-pt2.x);
                int isaret=(int)Math.signum(pt1.x-pt2.x);
                for (double dx=fark;dx>0;dx=dx-step){
                    //c++;
                    int newX=(int)Math.round(pt1.x-isaret*dx);
                    int newY=(int)Math.round(pt1.y-isaret*m*dx);
                    Point pt=new Point(newX,newY);

                    //boolean b=normalizeHelper2(img, pt, Color.BLACK, threshold);
                    boolean b=normalizeHelper2(img, pt, mcm, threshold);
                    //if (c>=1){
                        if (b){
                            pt2.translate(pt.x-newX, pt.y-newY);
                            break;
                        }
                    //}
                }

            }else{
                //int c=0;
                int fark=Math.abs(pt1.y-pt2.y);
                int isaret=(int)Math.signum(pt1.y-pt2.y);
                for (int dx=fark;dx>0;dx--){
                    //c++;
                    int newX=pt1.x;
                    int newY=(int)Math.round(pt1.y-isaret*dx);
                    Point pt=new Point(newX,newY);
                    //boolean b=normalizeHelper2(img, pt, Color.BLACK, threshold);
                    boolean b=normalizeHelper2(img, pt, mcm, threshold);
                    //if (c>=1){
                        if (b){
                            pt2.translate(pt.x-newX, pt.y-newY);
                            break;
                        }
                    //}
                }

            }

        }

    }

    public boolean normalizeHelper(BufferedImage img, Point p, MapColorModel mcm, int horizontalThreshold, int verticalThreshold){
        return normalizeHelper(img, p, mcm, new Threshold(horizontalThreshold, verticalThreshold));
    }

    public boolean normalizeHelper(BufferedImage img, Point p, MapColorModel mcm, Threshold threshold){
        //Bir noktayı sol, sağ, yukarı ve aşağıdan ortalayan yardımcı fonk.
        int x=p.x;
        int y=p.y;
        int left=0,right=0;
        boolean b1=false,b2=false,b3=false,b4=false;
        //System.out.print(x+","+y+"->");
        for (int i=0;i<threshold.getHorizontal();i++){

            if (x-i>=0 && mcm.isOnTrack(img.getRGB(x-i, y))){
                left++;
            }else{
                b1=true;
                break;
            }

        }

        for (int i=0;i<threshold.getHorizontal();i++){

            if (x+i<img.getWidth() && mcm.isOnTrack(img.getRGB(x+i, y))){
                right++;
            }else{
                b2=true;
                break;
            }

        }
        int dx=0;
        if (b1 && b2){
            int avg=(left+right)/2;
            dx=avg-left;
        }

        //p.translate(dx/2, 0);

        int up=0,down=0;

        for (int i=0;i<threshold.getVertical();i++){

            if (y-i>=0 && mcm.isOnTrack(img.getRGB(x, y-i))){
                up++;
            }else{
                b3=true;
                break;
            }

        }

        for (int i=0;i<threshold.getVertical();i++){

            if (y+i<img.getHeight() && mcm.isOnTrack(img.getRGB(x, y+i))){
                down++;
            }else{
                b4=true;
                break;
            }

        }
        int dy=0;
        if (b3 && b4){
            int avg2=(up+down)/2;
            dy=avg2-up;
        }

        double deg;
        if (dx==0 || dy==0){
            deg=0;
            int ddy=dy;
            int ddx=dx;
            p.translate(ddx, ddy);
            //System.out.println(ddx+","+ddy);
        }else{
            deg=Math.atan(((double)(dy)/(double)(dx)));
//                int ddx=(int)Math.round(dx*Math.sin(deg));
//                int ddy=(int)Math.round(dx*Math.cos(deg));
            int ddx=(int)Math.round(dx*Math.sin(deg)*Math.cos(Math.toRadians(90)-deg));
            int ddy=(int)Math.round(dx*Math.sin(deg)*Math.sin(Math.toRadians(90)-deg));
            p.translate(ddx, ddy);
            //System.out.println(ddx+","+ddy);
        }


        if ((b1 && b2) || (b3 && b4)){
            return true;
        }else{
            return false;
        }

    }

    public boolean normalizeHelper2(BufferedImage img, Point p, MapColorModel mcm, int horizontalThreshold, int verticalThreshold){
        return normalizeHelper2(img, p, mcm, new Threshold(horizontalThreshold, verticalThreshold));
    }

    public boolean normalizeHelper2(BufferedImage img, Point p, MapColorModel mcm, Threshold threshold){
        //Bir noktayı sol, sağ, yukarı ve aşağıdan ortalayan yardımcı fonk.
        int x=p.x;
        int y=p.y;
        int left=0,right=0;
        boolean b1=false,b2=false,b3=false,b4=false;
        //System.out.print(x+","+y+"->");
        for (int i=0;i<threshold.getHorizontal();i++){

            //if (x-i>=0 && img.getRGB(x-i, y)!=c.getRGB()){
            if (x-i>=0 && mcm.isOnTrack(img.getRGB(x-i, y))){
                left++;
            }else{
                b1=true;
                break;
            }

        }

        for (int i=0;i<threshold.getHorizontal();i++){

            if (x+i<img.getWidth() && mcm.isOnTrack(img.getRGB(x+i, y))){
                right++;
            }else{
                b2=true;
                break;
            }

        }
        int dx=0;
        if (b1 && b2){
            int avg=(left+right)/2;
            dx=avg-left;
        }

        //p.translate(dx/2, 0);

        int up=0,down=0;

        for (int i=0;i<threshold.getVertical();i++){

            if (y-i>=0 && mcm.isOnTrack(img.getRGB(x, y-i))){
                up++;
            }else{
                b3=true;
                break;
            }

        }

        for (int i=0;i<threshold.getVertical();i++){

            if (y+i<img.getHeight() && mcm.isOnTrack(img.getRGB(x, y+i))){
                down++;
            }else{
                b4=true;
                break;
            }

        }
        int dy=0;
        if (b3 && b4){
            int avg2=(up+down)/2;
            dy=avg2-up;
        }

        double deg;
        if (dx==0 || dy==0){
            deg=0;
            int ddy=dy;
            int ddx=dx;
            p.translate(ddx, ddy);
            //System.out.println(ddx+","+ddy);
        }else{
            deg=Math.atan(((double)(dy)/(double)(dx)));
//                int ddx=(int)Math.round(dx*Math.sin(deg));
//                int ddy=(int)Math.round(dx*Math.cos(deg));
            int ddx=(int)Math.round(dx*Math.sin(deg)*Math.cos(Math.toRadians(90)-deg));
            int ddy=(int)Math.round(dx*Math.sin(deg)*Math.sin(Math.toRadians(90)-deg));
            Point temp=new Point(p.x,p.y);
            temp.translate(ddx, ddy);
            if (normalizeCheck(img, temp, mcm, threshold)){
                p.translate(ddx, ddy);
            }else{
                p.translate(dx, dy);
            }

            //System.out.println(ddx+","+ddy);
        }

        if ((b1 && b2) || (b3 && b4)){
            return true;
        }else{
            return false;
        }

    }

    public boolean normalizeCheck(BufferedImage img, Point p, MapColorModel mcm, Threshold threshold){

        int x=p.x;
        int y=p.y;
        int left=0,right=0;
        boolean b1=false,b2=false,b3=false,b4=false;
        //System.out.print(x+","+y+"->");
        for (int i=0;i<threshold.getHorizontal();i++){

            //if (x-i>=0 && img.getRGB(x-i, y)!=c.getRGB()){
            if (x-i>=0 && mcm.isOnTrack(img.getRGB(x-i, y))){
                left++;
            }else{
                b1=true;
                break;
            }

        }

        for (int i=0;i<threshold.getHorizontal();i++){

            if (x+i<img.getWidth() && mcm.isOnTrack(img.getRGB(x+i, y))){
                right++;
            }else{
                b2=true;
                break;
            }

        }
        int dx=0;
        if (b1 && b2){
            int avg=(left+right)/2;
            dx=Math.abs(avg-left);
        }

        //p.translate(dx/2, 0);

        int up=0,down=0;

        for (int i=0;i<threshold.getVertical();i++){

            if (y-i>=0 && mcm.isOnTrack(img.getRGB(x, y-i))){
                up++;
            }else{
                b3=true;
                break;
            }

        }

        for (int i=0;i<threshold.getVertical();i++){

            if (y+i<img.getHeight() && mcm.isOnTrack(img.getRGB(x, y+i))){
                down++;
            }else{
                b4=true;
                break;
            }

        }
        int dy=0;
        if (b3 && b4){
            int avg2=(up+down)/2;
            dy=Math.abs(avg2-up);
        }

        if (dx<=2 && dy<=2){
            return true;
        }

        return false;

    }

}
