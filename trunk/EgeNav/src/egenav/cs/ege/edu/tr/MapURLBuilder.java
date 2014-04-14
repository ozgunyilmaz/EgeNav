package egenav.cs.ege.edu.tr;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 13:28:15
 */
public abstract class MapURLBuilder {
    
    private boolean secure=false,sensor=false;
    private String apiKey,clientID,signature,midURL,language="",region="",mapType="",format="";
    private MapCenter center=null;
    private int zoom=-1,scale=-1;
    private MapSize mapSize;
    
    
    public MapURLBuilder(){
        
    }
    
    public MapURLBuilder(String apiKey){
        this.apiKey=apiKey;
    }
    
    public MapURLBuilder(String clientID,String signature){
        this.clientID=clientID;
        this.signature=signature;
    }
    
    @Override
    public abstract String toString();
    
    public void setSecure(boolean b){
        secure=b;
    }
    
    public boolean isSecure(){
        return secure;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public MapCenter getCenter() {
        return center;
    }

    public void setCenter(MapCenter center) {
        this.center = center;
    }
    
    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getMiddleURL() {
        return midURL;
    }

    public void setMiddleURL(String midURL) {
        this.midURL = midURL;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public MapSize getMapSize() {
        return mapSize;
    }

    public void setMapSize(MapSize mapSize) {
        this.mapSize = mapSize;
    }

    public boolean isSensor() {
        return sensor;
    }

    public void setSensor(boolean sensor) {
        this.sensor = sensor;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    
    
    
    
}
