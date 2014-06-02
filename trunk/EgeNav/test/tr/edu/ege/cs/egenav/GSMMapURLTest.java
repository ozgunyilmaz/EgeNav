/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.ege.cs.egenav;

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
public class GSMMapURLTest {
    
    public GSMMapURLTest() {
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
    public void getAbsoluteURLStringTest() {
    
        GSMMapURL map=new GSMMapURL();
        map.setCenter(new GeoPoint(30,40));
        map.setMapSize(new MapSize(500,500));
        map.setZoom(10);
        assertEquals(map.getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/staticmap?center=30.0,40.0&zoom=10&size=500x500&sensor=false");
        map.setSecure(true);
        assertEquals(map.getAbsoluteURLString(),"https://maps.googleapis.com/maps/api/staticmap?center=30.0,40.0&zoom=10&size=500x500&sensor=false");
        map.incrementZoom();
        assertEquals(map.getAbsoluteURLString(),"https://maps.googleapis.com/maps/api/staticmap?center=30.0,40.0&zoom=11&size=500x500&sensor=false");
    
    }
    
    @Test
    public void getAbsoluteURLStringTest2() {
        
        GSMMapURL map=new GSMMapURL();
        map.setCenter(new GeoPoint(30,40));
        map.setMapSize(new MapSize(500,500));
        map.setZoom(5);
        
        GSMPathStyle ps=new GSMPathStyle("0x0000ff","5");
        GSMPath path=new GSMPath(ps);
        path.addPathPoint(new GSMLocation(new GeoPoint(35,45)));
        path.addPathPoint(new GSMLocation(new GeoPoint(36,44)));
        path.addPathPoint(new GSMLocation(new GeoPoint(37,46)));
        path.addPathPoint(new GSMLocation(new GeoPoint(38,47)));
        map.addPath(path);
        
        
        GSMPathStyle ps2=new GSMPathStyle("0x00ff00","3");
        GSMPath path2=new GSMPath(ps,"_p~iF~ps|U");
        map.addPath(path2);
        
        GSMMarker m=new GSMMarker(GSMColor.YELLOW);
        m.addPoint(new GeoPoint(30,40));
        m.addPoint(new GeoPoint(32,42));
        m.addPoint(new GeoPoint(33,43));
        map.addMarker(m);
        
        GSMMarker m2=new GSMMarker(GSMColor.BLUE);
        m2.addPoint(new GeoPoint(27,40));
        m2.addPoint(new GeoPoint(28,42));
        m2.addPoint(new GeoPoint(29,43));
        map.addMarker(m2);
        
        GSMStyle style=new GSMStyle("road.local","geometry");
        
        GSMStyleRule rule=new GSMStyleRule();
        rule.setColor("0x00ff00");
        rule.setWeight("1");
        rule.setVisibility(GSMStyleRule.VISIBILITY_ON);
        style.addRule(rule);
        map.addStyle(style);
        
        
        assertEquals(map.getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/staticmap?center=30.0,40.0&zoom=5&size=500x500&sensor=false&path=color:0x0000ff|weight:5|35.0,45.0|36.0,44.0|37.0,46.0|38.0,47.0&path=color:0x0000ff|weight:5|enc:_p~iF~ps|U&style=feature:road.local|element:geometry|color:0x00ff00|weight:1|visibility:on&markers=color:yellow|30.0,40.0|32.0,42.0|33.0,43.0&markers=color:blue|27.0,40.0|28.0,42.0|29.0,43.0");
        
    }
    
    @Test
    public void cloneTest() {
    
        GSMMapURL map=new GSMMapURL();
        map.setCenter(new GeoPoint(30,40));
        map.setMapSize(new MapSize(500,500));
        map.setZoom(10);
        map.setSecure(true);
        map.incrementZoom();
        GSMMapURL map2=map.clone();
        assertEquals(map.getAbsoluteURLString(),map2.getAbsoluteURLString());
    
    }
}
