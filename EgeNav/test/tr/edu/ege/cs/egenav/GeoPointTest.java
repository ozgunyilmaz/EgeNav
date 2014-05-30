package tr.edu.ege.cs.egenav;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import tr.edu.ege.cs.egenav.GeoPoint;
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
public class GeoPointTest {
    
    public GeoPointTest() {
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
    public void toStringTest1() {
        
        GeoPoint gp=new GeoPoint(30.2,40.8);
        assertEquals(gp.toString(),"30.2,40.8");
        
    }
    
    @Test
    public void toStringTest2() {
        
        GeoPoint gp=new GeoPoint("38 26 08N 027 12 52E");
        assertEquals(gp.toString(),"38.43555555555555,27.214444444444442");
        
    }
    
    @Test
    public void cloneTest() {
        
        GeoPoint gp=new GeoPoint(30.2,40.8);
        GeoPoint gp2=gp.clone();
        assertEquals(gp.getLatitude(),gp2.getLatitude(),0.01);
        assertEquals(gp.getLongitude(),gp2.getLongitude(),0.01);
        
    }
    
    @Test
    public void getDistanceToTest() {
        
        GeoPoint gp=new GeoPoint("38 26 08N 027 12 52E");
        
        GeoPoint gp2=new GeoPoint("48 12 28N 028 22 32E");
        assertEquals(gp2.getDistanceTo(gp),1090.6375527630503,0.01);
        
    }
}
