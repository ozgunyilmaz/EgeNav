package tr.edu.ege.cs.egenav;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author Özgün Yılmaz
 * Created on 21.Nis.2014, 13:43:04
 */
public class MapDownloader {
    
    public static BufferedImage downloadMap(String adr){
        try {
            URLConnection con;
            URL url=new URL(adr);
            con=url.openConnection();
            InputStream in=con.getInputStream();
            BufferedImage img = ImageIO.read(in);

            return img;
        } catch (IOException ex) {
            Logger.getLogger(MapDownloader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
