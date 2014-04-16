package tr.edu.ege.cs.egenav;

/**
 * @author Özgün Yılmaz
 * Created on 04.Nis.2014, 14:35:45
 */
public class GSMMapURL extends MapURL{
    
    //marker,styled maps, paths
    //visible parametresini ekle.
    
    public GSMMapURL(){
        setMiddleURL("maps.googleapis.com/maps/api/staticmap?");
    }

    @Override
    public String toString() {
        
        String url;
        
        if (isSecure()){
            url="https://";
        }else{
            url="http://";
        }
        
        url=url+getMiddleURL();
        
        if (getCenter()!=null){
            url=url+"center="+getCenter().toString()+"&";
        }
        
        if (getZoom()!=-1){
            url=url+"zoom="+getZoom()+"&";
        }
        
        url=url+"size="+getMapSize().getHorizantal()+"x"+getMapSize().getVertical()+"&"+
                "sensor="+isSensor()+"&";
        
        if (getScale()!=-1){
            url=url+"scale="+getScale()+"&";
        }
        
        if (getFormat()!=null && !getFormat().isEmpty()){
            
            url=url+"format="+getFormat()+"&";
        }
        
        if (getMapType()!=null && !getMapType().isEmpty()){
            
            url=url+"maptype="+getMapType()+"&";
        }
        
        if (getLanguage()!=null && !getLanguage().isEmpty()){
            url=url+"language="+getLanguage()+"&";
        }
                
        if (getRegion()!=null && !getRegion().isEmpty()){
            url=url+"region="+getRegion()+"&";
        }
        
        while (url.endsWith("&")){
            url=url.substring(0, url.lastIndexOf("&"));
        }
        
        
        
        return url;
    }
    
}
