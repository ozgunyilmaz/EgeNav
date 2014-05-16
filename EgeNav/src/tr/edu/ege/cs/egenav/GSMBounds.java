package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 15.May.2014, 16:46:31
 */
public class GSMBounds {
    
    private GSMPoint northeast;
    private GSMPoint southwest;

    public GSMBounds(GSMPoint northeast, GSMPoint southwest) {
        this.northeast = northeast;
        this.southwest = southwest;
    }

    public GSMPoint getNortheast() {
        return northeast;
    }

    public void setNortheast(GSMPoint northeast) {
        this.northeast = northeast;
    }

    public GSMPoint getSouthwest() {
        return southwest;
    }

    public void setSouthwest(GSMPoint southwest) {
        this.southwest = southwest;
    }

    
}
