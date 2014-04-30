package tr.edu.ege.cs.egenav.test;

import java.awt.Point;
import tr.edu.ege.cs.egenav.Direction;
import tr.edu.ege.cs.egenav.GeoPoint;
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
         
         url.setCenter(new GSMLocation(new GeoPoint(40,30)));
         url.setZoom(6);
         url.setMapSize(new MapSize(500,500));
//         url.setMapType(GSMMapType.HYBRID);
//         url.setFormat(GSMImageFormat.PNG);
         System.out.println(url.getAbsoluteURLString());
         System.out.println(url.getCordinatesOnMap(new Point(10,10)));
         System.out.println(url.getPixelOnMap(38, 32));
         System.out.println(url.getNeighborTile(Direction.NORTH, Direction.CONSTANT).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Direction.SOUTH, Direction.CONSTANT).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Direction.CONSTANT, Direction.WEST).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Direction.CONSTANT, Direction.EAST).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Direction.NORTH, Direction.WEST).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Direction.NORTH, Direction.EAST).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Direction.SOUTH, Direction.WEST).getAbsoluteURLString());
         System.out.println(url.getNeighborTile(Direction.SOUTH, Direction.EAST).getAbsoluteURLString());
     }
}
