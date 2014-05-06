package tr.edu.ege.cs.egenav;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 06.May.2014, 13:40:09
 */
public class MapColorModel {
    
    private ArrayList<Color> border;
    private ArrayList<Color> track;

    public MapColorModel(ArrayList<Color> border, ArrayList<Color> track) {
        this.border = border;
        this.track = track;
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
        return border.get(0);
    }
}
