package tr.edu.ege.cs.egenav.direction;

/**
 * @author Özgün Yılmaz
 * Created on 05.Ara.2014, 16:22:31
 */
public class Threshold {
    int horizontal,vertical;

    public Threshold(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }
    
    public Threshold multiply(double x, double y){
        
        return new Threshold((int)(horizontal*x), (int)(vertical*y));

    }
}
