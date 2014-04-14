package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 14:03:02
 */
public class MapSize {
    private int horizantal,vertical;

    public MapSize(int horizantal, int vertical) {
        this.horizantal = horizantal;
        this.vertical = vertical;
    }

    public int getHorizantal() {
        return horizantal;
    }

    public void setHorizantal(int horizantal) {
        this.horizantal = horizantal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }
    
    
}
