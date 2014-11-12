package tr.edu.ege.cs.egenav.mapcache;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import tr.edu.ege.cs.egenav.MapURL;

/**
 * @author Özgün Yılmaz
 * Created on 26.May.2014, 13:49:20
 */
public abstract class MapCache {
    
    private String path;
    private int limit=100;
    private int timeLimit=-1;
    private boolean partialMapsSupported=true;

    public MapCache() {
    
    }
    
    public MapCache(String filePath) {
        File f=new File(filePath);
        f.mkdirs();
        if (filePath.endsWith("\\")){
            this.path = filePath;
        }else{
            this.path = filePath+"\\";
        }
        
    }
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
    
    public String getImagePath(){
        return getPath()+"images\\";
    }

    public boolean isPartialMapsSupported() {
        return partialMapsSupported;
    }

    public void setPartialMapsSupported(boolean partialMapsSupported) {
        this.partialMapsSupported = partialMapsSupported;
    }
    
    public abstract String getCacheFileAbsoluteName();
    public abstract BufferedImage getMap(MapURL mapurl) throws IOException;
    public abstract MapInfo find(MapURL mapurl);
    public abstract boolean isLimitReached();
    public abstract void deleteDateDueMaps();
    public abstract void removeLeastUsed();
    public abstract void close();
    
    
    
    
}
