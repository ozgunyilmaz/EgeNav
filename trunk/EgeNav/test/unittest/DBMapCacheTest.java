/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import tr.edu.ege.cs.egenav.MapSize;
import tr.edu.ege.cs.egenav.GeoPoint;
import tr.edu.ege.cs.egenav.GSMLocation;
import tr.edu.ege.cs.egenav.GSMMapURL;
import java.io.File;
import tr.edu.ege.cs.egenav.mapcache.DBMapCache;
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
public class DBMapCacheTest {
    
    public DBMapCacheTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        File f=new File("C:\\Users\\samsung\\Documents\\mapdata.db");
        if (f.exists()){
            f.delete();
        }
        f=new File(System.getProperty("user.dir")+"\\mapdata.db");
        if (f.exists()){
            f.delete();
        }
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
    public void contructorTest() {
        DBMapCache mmc=new DBMapCache();
        assertEquals(mmc.getPath(),System.getProperty("user.dir")+"\\");
        File f=new File(System.getProperty("user.dir")+"\\mapdata.db");
        if (f.exists()){
            f.delete();
        }
    }
    
    @Test
    public void contructorTest2() {
    
        DBMapCache mmc=new DBMapCache("C:\\Users\\samsung\\Documents");
        assertEquals(mmc.getPath(),"C:\\Users\\samsung\\Documents\\");
        File f=new File("C:\\Users\\samsung\\Documents\\mapdata.db");
        if (f.exists()){
            f.delete();
        }
    }
    
    @Test
    public void contructorTest3() {
    
        DBMapCache mmc=new DBMapCache("C:\\Users\\samsung\\Documents\\");
        assertEquals(mmc.getPath(),"C:\\Users\\samsung\\Documents\\");
        File f=new File("C:\\Users\\samsung\\Documents\\mapdata.db");
        if (f.exists()){
            f.delete();
        }
    }
    
    @Test
    public void cacheFilePathTest() {
    
        DBMapCache mmc=new DBMapCache("C:\\Users\\samsung\\Documents\\");
        assertEquals(mmc.getCacheFileAbsoluteName(),"C:\\Users\\samsung\\Documents\\mapdata.db");
        File f=new File("C:\\Users\\samsung\\Documents\\mapdata.db");
        if (f.exists()){
            f.delete();
        }
    }
    
//    @Test
//    public void getMapTest() {
//    
//        File f=new File("C:\\Users\\samsung\\Documents\\deneme\\mapdata.db");
//        if (f.exists()){
//            f.delete();
//        }
//        File f2=new File("C:\\Users\\samsung\\Documents\\deneme\\images");
//        if (f2.exists()){
//            f2.delete();
//        }
//        DBMapCache mmc=new DBMapCache("C:\\Users\\samsung\\Documents\\deneme");
//        GSMMapURL url=new GSMMapURL();
//         
//        url.setCenter(new GSMLocation(new GeoPoint(40,30)));
//        url.setZoom(6);
//        url.setMapSize(new MapSize(500,500));
//        
//        mmc.getMap(url);
//        assertEquals(mmc.getMaps().size(),1);
//        assertEquals(mmc.getMaps().get(0).getUsageCount(),1);
//        mmc.getMap(url);
//        assertEquals(mmc.getMaps().size(),1);
//        assertEquals(mmc.getMaps().get(0).getUsageCount(),2);
//        mmc.close();
//        File f3=new File("C:\\Users\\samsung\\Documents\\deneme\\mapdata.db");
//        File f4=new File("C:\\Users\\samsung\\Documents\\deneme\\images");
//        assertTrue(f3.exists());
//        assertTrue(f4.exists());
//        assertEquals(f4.list().length,1);
//        if (f3.exists()){
//            f3.delete();
//        }
//        
//        if (f4.exists()){
//            f4.delete();
//        }
//    }
//    
//    @Test
//    public void getMapTest2() {
//    
//        File f=new File("C:\\Users\\samsung\\Documents\\deneme\\mapdata");
//        if (f.exists()){
//            f.delete();
//        }
//        File f2=new File("C:\\Users\\samsung\\Documents\\deneme\\images");
//        if (f2.exists()){
//            f2.delete();
//        }
//        DBMapCache mmc=new DBMapCache("C:\\Users\\samsung\\Documents\\deneme");
//        GSMMapURL url=new GSMMapURL();
//        url.setFormat("jpg");
//        url.setCenter(new GSMLocation(new GeoPoint(40,30)));
//        url.setZoom(6);
//        url.setMapSize(new MapSize(500,500));
//        
//        mmc.getMap(url);
//        assertEquals(mmc.getMaps().size(),1);
//        assertEquals(mmc.getMaps().get(0).getUsageCount(),1);
//        mmc.getMap(url);
//        assertEquals(mmc.getMaps().size(),1);
//        assertEquals(mmc.getMaps().get(0).getUsageCount(),2);
//        mmc.close();
//        File f3=new File("C:\\Users\\samsung\\Documents\\deneme\\mapdata");
//        File f4=new File("C:\\Users\\samsung\\Documents\\deneme\\images");
//        assertTrue(f3.exists());
//        assertTrue(f4.exists());
//        assertEquals(f4.list().length,1);
//        assertTrue(f4.list()[0].endsWith(".jpg"));
//        if (f3.exists()){
//            f3.delete();
//        }
//        
//        if (f4.exists()){
//            f4.delete();
//        }
//    }
    
}
