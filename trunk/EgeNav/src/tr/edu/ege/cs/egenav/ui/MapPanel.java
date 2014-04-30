/*
 * MapPanel.java
 *
 * Created on 17.Nis.2014, 13:56:36
 */
package tr.edu.ege.cs.egenav.ui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import tr.edu.ege.cs.egenav.Location;
import tr.edu.ege.cs.egenav.MapDownloader;
import tr.edu.ege.cs.egenav.MapURL;

/**
 *
 * @author Özgün Yılmaz
 */
public class MapPanel extends javax.swing.JPanel implements MouseListener{

    private MapURL mapurl=null;
    private BufferedImage img;
    private InputStream in;
    private double x1,y1,x2,y2;
    private boolean enforceCenter=false;
    private ArrayList<NavigationInfo> history=new ArrayList<NavigationInfo>(); //todo bu liste ayrı bir sınıfta tutulabilir. İlgili metotlar yazılır ve daha temiz kodlama olur.
    private int heading=-1,speed=-1;
    private Arrow arrow=new Arrow();
    private LineStyle routeLineStyle=new LineStyle();
    
    
    /** Creates new form MapPanel */
    public MapPanel() {
        initComponents();
        enableDragRefresh();
    }
    
    public MapPanel(MapURL mapurl){
        this.mapurl=mapurl;
        img=MapDownloader.downloadMap(mapurl.getAbsoluteURLString());
    }
    
    public void clearNavigationHistory(){
        history.clear();
    }
    
    public void refreshMap(){
        img=MapDownloader.downloadMap(mapurl.getAbsoluteURLString());
        repaint();
    }

    public boolean isCenterEnforced() {
        return enforceCenter;
    }

    public void setEnforceCenter(boolean enforceCenter) {
        this.enforceCenter = enforceCenter;
    }

    public int getBearing() {
        return heading;
    }

    public int getSpeed() {
        return speed;
    }
    
    public MapURL getMapUrl() {
        return mapurl;
    }
    
    public int getMapZoom(){
        return mapurl.getZoom();
    }
    
    public Arrow getArrow() {
        return arrow;
    }

    public void setArrow(Arrow arrow) {
        this.arrow = arrow;
    }

    public LineStyle getRouteLineStyle() {
        return routeLineStyle;
    }

    public void setRouteLineStyle(LineStyle routeLineStyle) {
        this.routeLineStyle = routeLineStyle;
    }
    
    //------------------------------------------------------------
    public void setMapUrl(MapURL mapurl) {
        
        this.mapurl = mapurl;
    }
    
    public void setMapZoom(int z){
        
        mapurl.setZoom(z);
    }
    
    public boolean incrementZoom(){
        
        return mapurl.incrementZoom();
    }
    
    public boolean decrementZoom(){
        
        return mapurl.incrementZoom();
    }
    
    //-----------------------------------------------------------
    
    public void updateLocation(Location loc){
        
        long timestamp=Calendar.getInstance().getTimeInMillis();
        double distance;
        if (history.isEmpty()){
            distance=0;
        }else{
            NavigationInfo ni=history.get(history.size()-1);
            distance=ni.getLocation().getDistanceTo(loc);
        }
        //todo pikselleri de hesaplayıp navinfonun içine koy
        history.add(new NavigationInfo(loc,timestamp,distance));
        if (enforceCenter){
            mapurl.setLocation(loc.clone());
        }else{
            //todo harita dışına çıkılmışsa harita güncellenmeli
        }
        
    }
    
    public final void enableDragRefresh(){

        setCursor(new Cursor(Cursor.MOVE_CURSOR));
        addMouseListener(this);
    }
    
    public final void disableDragRefresh(){

        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        removeMouseListener(this);
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;
        g2d.drawImage(img, 0, 0, null);
        //**************************draw route
        
        if (history.size()>1){
            
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(getRouteLineStyle().getColor());
            if (getRouteLineStyle().getStroke()!=null){
                g2d.setStroke(getRouteLineStyle().getStroke());
            }
            g2d.setComposite(getRouteLineStyle().getComposite());
            //...
            NavigationInfo ng1,ng2;
            Point p1=null,p2=null;

            for (int i=0;i<history.size()-1;i++){

                ng1=history.get(i);
                ng2=history.get(i+1);
                p1=mapurl.getPixelOnMap(ng1.getLocation().getLatitude(),ng1.getLocation().getLongitude());
                p2=mapurl.getPixelOnMap(ng2.getLocation().getLatitude(),ng2.getLocation().getLongitude());
                g2d.drawLine((int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY());

            }

            //*****************************

            //***************************draw arrow
            g2d.setColor(getArrow().getLineStyle().getColor());
            if (getArrow().getLineStyle().getStroke()!=null){
                g2d.setStroke(getArrow().getLineStyle().getStroke());
            }
            g2d.setComposite(getArrow().getLineStyle().getComposite());
            //...
            int x2=(int)p2.getX(),y2=(int)p2.getY();

            g2d.translate(x2, y2);
            
            g2d.rotate(calcArrowDegree(p1,p2)+Math.PI/2);

            g2d.fill(getArrow().getShape());
        }
        
    }
    
    
    
    private double calcArrowDegree(Point p1, Point p2){
        int x1=(int)p1.getX(),y1=(int)p1.getY(),x2=(int)p2.getX(),y2=(int)p2.getY();
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
                    System.out.print(degree);
                }else{
                    degree=Math.atan((y2-y1)/(x2-x1))+Math.toRadians(180);
                }

            }
            return degree;
    }
    
    @Override
    public Dimension getPreferredSize() {
        if (mapurl == null) {
            return new Dimension(500,500);
        } else {
            return new Dimension(mapurl.getMapSize().getHorizontal(), mapurl.getMapSize().getVertical());
       }
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
