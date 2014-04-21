/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MapPanel.java
 *
 * Created on 17.Nis.2014, 13:56:36
 */
package tr.edu.ege.cs.egenav.ui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import tr.edu.ege.cs.egenav.Location;
import tr.edu.ege.cs.egenav.MapDownloader;
import tr.edu.ege.cs.egenav.MapURL;

/**
 *
 * @author Özgün Yılmaz
 */
public class MapPanel extends javax.swing.JPanel {

    private MapURL mapurl=null;
    private BufferedImage img;
    private InputStream in;
    private double x1,y1,x2,y2;
    private boolean enforceCenter;

    
    
    /** Creates new form MapPanel */
    public MapPanel() {
        initComponents();
        setCursor(new Cursor(Cursor.MOVE_CURSOR));
    }
    
    public MapPanel(MapURL mapurl){
        this.mapurl=mapurl;
        img=MapDownloader.downloadMap(mapurl.getAbsoluteURLString());
    }
    
    public void refreshMap(){
        img=MapDownloader.downloadMap(mapurl.getAbsoluteURLString());
        repaint();
    }

    public MapURL getMapUrl() {
        return mapurl;
    }

    //------------------------------------------------------------
    public void setMapUrl(MapURL mapurl) {
        this.mapurl = mapurl;
    }
    //todo geçmişe eklenecek
    public int getMapZoom(){
        return mapurl.getZoom();
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
    
    public Location getMapLocation(){
        return mapurl.getLocation();
    }
    
    public void setMapLocation(Location loc){
        mapurl.setLocation(loc);
    }
    //-----------------------------------------------------------
    
    public void enableDragRefresh(){
        //TODO 
    }
    
    public void disableDragRefresh(){
        //TODO 
    }
    
    
    @Override
    public void paint(Graphics g) {

        g.drawImage(img, 0, 0, null);
    }
    
    @Override
    public Dimension getPreferredSize() {
        if (mapurl == null) {
            return new Dimension(500,500);
        } else {
            return new Dimension(mapurl.getMapSize().getHorizantal(), mapurl.getMapSize().getVertical());
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
}
