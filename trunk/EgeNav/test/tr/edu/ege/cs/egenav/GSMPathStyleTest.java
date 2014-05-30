/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.ege.cs.egenav;

import tr.edu.ege.cs.egenav.GSMPathStyle;
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
public class GSMPathStyleTest {
    
    public GSMPathStyleTest() {
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
    
        GSMPathStyle ps=new GSMPathStyle(GSMColor.BLUE,"",GSMColor.YELLOW);
        assertEquals(ps.toString(),"color:blue|fillcolor:yellow");
        ps.setGeodesic(true);
        assertEquals(ps.toString(),"color:blue|fillcolor:yellow|geodesic:true");
    
    }
}
