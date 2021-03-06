package tr.edu.ege.cs.egenav;

import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 07.Nis.2014, 15:53:56
 */
public abstract class Marker extends Parameter{
    
    protected ArrayList<GeoPoint> points=new ArrayList<GeoPoint>();
    private String color="",size="",label="";

    public Marker(String color) {
        this.color = color;
    }

    public Marker(String color, String size) {
        this.color = color;
        this.size = size;
    }

    public Marker(String color, String size, String label) {
        this.color = color;
        this.size = size;
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    public void addPoint(GeoPoint c){
        points.add(c);
    }
    
    
    @Override
    public abstract String toString();
    
}
