package tr.edu.ege.cs.egenav.test;

import tr.edu.ege.cs.egenav.GSMColor;
import tr.edu.ege.cs.egenav.GSMDirectionURL;
import tr.edu.ege.cs.egenav.GSMLocation;
import tr.edu.ege.cs.egenav.GSMMarker;
import tr.edu.ege.cs.egenav.GSMPathStyle;
import tr.edu.ege.cs.egenav.GeoPoint;

/**
 * @author Özgün Yılmaz
 * Created on 30.May.2014, 13:35:46
 */
public class GSMDirectionURLTest {
    
    public static void main(String args[]){
        
        GSMDirectionURL gdu=new GSMDirectionURL(new GSMLocation(new GeoPoint(30,30)),new GSMLocation(new GeoPoint(40,40)));
        
        System.out.println(gdu.getAbsoluteURLString());
        
        GSMDirectionURL gdu2=new GSMDirectionURL(new GSMLocation(new GeoPoint(30,30)),new GSMLocation(new GeoPoint(40,40)),GSMDirectionURL.MODE_WALKING_MODE);
        System.out.println(gdu2.getAbsoluteURLString());
        
        GSMMarker m=new GSMMarker(GSMColor.YELLOW);
        m.addPoint(new GeoPoint(30,40));
        System.out.println(m.toString());
        m.addPoint(new GeoPoint(32,42));
        System.out.println(m.toString());
        m.addPoint(new GeoPoint(33,43));
        System.out.println(m.toString());
        
        
        GSMPathStyle ps=new GSMPathStyle(GSMColor.BLUE,"",GSMColor.YELLOW);
        System.out.println(ps.toString());
        ps.setGeodesic(true);
        System.out.println(ps.toString());
        
        GeoPoint gp=new GeoPoint("38 26 08N 027 12 52E");
        System.out.println(gp.toString());
        
        GeoPoint gp2=new GeoPoint("48 12 28N 028 22 32E");
        System.out.println(gp2.getDistanceTo(gp));
    }
    
    
}
