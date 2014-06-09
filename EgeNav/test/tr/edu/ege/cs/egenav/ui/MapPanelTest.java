/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.ege.cs.egenav.ui;

import tr.edu.ege.cs.egenav.MapSize;
import tr.edu.ege.cs.egenav.Location;
import tr.edu.ege.cs.egenav.GeoPoint;
import tr.edu.ege.cs.egenav.GSMMapURL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Özgün Yılmaz
 */
public class MapPanelTest {
    
    public MapPanelTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void enforceCenterTest() {
    
        GSMMapURL m=new GSMMapURL();
        m.setCenter(new Location(new GeoPoint(40,30)));
        
        m.setZoom(6);
        m.setMapSize(new MapSize(500,500));
        
        MapPanel mp=new MapPanel(m);
        mp.refreshMap();
        assertEquals(mp.getMapUrl().getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/staticmap?center=40.0,30.0&zoom=6&size=500x500&sensor=false");
        
        mp.updateLocation(new Location(42,32));
        assertEquals(mp.getMapUrl().getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/staticmap?center=40.0,30.0&zoom=6&size=500x500&sensor=false");
        
        mp.setEnforceCenter(true);
        mp.updateLocation(new Location(44,34));
        assertEquals(mp.getMapUrl().getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/staticmap?center=44.0,34.0&zoom=6&size=500x500&sensor=false");
    }
    
    @Test
    public void dragMapTest() {
        
        GSMMapURL m=new GSMMapURL();
        m.setCenter(new Location(new GeoPoint(40,30)));
        
        m.setZoom(6);
        m.setMapSize(new MapSize(500,500));
        
        MapPanel mp=new MapPanel(m);
        mp.dragMap(30, 30, 80, 60);
        
        assertEquals(mp.getMapUrl().getLocation().getLatitude(),40.503091752950915,0.0001);
        assertEquals(mp.getMapUrl().getLocation().getLongitude(),28.901367411017414,0.0001);
        assertEquals(mp.getMapUrl().getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/staticmap?center=40.503091752950915,28.901367411017414&zoom=6&size=500x500&sensor=false");
    }
    
    @Test
    public void updateLocationTest() {
        
        GSMMapURL m=new GSMMapURL();
        m.setCenter(new Location(new GeoPoint(40,30)));
        
        m.setZoom(6);
        m.setMapSize(new MapSize(500,500));
        
        MapPanel mp=new MapPanel(m);
        
        mp.updateLocation(new Location(40,30));
        mp.updateLocation(new Location(40,44));
        
        assertEquals(mp.getMapUrl().getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/staticmap?center=40.0,40.98632834851742&zoom=6&size=500x500&sensor=false");
        
    }
    
    @Test
    public void updateLocationTest2() {
        
        GSMMapURL m=new GSMMapURL();
        m.setCenter(new Location(new GeoPoint(40,30)));
        
        m.setZoom(6);
        m.setMapSize(new MapSize(500,500));
        
        MapPanel mp=new MapPanel(m);
        
        mp.updateLocation(new Location(40,30));
        mp.updateLocation(new Location(40,15));
        
        assertEquals(mp.getMapUrl().getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/staticmap?center=40.0,19.013672098517418&zoom=6&size=500x500&sensor=false");
        
    }
    
    @Test
    public void updateLocationTest3() {
        
        GSMMapURL m=new GSMMapURL();
        m.setCenter(new Location(new GeoPoint(40,30)));
        
        m.setZoom(6);
        m.setMapSize(new MapSize(500,500));
        
        MapPanel mp=new MapPanel(m);
        
        mp.updateLocation(new Location(40,30));
        mp.updateLocation(new Location(50,30));
        
        assertEquals(mp.getMapUrl().getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/staticmap?center=47.89217139794361,30.0&zoom=6&size=500x500&sensor=false");
        
    }
    
    @Test
    public void updateLocationTest4() {
        
        GSMMapURL m=new GSMMapURL();
        m.setCenter(new Location(new GeoPoint(40,30)));
        
        m.setZoom(6);
        m.setMapSize(new MapSize(500,500));
        
        MapPanel mp=new MapPanel(m);
        
        mp.updateLocation(new Location(40,30));
        mp.updateLocation(new Location(30,30));
        
        assertEquals(mp.getMapUrl().getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/staticmap?center=31.07851337646032,30.0&zoom=6&size=500x500&sensor=false");
        
    }
    
    @Test
    public void updateLocationTest5() {
        
        GSMMapURL m=new GSMMapURL();
        m.setCenter(new Location(new GeoPoint(40,30)));
        
        m.setZoom(6);
        m.setMapSize(new MapSize(500,500));
        
        MapPanel mp=new MapPanel(m);
        
        mp.updateLocation(new Location(40,30));
        mp.updateLocation(new Location(30,15));
        
        assertEquals(mp.getMapUrl().getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/staticmap?center=31.07851337646032,19.013672098517418&zoom=6&size=500x500&sensor=false");
        
    }
}
