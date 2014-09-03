/*
 * MapPanel.java
 *
 * Created on 17.Nis.2014, 13:56:36
 */
package tr.edu.ege.cs.egenav.ui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import tr.edu.ege.cs.egenav.direction.Direction;
import tr.edu.ege.cs.egenav.direction.Directions;
import tr.edu.ege.cs.egenav.GeoPoint;
import tr.edu.ege.cs.egenav.Location;
import tr.edu.ege.cs.egenav.MapDownloader;
import tr.edu.ege.cs.egenav.MapURL;
import tr.edu.ege.cs.egenav.MercatorProjection;
import tr.edu.ege.cs.egenav.mapcache.MapCache;

/**
 *
 * @author Özgün Yılmaz
 */
public class MapPanel extends javax.swing.JPanel implements MouseListener{

    //cache desteklenecek mi? için boolean
    //todo cache haritaların bulunduğu dizin için string ve getter setter lar
    //Eğer bu string set edilmezse local path te çalışılır.
    private MapURL mapurl=null;
    private BufferedImage img;
    private int x1,y1,x2,y2;
    private boolean enforceCenter=false;
    private Navigation navigation=new Navigation();
    
    private Arrow arrow=new Arrow();
    private LineStyle directionLineStyle=new LineStyle(Color.RED,new BasicStroke(5,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL),AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5F));
    private LineStyle routeLineStyle=new LineStyle();
    
    private Direction direction;
    private NavigationInformation navPanel;
    
    private MapCache cache=null;
    
    /** Creates new form MapPanel */
    public MapPanel() {
        initComponents();
        enableDragRefresh();
    }
    
    public MapPanel(MapURL mapurl){
        this();
        this.mapurl=mapurl;
        //img=MapDownloader.downloadMap(mapurl.getAbsoluteURLString());
        //todo aşağıdaki satırdaki uyarıya bak.
      
    }
    
    public MapPanel(MapURL mapurl,MapCache cache){
        this();
        this.mapurl=mapurl;
        this.cache=cache;
        //img=MapDownloader.downloadMap(mapurl.getAbsoluteURLString());
        //todo aşağıdaki satırdaki uyarıya bak.
        
    }

    public NavigationInformation getNavPanel() {
        return navPanel;
    }

    public MapCache getMapCache() {
        return cache;
    }

    public void setMapCache(MapCache cache) {
        this.cache = cache;
    }
    
    public void setNavPanel(NavigationInformation navPanel) {
        this.navPanel = navPanel;
    }
    
    public void clearNavigationHistory(){
        navigation.clearHistory();
    }
    
    public void refreshMap(){
        
        if (cache==null){
            img=MapDownloader.downloadMap(mapurl.getAbsoluteURLString());
        }else{
            img=cache.getMap(mapurl);
        }
        
        //cache kullanılacaksa cachede mapurldeki harita indirilmeden oluşturulabilir mi diye kontrol et
        //Eğer oluşturulan harita null değilse yeniden indirme
        repaint();
    }

    public boolean isCenterEnforced() {
        return enforceCenter;
    }

    public void setEnforceCenter(boolean enforceCenter) {
        this.enforceCenter = enforceCenter;
    }

//    public int getHeading() {
//        return heading;
//    }
//
//    public int getSpeed() {
//        return speed;
//    }
    
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
        Point p=mapurl.getPixelOnMap(loc.getLatitude(),loc.getLongitude());
        //System.out.println(p);
        double distance;
        if (navigation.isEmpty()){
            distance=0;
        }else{
            NavigationPointInfo ni=navigation.getLastElement();
            distance=ni.getLocation().getDistanceTo(loc);
        }
        
        navigation.add(new NavigationPointInfo(loc,timestamp,distance,p));

        if (navPanel!=null){
            navPanel.setAverageSpeed(navigation.getAverageSpeed());
            navPanel.setHeading(Math.toDegrees(navigation.getHeading()));
            if (direction!=null){
                navPanel.setInstructions(direction.getInstructions(new GeoPoint(loc.getLatitude(),loc.getLongitude())));
            }else{
                navPanel.setInstructions("");
            }
            navPanel.setLatitude(loc.getLatitude());
            navPanel.setLongitude(loc.getLongitude());
            navPanel.setSpeed(navigation.getSpeed());
            navPanel.setTimeElapsed(navigation.getTimeElapsed());
            navPanel.setTotalDistance(navigation.getTotalDistance());
        }
        
        if (enforceCenter){
            
            mapurl.setLocation(loc.clone());
            if (navigation!=null){
                navigation.refreshPixelCoordinates(mapurl);
            }
            if (direction!=null){
                direction.refreshPixelCoordinates(mapurl);
            }
            //************************
            refreshMap();
        }else{
            //harita dışına çıkılmışsa harita güncellenmeli, çıkılmamışsa güncellenmemeli
            int hor=Directions.CONSTANT,ver=Directions.CONSTANT;
            if (p.x<0){
                hor=Directions.WEST;
            }else if (p.x>getMapUrl().getMapSize().getHorizontal()){
                hor=Directions.EAST;
            }
            if (p.y<0){
                ver=Directions.NORTH;
            }else if (p.y>getMapUrl().getMapSize().getVertical()){
                ver=Directions.SOUTH;
            }
            
            if (hor!=Directions.CONSTANT || ver!=Directions.CONSTANT){
                MapURL m=mapurl.getNeighborTile(ver, hor);
                setMapUrl(m);
                if (navigation!=null){
                    navigation.refreshPixelCoordinates(m);
                }
                //todo aşağıdaki satır yukarıdakiyle aynı mı olacak.
                //direction.refreshPixelCoordinates(mapurl);
                if (direction!=null){
                    direction.refreshPixelCoordinates(m);
                }
                //********************
                refreshMap();
                
            }else{
                repaint();
            }
            
        }
        //refreshMap();
        
    }
    
    public Location getLastLocation(){
        
        if (navigation.isEmpty()){
            return null;
        }else{
            return navigation.getLastElement().getLocation();
        }
        
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        if (direction!=null){
            direction.refreshPixelCoordinates(mapurl);
        }
        repaint();
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
        //**************************if set draw direction
        if (direction!=null){
            direction.drawPathOnMap(g2d, directionLineStyle);
        }
        //**************************draw route
        navigation.drawRoute(g2d,getRouteLineStyle());
        //***************************draw arrow
        navigation.drawDirectionArrow(g2d,getArrow());
        
   
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
    public void mousePressed(MouseEvent event) {
        x1 = event.getX();
        y1 = event.getY();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        x2 = event.getX();
	y2 = event.getY();
        dragMap(x1,y1,x2,y2);
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    public void dragMap(int x1,int y1,int x2,int y2){//abstract olmalı
        
        if (y1!=y2){
            double newLon=MercatorProjection.getLonByPixels(getMapUrl().getLocation().getLongitude(),x1-x2,getMapZoom());
            getMapUrl().getLocation().setLongitude(newLon);
        }
        
        if (x1!=x2){
            double newLat=MercatorProjection.getLatByPixels(getMapUrl().getLocation().getLatitude(),y1-y2,getMapZoom());
            getMapUrl().getLocation().setLatitude(newLat);
        }
        navigation.refreshPixelCoordinates(getMapUrl());
        if (direction!=null){
            direction.refreshPixelCoordinates(getMapUrl());
        }
        refreshMap();
        
    }
}
