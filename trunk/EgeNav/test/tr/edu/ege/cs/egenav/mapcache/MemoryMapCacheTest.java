/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.ege.cs.egenav.mapcache;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import java.io.File;
import tr.edu.ege.cs.egenav.GeoPoint;
import tr.edu.ege.cs.egenav.MapSize;
import tr.edu.ege.cs.egenav.Location;
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
public class MemoryMapCacheTest {
    
    public MemoryMapCacheTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
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
    public void constructorTest() {
        MemoryMapCache mmc=new MemoryMapCache();
        assertEquals(mmc.getPath(),System.getProperty("user.dir")+"\\");
    }
    
    @Test
    public void constructorTest2() {
    
        MemoryMapCache mmc=new MemoryMapCache("C:\\Users\\samsung\\Documents\\deneme");
        assertEquals(mmc.getPath(),"C:\\Users\\samsung\\Documents\\deneme\\");
    }
    
    @Test
    public void constructorTest3() {
    
        MemoryMapCache mmc=new MemoryMapCache("C:\\Users\\samsung\\Documents\\deneme\\");
        assertEquals(mmc.getPath(),"C:\\Users\\samsung\\Documents\\deneme\\");
    }
    
//    @Test
//    public void getImagePathTest() {
//    
//        MemoryMapCache mmc=new MemoryMapCache("tr");
//        assertEquals(mmc.getPath(),"tr\\");
//        assertEquals(mmc.getImagePath(),"tr\\images\\");
//        File f1=new File("tr\\");
//        File f2=new File("tr\\images\\");
//        try {
//            FileUtils.deleteDirectory(f1);
//            FileUtils.deleteDirectory(f2);
//        } catch (IOException ex) {
//            Logger.getLogger(MemoryMapCacheTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//    
//    @Test
//    public void getCacheFileAbsoluteNameTest() {
//    
//        MemoryMapCache mmc=new MemoryMapCache("tr");
//        assertEquals(mmc.getPath(),"tr\\");
//        assertEquals(mmc.getCacheFileAbsoluteName(),"tr\\mapdata");
//        File f1=new File("tr\\");
//        File f2=new File("tr\\images\\");
//        try {
//            FileUtils.deleteDirectory(f1);
//            FileUtils.deleteDirectory(f2);
//        } catch (IOException ex) {
//            Logger.getLogger(MemoryMapCacheTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    @Test
    public void cacheFilePathTest() {
    
        MemoryMapCache mmc=new MemoryMapCache("C:\\Users\\samsung\\Documents\\deneme\\");
        assertEquals(mmc.getCacheFileAbsoluteName(),"C:\\Users\\samsung\\Documents\\deneme\\mapdata");
    }
    
    @Test
    public void imagePathTest() {
    
        MemoryMapCache mmc=new MemoryMapCache("C:\\Users\\samsung\\Documents\\deneme\\");
        assertEquals(mmc.getImagePath(),"C:\\Users\\samsung\\Documents\\deneme\\images\\");
    }
    
    @Test
    public void getMapTest() {
    
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
        MemoryMapCache mmc=new MemoryMapCache("C:\\Users\\samsung\\Documents\\deneme");
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
        File f3=new File("C:\\Users\\samsung\\Documents\\deneme\\mapdata");
        File f4=new File("C:\\Users\\samsung\\Documents\\deneme\\images");
        assertTrue(f3.exists());
        assertTrue(f4.exists());
        assertEquals(f4.list().length,2);
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
        MemoryMapCache mmc=new MemoryMapCache("C:\\Users\\samsung\\Documents\\deneme");
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
        assertEquals(f4.list().length,2);
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
