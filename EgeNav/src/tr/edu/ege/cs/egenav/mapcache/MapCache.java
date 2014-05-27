package tr.edu.ege.cs.egenav.mapcache;

import java.awt.image.BufferedImage;
import tr.edu.ege.cs.egenav.MapURL;

/**
 * @author Özgün Yılmaz
 * Created on 26.May.2014, 13:49:20
 */
public abstract class MapCache {
    
    private String path;
    private int limit=100;
    private int timeLimit=-1;

    public MapCache() {
    
    }
    
    public MapCache(String filePath) {
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
        return getPath()+"\\images\\";
    }
    
    public abstract String getCacheFileAbsoluteName();

    public abstract BufferedImage getMap(MapURL mapurl);
    public abstract void close();
    public abstract boolean isLimitReached();
    
    
}
