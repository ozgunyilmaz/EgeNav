/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.ege.cs.egenav;

import tr.edu.ege.cs.egenav.GeoPoint;
import tr.edu.ege.cs.egenav.GSMMarker;
import tr.edu.ege.cs.egenav.GSMColor;
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
public class GSMMarkerTest {
    
    public GSMMarkerTest() {
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
    public void toStringTest() {
    
        GSMMarker m=new GSMMarker(GSMColor.YELLOW);
        m.addPoint(new GeoPoint(30,40));
        assertEquals(m.toString(),"markers=color:yellow|30.0,40.0");
        m.addPoint(new GeoPoint(32,42));
        assertEquals(m.toString(),"markers=color:yellow|30.0,40.0|32.0,42.0");
        m.addPoint(new GeoPoint(33,43));
        assertEquals(m.toString(),"markers=color:yellow|30.0,40.0|32.0,42.0|33.0,43.0");
    
    }
}
