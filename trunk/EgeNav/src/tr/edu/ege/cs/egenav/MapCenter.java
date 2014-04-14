package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 14:29:26
 */
public class MapCenter {
    
    private Coordinate coordinate=null;
    private String address="";
    
    public MapCenter(Coordinate coordinate){
        this.coordinate=coordinate;
    }
    
    public MapCenter(String address){
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
        return address.replace(" ", "+");
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
