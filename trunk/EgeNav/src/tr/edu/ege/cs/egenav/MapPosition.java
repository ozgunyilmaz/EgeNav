package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 08.Eyl.2014, 16:33:14
 */
public class MapPosition {
    
    private boolean intersection;
    private int vertical,horizontal;

    public MapPosition(boolean intersection, int vertical, int horizontal) {
        this.intersection = intersection;
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public MapPosition(boolean intersection) {
        this.intersection = intersection;
    }
    
    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public boolean isIntersection() {
        return intersection;
    }

    public void setIntersection(boolean intersection) {
        this.intersection = intersection;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }
    
}
