package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 15:17:44
 */
public class Test {
    
     public static void main(String args[]) {
         GSMMapURLBuilder url=new GSMMapURLBuilder();
         
         url.setCenter(new MapCenter(new Coordinate(40,30)));
         url.setZoom(7);
         url.setMapSize(new MapSize(500,500));
         url.setMapType(GSMMapType.HYBRID);
         url.setFormat(GSMImageFormat.PNG);
         System.out.println(url);
     }
}
