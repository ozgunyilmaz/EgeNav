package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 15.May.2014, 14:58:19
 */
public class GSMDirectionResponse {
    
    private String status;
    private GSMRoute[] routes;

    public GSMRoute[] getRoutes() {
        return routes;
    }

    public void setRoutes(GSMRoute[] routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
