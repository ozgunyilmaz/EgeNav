package tr.edu.ege.cs.egenav;

import tr.edu.ege.cs.egenav.direction.Directions;

/**
 * @author Özgün Yılmaz
 * Created on 08.Eyl.2014, 16:33:14
 */
public class MapPosition {
    
    private boolean intersection;
    private int vertical,horizontal;
    private int deltaVertical,deltaHorizontal;

    public MapPosition(boolean intersection, int vertical, int horizontal) {
        this.intersection = intersection;
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public MapPosition(boolean intersection) {
        this.intersection = intersection;
    }
    
    public int getHorizontalDirection() {
        return horizontal;
    }

    public void setHorizontalDirection(int horizontal) {
        this.horizontal = horizontal;
    }

    public boolean isIntersection() {
        return intersection;
    }

    public void setIntersection(boolean intersection) {
        this.intersection = intersection;
    }

    public int getVerticalDirection() {
        return vertical;
    }

    public void setVerticalDirection(int vertical) {
        this.vertical = vertical;
    }

    public int getDeltaHorizontal() {
        return deltaHorizontal;
    }

    public void setDeltaHorizontal(int deltaHorizontal) {
        this.deltaHorizontal = deltaHorizontal;
        if (deltaHorizontal>0){
            setHorizontalDirection(Directions.EAST);
        }else if (deltaHorizontal<0){
            setHorizontalDirection(Directions.WEST);
        }else{
            setHorizontalDirection(Directions.CONSTANT);
        }
        
    }

    public int getDeltaVertical() {
        return deltaVertical;
    }

    public void setDeltaVertical(int deltaVertical) {
        this.deltaVertical = deltaVertical;
        
        if (deltaVertical>0){
            setVerticalDirection(Directions.SOUTH);
        }else if (deltaVertical<0){
            setVerticalDirection(Directions.NORTH);
        }else{
            setVerticalDirection(Directions.CONSTANT);
        }
    }
    
}
