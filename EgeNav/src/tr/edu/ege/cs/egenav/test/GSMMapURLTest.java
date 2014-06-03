package tr.edu.ege.cs.egenav.test;

import java.awt.Point;
import tr.edu.ege.cs.egenav.direction.Directions;
import tr.edu.ege.cs.egenav.GeoPoint;
import tr.edu.ege.cs.egenav.GSMMapURL;
import tr.edu.ege.cs.egenav.Location;
import tr.edu.ege.cs.egenav.MapSize;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 15:17:44
 */
public class GSMMapURLTest {
    
     public static void main(String args[]) {
         GSMMapURL url=new GSMMapURL();
         
         url.setCenter(new Location(new GeoPoint(40,30)));
         url.setZoom(6);
         url.setMapSize(new MapSize(500,500));
//         url.setMapType(GSMMapType.HYBRID);
//         url.setFormat(GSMImageFormat.PNG);
         System.out.println(url.getAbsoluteURLString());
         System.out.println(url.getCordinatesOnMap(new Point(10,10)));
         System.out.println(url.getPixelOnMap(38, 32));
         System.out.println(url.getNeighborTile(Directions.NORTH, Directions.CONSTANT).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Directions.SOUTH, Directions.CONSTANT).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Directions.CONSTANT, Directions.WEST).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Directions.CONSTANT, Directions.EAST).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Directions.NORTH, Directions.WEST).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Directions.NORTH, Directions.EAST).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Directions.SOUTH, Directions.WEST).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Directions.SOUTH, Directions.EAST).getAbsoluteURLString());
     }
}
