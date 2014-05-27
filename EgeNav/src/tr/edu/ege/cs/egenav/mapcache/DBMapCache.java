package tr.edu.ege.cs.egenav.mapcache;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.edu.ege.cs.egenav.MapURL;

/**
 * @author Özgün Yılmaz
 * Created on 27.May.2014, 11:27:25
 */
public class DBMapCache extends MapCache{

    Connection c = null;
    
    public DBMapCache() {
        
        this(System.getProperty("user.dir"));
        
    }

    public DBMapCache(String filePath) {
        super(filePath);
        
        File f=new File(getCacheFileAbsoluteName());
        boolean exist=f.exists();
        //System.out.println(f.exists());
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+getCacheFileAbsoluteName());

            System.out.println("Opened database successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!exist){
            //yeni tablo yarat
            createTable();
            
        }
    }
    
    private void createTable(){
        try {
            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE Maps " +
                       "(Mapurl TEXT PRIMARY KEY NOT NULL," +
                       " ImageFileName  TEXT    NOT NULL, " + 
                       " DownloadDate   INTEGER NOT NULL, " + 
                       " UsageCount     INTEGER NOT NULL)"; 
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
////    private void deleteDateDueMaps(){
////        
////        if (getTimeLimit()<0){
////            return;
////        }
////        for (int i=0;i<maps.size();i++){
////            
////            MapInfo m=maps.get(i);
////            long now=Calendar.getInstance().getTimeInMillis();
////            long past=m.getDownloadDate().getTime();
////            int day=(int)((now-past)/((24 * 60 * 60 * 1000)));
////            
////            
////            if (day>getTimeLimit()){
////                maps.remove(i);
////                i--;
////                m.deleteImageFile();
////            }
////        }
////        
////    }
////    
////    @Override
////    public boolean isLimitReached(){
////        if (getLimit()>0){
////            return getLimit()<=maps.size();
////        }else{
////            return false;
////        }
////        
////    }
////    
////    @Override
////    public BufferedImage getMap(MapURL mapurl) {
////        
////        String mstr=mapurl.getAbsoluteURLString();
////        MapInfo m=find(mstr);
////        if (m==null){
////            BufferedImage bim=MapDownloader.downloadMap(mstr);
////            
////            String fs;
////            if (mapurl.getFormat()==null || mapurl.getFormat().equals("")){
////                fs="png";
////            }else{
////                fs=mapurl.getFormat();
////            }
////            
////            MapInfo minfo=new MapInfo(mstr,fs);    //Eğer limitse çıkarılmalı
////            
////            File outputfile = new File(getImagePath() + minfo.getImageFileName());
////            try {
////                
////                ImageIO.write(bim, fs, outputfile);
////            } catch (IOException ex) {
////                Logger.getLogger(MemoryMapCache.class.getName()).log(Level.SEVERE, null, ex);
////            }
////            
////            if (isLimitReached()){
////                removeLeastUsed();  
////                //en az sayıda kullanılanı ve en eski olanı çıkar ve image dosyasını sil.
////            }
////            
////            maps.add(minfo);
////            
////            return bim;
////        }else{
////            m.incrementUsageCount();
////            String fname=m.getImageFileName();
////            try {
////                return ImageIO.read(new File(getImagePath()+fname));
////            } catch (IOException ex) {
////                Logger.getLogger(MemoryMapCache.class.getName()).log(Level.SEVERE, null, ex);
////                return null;
////            }
////            
////        }
////        
////    }
////    
////    private void removeLeastUsed(){
////        
////        int index=-1,eks=1000;
////        for (int i=0;i<maps.size();i++){
////            
////            MapInfo m=maps.get(i);
////            if (m.getUsageCount()<eks){
////                
////                index=i;
////                
////            }
////        }
////        MapInfo m=maps.get(index);
////        m.deleteImageFile();
////        
////    }
////    
////    public MapInfo find(String mapurl){
////        
////        for (int i=0;i<maps.size();i++){
////            if (maps.get(i).getMapurl().equals(mapurl)){
////                return maps.get(i);
////            }
////        }
////        return null;
////    }
////
////    @Override
////    public void close() {
////        FileOutputStream fout = null;
////        try {
////            fout = new FileOutputStream(getFilePath()+"\\mapdata");
////            ObjectOutputStream oos = new ObjectOutputStream(fout);
////            oos.writeObject(maps);
////            oos.close();
////            System.out.println("Done");
////        } catch (Exception ex) {
////            Logger.getLogger(MemoryMapCache.class.getName()).log(Level.SEVERE, null, ex);
////        } finally {
////            try {
////                fout.close();
////            } catch (IOException ex) {
////                Logger.getLogger(MemoryMapCache.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        }
////    }
////    
////    public static void main(String args[]){
////        MemoryMapCache m=new MemoryMapCache();
////    }
    
    @Override
    public final String getCacheFileAbsoluteName() {
        return getPath()+"mapdata.db";
    }

    @Override
    public BufferedImage getMap(MapURL mapurl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isLimitReached() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
