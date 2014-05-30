/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.ege.cs.egenav;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
public class MapDownloaderTest {
    
    public MapDownloaderTest() {
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
    public void downloadMapTest1() {
        try {
            BufferedImage img=MapDownloader.downloadMap("http://maps.googleapis.com/maps/api/staticmap?center=40.0,30.0&zoom=6&size=500x500&sensor=false");
            BufferedImage img2=ImageIO.read(new File("test\\tr\\edu\\ege\\cs\\egenav\\TestImages\\center=40.0,30.0&zoom=6&size=500x500&sensor=false.png"));
            assertEquals(img.getHeight(),img2.getHeight());
            assertEquals(img.getWidth(),img2.getWidth());
            
            for (int i=0;i<img.getWidth();i++){
                for (int j=0;j<img.getHeight();j++){
                    assertEquals(img.getRGB(i, j),img2.getRGB(i, j));
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MapDownloaderTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    
    }
    
    @Test
    public void downloadMapTest2() {
    
        try {
            BufferedImage img=MapDownloader.downloadMap("http://maps.googleapis.com/maps/api/staticmap?center=38.0,32.0&zoom=6&size=500x500&sensor=false");
            BufferedImage img2=ImageIO.read(new File("test\\tr\\edu\\ege\\cs\\egenav\\TestImages\\center=38.0,32.0&zoom=6&size=500x500&sensor=false.png"));
            assertEquals(img.getHeight(),img2.getHeight());
            assertEquals(img.getWidth(),img2.getWidth());
            
            for (int i=0;i<img.getWidth();i++){
                for (int j=0;j<img.getHeight();j++){
                    assertEquals(img.getRGB(i, j),img2.getRGB(i, j));
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MapDownloaderTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    
    }
    
    @Test
    public void downloadMapTest3() {
    
        try {
            BufferedImage img=MapDownloader.downloadMap("http://maps.googleapis.com/maps/api/staticmap?center=37.95041914767124,32.63671897351742&zoom=6&size=500x500&sensor=false");
            BufferedImage img2=ImageIO.read(new File("test\\tr\\edu\\ege\\cs\\egenav\\TestImages\\center=37.95041914767124,32.63671897351742&zoom=6&size=500x500&sensor=false.png"));
            assertEquals(img.getHeight(),img2.getHeight());
            assertEquals(img.getWidth(),img2.getWidth());
            
            for (int i=0;i<img.getWidth();i++){
                for (int j=0;j<img.getHeight();j++){
                    assertEquals(img.getRGB(i, j),img2.getRGB(i, j));
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MapDownloaderTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    
    }
}
