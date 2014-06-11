package tr.edu.ege.cs.egenav.ui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Stroke;

/**
 * @author Özgün Yılmaz
 * Created on 24.Nis.2014, 14:28:50
 */
public class LineStyle {
    
    private Color color;
    private Stroke stroke;
    private Composite composite;

    public LineStyle() {
        this.color = Color.BLUE;
        this.stroke = new BasicStroke(5,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
        this.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5F);
    }
    
    public LineStyle(Color color, Stroke stroke, Composite composite) {
        this.color = color;
        this.stroke = stroke;
        this.composite = composite;
    }

    public Composite getComposite() {
        return composite;
    }

    public void setComposite(Composite composite) {
        this.composite = composite;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
}
