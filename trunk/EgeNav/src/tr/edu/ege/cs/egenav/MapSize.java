package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 14:03:02
 */
public class MapSize {
    private int horizontal,vertical;

    public MapSize(int horizantal, int vertical) {
        this.horizontal = horizantal;
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizantal(int horizantal) {
        this.horizontal = horizantal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }
    
    @Override
    public MapSize clone(){
        
        return new MapSize(horizontal,vertical);
        
    }
}
