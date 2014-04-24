package tr.edu.ege.cs.egenav.ui;

import java.awt.Polygon;
import java.awt.Shape;

/**
 * @author Özgün Yılmaz
 * Created on 24.Nis.2014, 14:16:52
 */
public class Arrow {
    
    private Shape shape;
    private LineStyle style;
    

    public Arrow() {
        Polygon p=new Polygon();
        p.addPoint(0, 0);
        p.addPoint(-10, 5);
        p.addPoint(0, -20);
        p.addPoint(10, 5);
        shape=p;
        style=new LineStyle();
        
    }

    public Arrow(Shape shape, LineStyle style) {
        this.shape = shape;
        this.style = style;
    }
    
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public LineStyle getLineStyle() {
        return style;
    }

    public void setLineStyle(LineStyle style) {
        this.style = style;
    }

    
    
    
    
}
