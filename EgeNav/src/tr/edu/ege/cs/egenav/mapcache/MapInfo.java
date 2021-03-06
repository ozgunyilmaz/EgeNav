package tr.edu.ege.cs.egenav.mapcache;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import tr.edu.ege.cs.egenav.MapURL;

/**
 * @author Özgün Yılmaz
 * Created on 26.May.2014, 13:51:00
 */
public class MapInfo implements Serializable{
    
    private String mapurl;
    private MapURL mobject;
    private String imageFileName;
    private Date downloadDate;
    private int usageCount;
    
    public MapInfo(String mapurl, String format, String path) {
        this.mapurl = mapurl;
        downloadDate=Calendar.getInstance().getTime();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        
        imageFileName="map_image"+dateFormat.format(downloadDate)+"."+format;
        File f=new File(path + imageFileName);
        int i=1;
        while (f.exists()){
            i++;
            f=new File(path + imageFileName + "(" + i +")");
        }
        imageFileName=f.getAbsolutePath();
        usageCount=1;
    }
    
    public MapInfo(MapURL mobject, String format, String path) {
        this.mobject = mobject;
        downloadDate=Calendar.getInstance().getTime();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        
        imageFileName="map_image"+dateFormat.format(downloadDate)+"."+format;
        File f=new File(path + imageFileName);
        int i=1;
        while (f.exists()){
            i++;
            f=new File(path + "map_image"+dateFormat.format(downloadDate) + "(" + i +")."+format);
        }
        imageFileName=f.getName();
        usageCount=1;
    }

    public MapInfo(String mapurl, String imageFileName, Date downloadDate, int usageCount) {
        this.mapurl = mapurl;
        this.imageFileName = imageFileName;
        this.downloadDate = downloadDate;
        this.usageCount = usageCount;
    }
    
    public MapInfo(MapURL mobject, String imageFileName, Date downloadDate, int usageCount) {
        this.mobject = mobject;
        this.imageFileName = imageFileName;
        this.downloadDate = downloadDate;
        this.usageCount = usageCount;
    }

    public MapURL getMapObject() {
        return mobject;
    }

    public void setMapObject(MapURL mobject) {
        this.mobject = mobject;
    }
    
    public boolean deleteImageFile(String path){
        
        File f=new File(path+imageFileName);
        return f.delete();
        
    }

    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(Date downloadDate) {
        this.downloadDate = downloadDate;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
    
    public void updateImageFileName(){
        
    }

    public String getMapurl() {
        return mapurl;
    }

    public void setMapurl(String mapurl) {
        this.mapurl = mapurl;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
    }
    
    

    public void incrementUsageCount() {
        usageCount++;
    }
    
    
    
}
