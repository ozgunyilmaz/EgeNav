package tr.edu.ege.cs.egenav.mapcache;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import tr.edu.ege.cs.egenav.MapDownloader;
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
            

            //System.out.println("Opened database successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!exist){
            //yeni tablo yarat
            createTable();
            
        }else{
            deleteDateDueMaps();
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
    
    private void deleteDateDueMaps(){
        
        if (getTimeLimit()<0){
            return;
        }

        long now=Calendar.getInstance().getTimeInMillis();
        long tl=getTimeLimit()* 24 * 60 * 60 * 1000;
        long due=now-tl;
            
        try {    
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT ImageFileName FROM Maps WHERE DownloadDate<"+due+";" );
            while ( rs.next() ) {
                
                String  fname = rs.getString("ImageFileName");
                File f=new File(getImagePath()+fname);
                f.delete();
                
            }
            
            rs.close();
            String sql = "DELETE from Maps where DownloadDate<"+due+";";
            stmt.executeUpdate(sql);
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public boolean isLimitReached(){
        if (getLimit()<=0){
            return false;
        }
        
        try {    
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT COUNT(*) FROM Maps;" );
            int x=rs.getInt(1);
            

            rs.close();
            
            stmt.close();
            return getLimit()<=x;
        } catch (SQLException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public void setAutoCommit(boolean b){
        try {
            c.setAutoCommit(b);
        } catch (SQLException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public BufferedImage getMap(MapURL mapurl) {
        
        String mstr=mapurl.getAbsoluteURLString();
        MapInfo m=find(mstr);
        if (m==null){
            BufferedImage bim=MapDownloader.downloadMap(mstr);
            
            String fs;
            if (mapurl.getFormat()==null || mapurl.getFormat().equals("")){
                fs="png";
            }else{
                fs=mapurl.getFormat();
            }
            
            MapInfo minfo=new MapInfo(mstr,fs);    //Eğer limitse çıkarılmalı
            
            File outputfile = new File(getImagePath() + minfo.getImageFileName());
            outputfile.getParentFile().mkdirs();
            try {
                
                ImageIO.write(bim, fs, outputfile);
            } catch (IOException ex) {
                Logger.getLogger(MemoryMapCache.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            if (isLimitReached()){
                removeLeastUsed();  
                //en az sayıda kullanılanı ve en eski olanı çıkar ve image dosyasını sil.
            }
            
            addToDB(minfo);
            
            return bim;
        }else{
            m.incrementUsageCount();
            updateToDB(m);
            String fname=m.getImageFileName();
            try {
                return ImageIO.read(new File(getImagePath()+fname));
            } catch (IOException ex) {
                Logger.getLogger(MemoryMapCache.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            
        }
        
    }
    
    public ArrayList<MapInfo> getMaps() {
        
        ArrayList<MapInfo> maps=new ArrayList<MapInfo>();
        try {    
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Maps;" );
            
            while ( rs.next() ) {
                
                MapInfo m=new MapInfo(rs.getString("Mapurl"),rs.getString("ImageFileName"),new Date(rs.getLong("DownloadDate")),rs.getInt("UsageCount"));
                maps.add(m);
                
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return maps;
    }
    
    private void removeLeastUsed(){
        //en az sayıda kullanılanı ve en eski olanı çıkar ve image dosyasını sil.
        
        try {    
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Maps GROUP BY UsageCount,DownloadDate;" );
            
            String  fname = rs.getString("ImageFileName");
            File f=new File(getImagePath()+fname);
            f.delete();
            rs.close();
            
            String sql = "DELETE from Maps where Mapurl='"+rs.getString("Mapurl")+"';";
            stmt.executeUpdate(sql);
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MapInfo find(String mapurl){
        MapInfo m=null;
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Maps WHERE Mapurl='"+mapurl+"';" );
            m=new MapInfo(mapurl,rs.getString("ImageFileName"),new Date(rs.getLong("DownloadDate")),rs.getInt("UsageCount"));
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return m;
    }
    
    public void addToDB(MapInfo m){
        try {
            Statement stmt = c.createStatement();
            String sql = "INSERT INTO Maps (Mapurl,ImageFileName,DownloadDate,UsageCount) " +
                       "VALUES ('"+m.getMapurl()+"', '"+m.getImageFileName()+"', "+m.getDownloadDate().getTime()+", "+m.getUsageCount()+");"; 
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateToDB(MapInfo m){
        try {
            Statement stmt = c.createStatement();
            String sql = "UPDATE Maps set UsageCount = "+m.getUsageCount()+" where Mapurl='"+m.getMapurl()+"';"; 
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() {
        try {
            if (!c.getAutoCommit()){
                c.commit();
            }
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBMapCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String args[]){
        DBMapCache dnm=new DBMapCache("C:\\Users\\samsung\\Documents\\Deneme2");
        dnm.close();
        File f=new File("C:\\Users\\samsung\\Documents\\Deneme2\\mapdata.db");
        if (f.exists()){
            System.out.println(f.delete());
        }
        
    }

    @Override
    public final String getCacheFileAbsoluteName() {
        return getPath()+"mapdata.db";
    }
    
}
