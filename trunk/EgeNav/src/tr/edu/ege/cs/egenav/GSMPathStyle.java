package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 16.Nis.2014, 11:53:45
 */
public class GSMPathStyle {
    
    private String color, weight, fillcolor;
    private boolean geodesic=false;

    public GSMPathStyle(String color) {
        this.color = color;
    }

    public GSMPathStyle(String color, String weight) {
        this.color = color;
        this.weight = weight;
    }

    public GSMPathStyle(String color, String weight, String fillcolor) {
        this.color = color;
        this.weight = weight;
        this.fillcolor = fillcolor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFillcolor() {
        return fillcolor;
    }

    public void setFillcolor(String fillcolor) {
        this.fillcolor = fillcolor;
    }

    public boolean isGeodesic() {
        return geodesic;
    }

    public void setGeodesic(boolean geodesic) {
        this.geodesic = geodesic;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    
    @Override
    public String toString(){
        
        String ps="";
        
        if (getColor()!=null && ! getColor().equals("")){
            ps="color:"+getColor()+"|";
        }
        
        if (getWeight()!=null && ! getWeight().equals("")){
            ps=ps+"weight:"+getWeight()+"|";
        }
        
        if (getFillcolor()!=null && ! getFillcolor().equals("")){
            ps=ps+"fillcolor:"+getFillcolor()+"|";
        }
        
        if (isGeodesic()){
            ps=ps+"geodesic:true";
        }
        
        while (ps.endsWith("|")){
            ps=ps.substring(0, ps.lastIndexOf("|"));
        }
        
        return ps;
    }
}
