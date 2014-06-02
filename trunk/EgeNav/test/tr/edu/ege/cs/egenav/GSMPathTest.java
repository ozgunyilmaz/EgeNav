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
public class GSMPathTest {
    
    public GSMPathTest() {
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
    
        GSMPathStyle ps=new GSMPathStyle("0x0000ff","5");
        
        GSMPath path=new GSMPath(ps);
        
        path.addPathPoint(new Location(new GeoPoint(40.737102,-73.990318)));
        path.addPathPoint(new Location(new GeoPoint(40.749825,-73.987963)));
        path.addPathPoint(new Location(new GeoPoint(40.752946,-73.987384)));
        path.addPathPoint(new Location(new GeoPoint(40.755823,-73.986397)));
        
        assertEquals(path.toString(),"path=color:0x0000ff|weight:5|40.737102,-73.990318|40.749825,-73.987963|40.752946,-73.987384|40.755823,-73.986397");
    }
    
    @Test
    public void toStringTest2() {
    
        GSMPathStyle ps=new GSMPathStyle("0x0000ff","5");
        
        GSMPath path=new GSMPath(ps,"_p~iF~ps|U");
        
        assertEquals(path.toString(),"path=color:0x0000ff|weight:5|enc:_p~iF~ps|U");
    }
}
