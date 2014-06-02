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
public class GSMDirectionURLTest {
    
    public GSMDirectionURLTest() {
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
     
        GSMDirectionURL gdu=new GSMDirectionURL(new Location(new GeoPoint(30,30)),new Location(new GeoPoint(40,40)));
        assertEquals(gdu.getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/directions/json?origin=30.0,30.0&destination=40.0,40.0&sensor=false");

     }
     
     public void getAbsoluteURLStringTest2() {
     
        GSMDirectionURL gdu=new GSMDirectionURL(new Location(new GeoPoint(30,30)),new Location(new GeoPoint(40,40)),GSMDirectionURL.MODE_WALKING_MODE);
        assertEquals(gdu.getAbsoluteURLString(),"http://maps.googleapis.com/maps/api/directions/json?origin=30.0,30.0&destination=40.0,40.0&sensor=false&mode=walking");
     
     }
     
     
}
