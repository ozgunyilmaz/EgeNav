package tr.edu.ege.cs.egenav;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Özgün Yılmaz
 * Created on 21.Nis.2014, 14:14:03
 */
public class Location implements Serializable{
    
    private String description, address;
    private GeoPoint geoPoint;
    
    public Location() {
        
    }

    public Location(String address) {
        this.address = address;
    }

    public Location(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }
    
    public Location(double lat,double lon) {
        this.geoPoint = new GeoPoint(lat,lon);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }
    
    public boolean isAbsolute(){
        return geoPoint!=null;
    }
    
    public boolean isAddress(){
        return geoPoint==null;
    }
    
    private String encodedAddress(){
        try {
            return URLEncoder.encode(address, "ISO-8859-1");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Location.class.getName()).log(Level.SEVERE, null, ex);
            return address.replace(" ", "+");
        }
    }
    
    
    @Override
    public String toString(){
        if (isAddress()){
            return encodedAddress();
        }else{
            return geoPoint.toString();
        }
    }
    
    
    @Override
    public Location clone(){
        
        if (isAddress()){
            return new Location(getAddress());
        }else{
            return new Location(geoPoint.clone());
        }
        
    }

    
    public double getDistanceTo(Location location) {
        
        //Google geocoding api ile lat ve lon adres için de elde edilebilir.
        //Adresi parametre olarak alan constructor'da lat ve lon otomatik elde edilebilir.
        return getGeoPoint().getDistanceTo(location.getGeoPoint());
        
    }

    
    public double getLatitude() {
        return getGeoPoint().getLatitude();
    }

    
    public double getLongitude() {
        return getGeoPoint().getLongitude();
    }

    
    public void setLatitude(double lat) {
        getGeoPoint().setLatitude(lat);
    }

    
    public void setLongitude(double lon) {
        getGeoPoint().setLongitude(lon);
    }
    
}
