package tr.edu.ege.cs.egenav;

import java.awt.Graphics2D;
import tr.edu.ege.cs.egenav.ui.LineStyle;

/**
 * @author Özgün Yılmaz
 * Created on 30.Nis.2014, 12:00:40
 */
public abstract class Direction {
    
    //todo alttaki yönleri ilgili başka bir sınıfa al.
    public static final int NORTH=-1;
    public static final int SOUTH=1;
    public static final int WEST=-1;
    public static final int EAST=1;
    public static final int CONSTANT=0;
    
    public abstract String getInstructions(GeoPoint g);
    public abstract void refreshPixelCoordinates(MapURL m);
    public abstract void drawPathOnMap(Graphics2D g2d, LineStyle routeLineStyle);
    
}
