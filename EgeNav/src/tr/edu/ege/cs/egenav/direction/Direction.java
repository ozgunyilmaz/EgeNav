package tr.edu.ege.cs.egenav.direction;

import java.awt.Graphics2D;
import tr.edu.ege.cs.egenav.GeoPoint;
import tr.edu.ege.cs.egenav.MapURL;
import tr.edu.ege.cs.egenav.ui.LineStyle;

/**
 * @author Özgün Yılmaz
 * Created on 30.Nis.2014, 12:00:40
 */
public abstract class Direction {
    
    
    public abstract String getInstructions(GeoPoint g);
    public abstract void refreshPixelCoordinates(MapURL m);
    public abstract void drawPathOnMap(Graphics2D g2d, LineStyle routeLineStyle);
    
}
