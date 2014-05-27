package tr.edu.ege.cs.egenav.mapcache;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Özgün Yılmaz
 * Created on 26.May.2014, 13:51:00
 */
public class MapInfo implements Serializable{
    
    private String mapurl;
    private String imageFileName;
    private Date downloadDate;
    private int usageCount;

    public MapInfo(String mapurl, String format) {
        this.mapurl = mapurl;
        
        downloadDate=Calendar.getInstance().getTime();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        
        imageFileName="map_image"+dateFormat.format(downloadDate)+"."+format;
        usageCount=1;
    }

    public MapInfo(String mapurl, String imageFileName, Date downloadDate, int usageCount) {
        this.mapurl = mapurl;
        this.imageFileName = imageFileName;
        this.downloadDate = downloadDate;
        this.usageCount = usageCount;
    }
    
    
    
    public boolean deleteImageFile(){
        
        File f=new File(imageFileName);
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
