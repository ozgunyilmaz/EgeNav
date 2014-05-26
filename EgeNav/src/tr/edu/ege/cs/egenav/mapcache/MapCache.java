package tr.edu.ege.cs.egenav.mapcache;

import java.awt.image.BufferedImage;
import tr.edu.ege.cs.egenav.MapURL;

/**
 * @author Özgün Yılmaz
 * Created on 26.May.2014, 13:49:20
 */
public abstract class MapCache {
    
    private String filePath;
    private int limit=100;

    public MapCache() {
    
    }
    
    public MapCache(String filePath) {
        this.filePath = filePath;
    }
    
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
    
    

    public abstract BufferedImage getMap(MapURL mapurl);
    public abstract void close();
    
    
}
