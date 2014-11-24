package tr.edu.ege.cs.egenav.direction;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 06.May.2014, 13:40:09
 */
public class MapColorModel {
    
    private ArrayList<Color> border;
    private ArrayList<Color> track;
    private boolean borderBased;

    public MapColorModel(ArrayList<Color> border, ArrayList<Color> track, boolean borderBased) {
        this.border = border;
        this.track = track;
        this.borderBased=borderBased;
    }
    
    public MapColorModel(){
        border=new ArrayList<Color>();
        track=new ArrayList<Color>();
    }
    
    public void addBorderColor(Color c){
        border.add(c);
    }
    
    public void addTrackColor(Color c){
        track.add(c);
    }
    
    public boolean isTrack(Color c){
        return track.contains(c);
    }
    
    public boolean isBorder(Color c){
        return border.contains(c);
    }
    
    public boolean isBorder(int rgb){
        Color c=new Color(rgb);
        return border.contains(c);
    }
    
    public Color getABorderColor(){
        if (!border.isEmpty()){
            return border.get(0);
        }else{
            if (track.isEmpty()){
                return null;
            }else{
                Color c=track.get(0).darker();
                while (isBorder(c) || isTrack(c)){
                    c=track.get(0).darker();
                }
                return c;
            }
        }
        
    }

    public boolean isBorderBased() {
        return borderBased;
    }

    public void setBorderBased(boolean borderBased) {
        this.borderBased = borderBased;
    }
    
    public boolean isOnTrack(Color c){
        if (borderBased){
            return isBorder(c);
        }else{
            return !(isTrack(c));
        }
    }
    
    public boolean isOnTrack(int rgb){
        return isOnTrack(new Color(rgb));
    }
}
