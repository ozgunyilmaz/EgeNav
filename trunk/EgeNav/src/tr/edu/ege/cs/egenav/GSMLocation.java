package tr.edu.ege.cs.egenav;

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

    public GeoPoint getGeoPoint() {
        return point;
    }
    
    public boolean isAbsolute(){
        return point!=null;
    }
    
    public boolean isAddress(){
        return point==null;
    }
    
    private String encodedAddress(){
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
            return encodedAddress();
        }else{
            return point.toString();
        }
    }
    
    @Override
    public GSMLocation clone(){
        
        if (isAddress()){
            return new GSMLocation(getAddress());
        }else{
            return new GSMLocation(getGeoPoint().clone());
        }
        
    }

    @Override
    public double getDistanceTo(Location location) {
        
        //Google geocoding api ile lat ve lon adres için de elde edilebilir.
        //Adresi parametre olarak alan constructor'da lat ve lon otomatik elde edilebilir.
        return getGeoPoint().getDistanceTo(new GeoPoint(location.getLatitude(),location.getLongitude()));
        
    }

    @Override
    public double getLatitude() {
        return getGeoPoint().getLatitude();
    }

    @Override
    public double getLongitude() {
        return getGeoPoint().getLongitude();
    }

    @Override
    public void setLatitude(double lat) {
        getGeoPoint().setLatitude(lat);
    }

    @Override
    public void setLongitude(double lon) {
        getGeoPoint().setLongitude(lon);
    }
    
}
