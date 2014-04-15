package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 07.Nis.2014, 16:20:48
 */
public class GSMStyleRule {
    //toString yaz
    
    public static final String VISIBILITY_ON="on";
    public static final String VISIBILITY_OFF="off";
    public static final String VISIBILITY_SIMP="simplified";
    
    private String color, hue, saturation, lightness, gamma, visibility;
    private boolean inverseLightness;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGamma() {
        return gamma;
    }

    public void setGamma(String gamma) {
        this.gamma = gamma;
    }

    public String getHue() {
        return hue;
    }

    public void setHue(String hue) {
        this.hue = hue;
    }

    public boolean isInverseLightness() {
        return inverseLightness;
    }

    public void setInverseLightness(boolean inverseLightness) {
        this.inverseLightness = inverseLightness;
    }

    public String getLightness() {
        return lightness;
    }

    public void setLightness(String lightness) {
        this.lightness = lightness;
    }

    public String getSaturation() {
        return saturation;
    }

    public void setSaturation(String saturation) {
        this.saturation = saturation;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
    
    @Override
    public String toString(){
        
        String r="";
        
        if (getColor()!=null && !getColor().equals("")){
            r="color:"+getColor()+"|";
        }
        
        if (getHue()!=null && !getHue().equals("")){
            r=r+"hue:"+getHue()+"|";
        }
        
        if (getSaturation()!=null && !getSaturation().equals("")){
            r=r+"saturation:"+getSaturation()+"|";
        }
        
        if (getLightness()!=null && !getLightness().equals("")){
            r=r+"lightness:"+getLightness()+"|";
        }
        
        if (getGamma()!=null && !getGamma().equals("")){
            r=r+"gamma:"+getGamma()+"|";
        }
        
        if (isInverseLightness()){
            r=r+"inverse_lightness:true|";
        }
        
        if (getVisibility()!=null && !getVisibility().equals("")){
            r=r+"visibility:"+getVisibility()+"|";
        }
        
        while (r.endsWith("|")){
            r=r.substring(0, r.lastIndexOf("|"));
        }
        
        return r;
    }
    
}
