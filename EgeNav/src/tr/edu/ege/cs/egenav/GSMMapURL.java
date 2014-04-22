package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 14:35:45
 */
public class GSMMapURL extends MapURL{
    
    //marker,styled maps, paths
    //visible parametresini ekle.
    
    private boolean sensor=false;
    private String apiKey,signature,language,region,mapType,format;
    private int scale=-1;
    
    public static final int MAX_ZOOM=21;
    
    
    public GSMMapURL(){
        setMiddleURL("maps.googleapis.com/maps/api/staticmap?");
        setSeparator("&");
    }
    
    public GSMMapURL(String apiKey){
        this();
        this.apiKey=apiKey;
    }
    
    public GSMMapURL(String clientID,String signature){
        this();
        setClientID(clientID);
        this.signature=signature;
    }
    
    @Override
    public boolean incrementZoom(){
        if (getZoom()<MAX_ZOOM){
            incrementZoom();
            return true;
        }
        return false;
    }
    
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    @Override
    public GSMLocation getLocation(){
        return (GSMLocation)getLocation();
    }
    
    public GSMLocation getCenter() {
        return getLocation();
    }

    public void setCenter(GSMLocation center) {
        setLocation(center);
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

    @Override
    public String getAbsoluteURLString() {
        
        String url;
        
        if (isSecure()){
            url="https://";
        }else{
            url="http://";
        }
        
        url=url+getMiddleURL();
        
        if (getCenter()!=null){
            url=url+"center="+getCenter().toString()+getSeparator();
        }
        
        if (getZoom()!=-1){
            url=url+"zoom="+getZoom()+getSeparator();
        }
        
        url=url+"size="+getMapSize().getHorizantal()+"x"+getMapSize().getVertical()+getSeparator()+
                "sensor="+isSensor()+getSeparator();
        
        if (getScale()!=-1){
            url=url+"scale="+getScale()+getSeparator();
        }
        
        if (getFormat()!=null && !getFormat().isEmpty()){
            
            url=url+"format="+getFormat()+getSeparator();
        }
        
        if (getMapType()!=null && !getMapType().isEmpty()){
            
            url=url+"maptype="+getMapType()+getSeparator();
        }
        
        if (getLanguage()!=null && !getLanguage().isEmpty()){
            url=url+"language="+getLanguage()+getSeparator();
        }
                
        if (getRegion()!=null && !getRegion().isEmpty()){
            url=url+"region="+getRegion()+getSeparator();
        }
        
        for (int i=0;i<parameters.size();i++){
            url=url+parameters.get(i).toString()+getSeparator();
        }
        
        while (url.endsWith(getSeparator())){
            url=url.substring(0, url.lastIndexOf(getSeparator()));
        }
        
        
        
        return url;
    }

    @Override
    public GSMMapURL clone() {
        
        GSMMapURL gsm=new GSMMapURL();
        gsm.setApiKey(getApiKey());
        gsm.setCenter(getCenter().clone());
        gsm.setClientID(getClientID());
        gsm.setFormat(getFormat());
        gsm.setLanguage(getLanguage());
        gsm.setMapSize(getMapSize().clone());
        gsm.setMapType(getMapType());
        gsm.setMiddleURL(getMiddleURL());
        gsm.setRegion(getRegion());
        gsm.setScale(getScale());
        gsm.setSecure(isSecure());
        gsm.setSensor(isSensor());
        gsm.setSeparator(getSeparator());
        gsm.setSignature(getSignature());
        gsm.setZoom(getZoom());
        
        return gsm;
    }
    
}
