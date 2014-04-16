package tr.edu.ege.cs.egenav.test;

import tr.edu.ege.cs.egenav.Coordinate;
import tr.edu.ege.cs.egenav.GSMImageFormat;
import tr.edu.ege.cs.egenav.GSMMapType;
import tr.edu.ege.cs.egenav.GSMMapURL;
import tr.edu.ege.cs.egenav.GSMLocation;
import tr.edu.ege.cs.egenav.MapSize;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 15:17:44
 */
public class GSMMapURLTest {
    
     public static void main(String args[]) {
         GSMMapURL url=new GSMMapURL();
         
         url.setCenter(new GSMLocation(new Coordinate(40,30)));
         url.setZoom(7);
         url.setMapSize(new MapSize(500,500));
         url.setMapType(GSMMapType.HYBRID);
         url.setFormat(GSMImageFormat.PNG);
         System.out.println(url);
     }
}
