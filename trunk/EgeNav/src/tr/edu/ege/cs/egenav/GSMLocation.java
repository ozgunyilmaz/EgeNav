package tr.edu.ege.cs.egenav;

import java.awt.Point;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 14:29:26
 */
public class GSMLocation extends Location{
    
    private GeoPoint point=null;
    private String address="";
    
    public GSMLocation(GeoPoint point){
        this.point=point;
    }
    
    public GSMLocation(String address){
        this.address=address;
    }

    public String getAddress() {
        return address;
    }

    public GeoPoint getCoordinate() {
        return point;
    }
    
    public boolean isAbsolute(){
        return point!=null;
    }
    
    public boolean isAddress(){
        return point==null;
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
            return point.toString();
        }
    }
    
    @Override
    public GSMLocation clone(){
        
        if (isAddress()){
            return new GSMLocation(getAddress());
        }else{
            return new GSMLocation(getCoordinate().clone());
        }
        
    }

    @Override
    public double getDistanceTo(Location location) {
        
        //Google geocoding api ile lat ve lon adres için de elde edilebilir.
        //Adresi parametre olarak alan constructor'da lat ve lon otomatik elde edilebilir.
        return getCoordinate().getDistanceTo(location);
        
    }

    @Override
    public double getLatitude() {
        return getCoordinate().getLatitude();
    }

    @Override
    public double getLongitude() {
        return getCoordinate().getLongitude();
    }

    @Override
    public void setLatitude(double lat) {
        getCoordinate().setLatitude(lat);
    }

    @Override
    public void setLongitude(double lon) {
        getCoordinate().setLongitude(lon);
    }
    
}
