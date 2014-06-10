/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.ege.cs.egenav.mapcache;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import tr.edu.ege.cs.egenav.MapSize;
import tr.edu.ege.cs.egenav.GeoPoint;
import tr.edu.ege.cs.egenav.Location;
import tr.edu.ege.cs.egenav.GSMMapURL;
import java.io.File;
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
        mmc.close();
        assertEquals(mmc.getPath(),System.getProperty("user.dir")+"\\");
        File f=new File(System.getProperty("user.dir")+"\\mapdata.db");
        if (f.exists()){
            f.delete();
        }
    }
    
    @Test
    public void contructorTest2() {
    
        DBMapCache mmc=new DBMapCache("C:\\Users\\samsung\\Documents\\deneme");
        mmc.close();
        assertEquals(mmc.getPath(),"C:\\Users\\samsung\\Documents\\deneme\\");
        File f=new File("C:\\Users\\samsung\\Documents\\deneme\\mapdata.db");
        if (f.exists()){
            f.delete();
        }
    }
    
    @Test
    public void contructorTest3() {
    
        DBMapCache mmc=new DBMapCache("C:\\Users\\samsung\\Documents\\deneme\\");
        mmc.close();
        assertEquals(mmc.getPath(),"C:\\Users\\samsung\\Documents\\deneme\\");
        File f=new File("C:\\Users\\samsung\\Documents\\deneme\\mapdata.db");
        if (f.exists()){
            f.delete();
        }
    }
    
    @Test
    public void cacheFilePathTest() {
    
        DBMapCache mmc=new DBMapCache("C:\\Users\\samsung\\Documents\\deneme\\");
        mmc.close();
        assertEquals(mmc.getCacheFileAbsoluteName(),"C:\\Users\\samsung\\Documents\\deneme\\mapdata.db");
        File f=new File("C:\\Users\\samsung\\Documents\\deneme\\mapdata.db");
        if (f.exists()){
            f.delete();
        }
    }
    
    @Test
    public void getMapTest() {
    
        File f=new File("C:\\Users\\samsung\\Documents\\deneme\\mapdata.db");
        if (f.exists()){
            f.delete();
        }
        File f2=new File("C:\\Users\\samsung\\Documents\\deneme\\images");
        if (f2.exists()){
            try {
                FileUtils.deleteDirectory(f2.getAbsoluteFile());
            } catch (IOException ex) {
                Logger.getLogger(MemoryMapCacheTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        DBMapCache mmc=new DBMapCache("C:\\Users\\samsung\\Documents\\deneme");
        GSMMapURL url=new GSMMapURL();
         
        url.setCenter(new Location(new GeoPoint(40,30)));
        url.setZoom(6);
        url.setMapSize(new MapSize(500,500));
        
        mmc.getMap(url);
        assertEquals(mmc.getMaps().size(),1);
        assertEquals(mmc.getMaps().get(0).getUsageCount(),1);
        mmc.getMap(url);
        assertEquals(mmc.getMaps().size(),1);
        assertEquals(mmc.getMaps().get(0).getUsageCount(),2);
    
        url.setCenter(new Location(new GeoPoint(42,32)));
        mmc.getMap(url);
        assertEquals(mmc.getMaps().size(),2);
        assertEquals(mmc.getMaps().get(1).getUsageCount(),1);
        mmc.getMap(url);
        assertEquals(mmc.getMaps().size(),2);
        assertEquals(mmc.getMaps().get(1).getUsageCount(),2);
    
    
        mmc.close();
        File f3=new File("C:\\Users\\samsung\\Documents\\deneme\\mapdata.db");
        File f4=new File("C:\\Users\\samsung\\Documents\\deneme\\images");
        assertTrue(f3.exists());
        assertTrue(f4.exists());
        assertEquals(f4.list().length,1);
        assertTrue(f4.list()[0].endsWith(".png"));
        if (f3.exists()){
            f3.delete();
        }
        
        if (f4.exists()){
            try {
                FileUtils.deleteDirectory(f4.getAbsoluteFile());
            } catch (IOException ex) {
                Logger.getLogger(MemoryMapCacheTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Test
    public void getMapTest2() {
    
        File f=new File("C:\\Users\\samsung\\Documents\\deneme\\mapdata");
        if (f.exists()){
            f.delete();
        }
        File f2=new File("C:\\Users\\samsung\\Documents\\deneme\\images");
        if (f2.exists()){
            try {
                FileUtils.deleteDirectory(f2.getAbsoluteFile());
            } catch (IOException ex) {
                Logger.getLogger(MemoryMapCacheTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        DBMapCache mmc=new DBMapCache("C:\\Users\\samsung\\Documents\\deneme");
        GSMMapURL url=new GSMMapURL();
        url.setFormat("jpg");
        url.setCenter(new Location(new GeoPoint(40,30)));
        url.setZoom(6);
        url.setMapSize(new MapSize(500,500));
        
        mmc.getMap(url);
        assertEquals(mmc.getMaps().size(),1);
        assertEquals(mmc.getMaps().get(0).getUsageCount(),1);
        mmc.getMap(url);
        assertEquals(mmc.getMaps().size(),1);
        assertEquals(mmc.getMaps().get(0).getUsageCount(),2);
    
        url.setCenter(new Location(new GeoPoint(42,32)));
        mmc.getMap(url);
        assertEquals(mmc.getMaps().size(),2);
        assertEquals(mmc.getMaps().get(1).getUsageCount(),1);
        mmc.getMap(url);
        assertEquals(mmc.getMaps().size(),2);
        assertEquals(mmc.getMaps().get(1).getUsageCount(),2);
    
    
    
        mmc.close();
        File f3=new File("C:\\Users\\samsung\\Documents\\deneme\\mapdata");
        File f4=new File("C:\\Users\\samsung\\Documents\\deneme\\images");
        assertTrue(f3.exists());
        assertTrue(f4.exists());
        assertEquals(f4.list().length,1);
        assertTrue(f4.list()[0].endsWith(".jpg"));
        if (f3.exists()){
            f3.delete();
        }
        
        if (f4.exists()){
            try {
                FileUtils.deleteDirectory(f4.getAbsoluteFile());
            } catch (IOException ex) {
                Logger.getLogger(MemoryMapCacheTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
