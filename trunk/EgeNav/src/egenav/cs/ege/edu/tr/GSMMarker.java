package egenav.cs.ege.edu.tr;

/**
 * @author Özgün Yılmaz
 * Created on 07.Nis.2014, 15:44:38
 */
public class GSMMarker extends Marker{

    public GSMMarker(String color) {
        super(color);
    }

    public GSMMarker(String color, String size) {
        super(color, size);
    }

    public GSMMarker(String color, String size, String label) {
        super(color, size, label);
    }

    @Override
    public String toString() {
        String markerString="markers=";
        
        if (getSize()!=null && !getSize().isEmpty()){
            markerString=markerString+"size:"+getSize()+"|";
        }
        
        if (getColor()!=null && !getColor().isEmpty()){
            markerString=markerString+"color:"+getColor()+"|";
        }
        
        if (getLabel()!=null && !getLabel().isEmpty()){
            markerString=markerString+"label:"+getLabel()+"|";
        }
        
        for (int i=0;i<points.size();i++){
            markerString=markerString+ points.get(i).toString()+"|";
        }
        
        while (markerString.endsWith("|")){
            markerString=markerString.substring(0, markerString.lastIndexOf("|"));
        }
        
        return markerString;
    }
    
    
}
