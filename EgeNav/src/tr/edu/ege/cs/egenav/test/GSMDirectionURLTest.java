package tr.edu.ege.cs.egenav.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import tr.edu.ege.cs.egenav.GSMColor;
import tr.edu.ege.cs.egenav.direction.GSMDirectionURL;
import tr.edu.ege.cs.egenav.GSMMapURL;
import tr.edu.ege.cs.egenav.GSMMarker;
import tr.edu.ege.cs.egenav.GSMPath;
import tr.edu.ege.cs.egenav.GSMPathStyle;
import tr.edu.ege.cs.egenav.GSMStyle;
import tr.edu.ege.cs.egenav.GSMStyleRule;
import tr.edu.ege.cs.egenav.GeoPoint;
import tr.edu.ege.cs.egenav.Location;
import tr.edu.ege.cs.egenav.MapSize;

/**
 * @author Özgün Yılmaz
 * Created on 30.May.2014, 13:35:46
 */
public class GSMDirectionURLTest {
    
    public static void main(String args[]){
        
        GSMDirectionURL gdu=new GSMDirectionURL(new Location(new GeoPoint(30,30)),new Location(new GeoPoint(40,40)));
        
        System.out.println(gdu.getAbsoluteURLString());
        
        GSMDirectionURL gdu2=new GSMDirectionURL(new Location(new GeoPoint(30,30)),new Location(new GeoPoint(40,40)),GSMDirectionURL.MODE_WALKING_MODE);
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
        
        File f=new File("ImageFiles\\center=40.0,30.0&zoom=6&size=500x500&sensor=false.png");
        System.out.println(f.getAbsolutePath());
        
        GSMStyleRule rule=new GSMStyleRule();
        rule.setColor("0x00ff00");
        rule.setWeight("3.9");
        rule.setVisibility(GSMStyleRule.VISIBILITY_ON);
        System.out.println(rule.toString());
        
        GSMStyle style=new GSMStyle("administrative","labels");
        
        rule=new GSMStyleRule();
        rule.setWeight("3.9");
        rule.setVisibility(GSMStyleRule.VISIBILITY_ON);
        rule.setInverseLightness(true);
        style.addRule(rule);
        System.out.println(style.toString());
        
        
        GSMMapURL map=new GSMMapURL();
        map.setCenter(new GeoPoint(30,40));
        map.setMapSize(new MapSize(500,500));
        map.setZoom(10);
        map.setSecure(true);
        map.incrementZoom();
        System.out.println(map.getAbsoluteURLString());
        
        /////////////////////
        map=new GSMMapURL();
        map.setCenter(new GeoPoint(40,30));
        map.setMapSize(new MapSize(500,500));
        map.setZoom(5);
        
        ps=new GSMPathStyle("0x0000ff","5");
        GSMPath path=new GSMPath(ps);
        path.addPathPoint(new Location(new GeoPoint(40,35)));
        path.addPathPoint(new Location(new GeoPoint(41,34)));
        path.addPathPoint(new Location(new GeoPoint(42,36)));
        path.addPathPoint(new Location(new GeoPoint(38,37)));
        map.addPath(path);
        
        
        GSMPathStyle ps2=new GSMPathStyle("0xff0000","3");
        GSMPath path2=new GSMPath(ps2,"_dpkFkulpDhoeCombFwbxBa|wFwcmEfdwB??`ukDvvfF");
        map.addPath(path2);
        
        m=new GSMMarker(GSMColor.YELLOW);
        m.addPoint(new GeoPoint(40,30));
        m.addPoint(new GeoPoint(42,32));
        m.addPoint(new GeoPoint(43,33));
        map.addMarker(m);
        
        GSMMarker m2=new GSMMarker(GSMColor.BLUE);
        m2.addPoint(new GeoPoint(42,30));
        m2.addPoint(new GeoPoint(45,32));
        m2.addPoint(new GeoPoint(38,33));
        map.addMarker(m2);
        
        style=new GSMStyle("road.highway","geometry");
        
        rule=new GSMStyleRule();
        rule.setColor("0x00ff00");
        rule.setWeight("3");
        rule.setVisibility(GSMStyleRule.VISIBILITY_ON);
        style.addRule(rule);
        map.addStyle(style);
        System.out.println(map.getAbsoluteURLString());
        
        
    }
    
    
}
