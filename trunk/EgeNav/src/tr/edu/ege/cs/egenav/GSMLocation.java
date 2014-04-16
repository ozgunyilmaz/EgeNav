package tr.edu.ege.cs.egenav;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 14:29:26
 */
public class GSMLocation {
    
    private Coordinate coordinate=null;
    private String address="";
    
    public GSMLocation(Coordinate coordinate){
        this.coordinate=coordinate;
    }
    
    public GSMLocation(String address){
        this.address=address;
    }

    public String getAddress() {
        return address;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
    
    public boolean isAbsolute(){
        return coordinate!=null;
    }
    
    public boolean isAddress(){
        return coordinate==null;
    }
    
    private String encodeAddress(){
        try {
            return URLEncoder.encode(address, "ISO-8859-1");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GSMLocation.class.getName()).log(Level.SEVERE, null, ex);
            return address.replace(" ", "+");
        }
    }
    
    @Override
    public String toString(){
        if (isAddress()){
            return encodeAddress();
        }else{
            return coordinate.toString();
        }
    }
}
