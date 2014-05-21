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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import tr.edu.ege.cs.egenav.Direction;
import tr.edu.ege.cs.egenav.Directions;
import tr.edu.ege.cs.egenav.Location;
import tr.edu.ege.cs.egenav.MapDownloader;
import tr.edu.ege.cs.egenav.MapURL;
import tr.edu.ege.cs.egenav.MercatorProjection;

/**
 *
 * @author Özgün Yılmaz
 */
public class MapPanel extends javax.swing.JPanel implements MouseListener{

    private MapURL mapurl=null;
    private BufferedImage img;
    private int x1,y1,x2,y2;
    private boolean enforceCenter=false;
    //private ArrayList<NavigationPointInfo> history=new ArrayList<NavigationPointInfo>(); //bu liste ayrı bir sınıfta tutulabilir. İlgili metotlar yazılır ve daha temiz kodlama olur.
    private Navigation navigation=new Navigation();
    private int heading=-1,speed=-1;
    private Arrow arrow=new Arrow();
    private LineStyle routeLineStyle=new LineStyle();
    
    private Direction direction;
    //todo bulunan yerden bir yere yol tarifi
    //konum bilgisi güncellendikçe yol bulma bilgilerin (yardımcı metin) gösterilmesi
    //yol bulmadaki kordinatların haritada işaretlenmesi
    //travel mode, vs. değiştirilebilmeli
    
    /** Creates new form MapPanel */
    public MapPanel() {
        initComponents();
        enableDragRefresh();
    }
    
    public MapPanel(MapURL mapurl){
        this();
        this.mapurl=mapurl;
        img=MapDownloader.downloadMap(mapurl.getAbsoluteURLString());
    }
    
    public void clearNavigationHistory(){
        navigation.clearHistory();
    }
    
    public void refreshMap(){
        img=MapDownloader.downloadMap(mapurl.getAbsoluteURLString());
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

    public int getHeading() {
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
        Point p=mapurl.getPixelOnMap(loc.getLatitude(),loc.getLongitude());
        double distance;
        if (navigation.isEmpty()){
            distance=0;
        }else{
            NavigationPointInfo ni=navigation.getLastElement();
            distance=ni.getLocation().getDistanceTo(loc);
        }
        
        navigation.add(new NavigationPointInfo(loc,timestamp,distance,p));
        
        if (enforceCenter){
            
            mapurl.setLocation(loc.clone());
            navigation.refreshPixelCoordinates(mapurl);
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
                navigation.refreshPixelCoordinates(m);
                refreshMap();
            }
            
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
            double newLon=MercatorProjection.getLonByPixels(getMapUrl().getLocation().getLongitude(),y1-y2,getMapZoom());
            getMapUrl().getLocation().setLongitude(newLon);
        }
        
        if (x1!=x2){
            double newLat=MercatorProjection.getLonByPixels(getMapUrl().getLocation().getLatitude(),x1-x2,getMapZoom());
            getMapUrl().getLocation().setLatitude(newLat);
        }
        
        refreshMap();
        
    }
}
