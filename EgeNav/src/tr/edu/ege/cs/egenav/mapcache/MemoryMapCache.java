package tr.edu.ege.cs.egenav.mapcache;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import tr.edu.ege.cs.egenav.MapDownloader;
import tr.edu.ege.cs.egenav.MapPosition;
import tr.edu.ege.cs.egenav.MapURL;
import tr.edu.ege.cs.egenav.direction.Directions;

/**
 * @author Özgün Yılmaz
 * Created on 26.May.2014, 14:40:20
 */
public class MemoryMapCache extends MapCache{
    //todo private metotlar (deleteExceededMaps, vs.) public olabilir mi?
    
    ArrayList<MapInfo> maps;

    public MemoryMapCache() {
        
        this(System.getProperty("user.dir")+"\\maps\\");
        
    }

    public ArrayList<MapInfo> getMaps() {
        return maps;
    }
    
    

    public MemoryMapCache(String filePath) {
        super(filePath);
        
        File f=new File(getCacheFileAbsoluteName());
        //System.out.println(f.exists());
        if (f.exists()){
            FileInputStream fin = null;
            try {
                fin = new FileInputStream(getCacheFileAbsoluteName());
                ObjectInputStream ois = new ObjectInputStream(fin);
                maps = (ArrayList<MapInfo>) ois.readObject();
                deleteDateDueMaps();
                ois.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MemoryMapCache.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MemoryMapCache.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fin.close();
                } catch (IOException ex) {
                    Logger.getLogger(MemoryMapCache.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            
            maps=new ArrayList<MapInfo>();
        }
    }
    
    @Override
    public void deleteDateDueMaps(){
        
        if (getTimeLimit()<0){
            return;
        }
        for (int i=0;i<maps.size();i++){
            
            MapInfo m=maps.get(i);
            long now=Calendar.getInstance().getTimeInMillis();
            long dd=m.getDownloadDate().getTime();
            long due=now-(getTimeLimit() * 24 * 60 *60 *1000);
            
            if (due>dd){
                maps.remove(i);
                i--;
                m.deleteImageFile(getImagePath());
            }
        }
        
    }
    
    @Override
    public boolean isLimitReached(){
        if (getLimit()>0){
            return getLimit()<=maps.size();
        }else{
            return false;
        }
        
    }
    
    @Override
    public BufferedImage getMap(MapURL mapurl) {
        
        //String mstr=mapurl.getAbsoluteURLString();
        MapInfo m=find(mapurl);
        if (m!=null){
            
            return getCachedMap(m);
        }

        if (isPartialMapsSupported()){
            
            BufferedImage bim=getMapFromPartialMaps(mapurl);
            if (bim!=null){
                return bim;
            }
        }

        String fs;
        if (mapurl.getFormat()==null || mapurl.getFormat().equals("")){
            fs="png";
        }else{
            fs=mapurl.getFormat();
        }

        return downloadMap(mapurl,fs);  //*********
        
    }
    
    private BufferedImage getMapFromPartialMaps(MapURL mapurl) {
        
        for (int i=0;i<maps.size();i++){
            MapURL m=maps.get(i).getMapObject();
            MapPosition mp=m.intersects(mapurl);
            if (mp.isIntersection()){
                return composeMap(maps.get(i),mp);
            }
        }
        return null;
    }
    
    private BufferedImage composeMap(MapInfo mi, MapPosition mp){
        
        BufferedImage comp=null;
        MapURL m=mi.getMapObject();
        if (mp.getHorizontalDirection()==Directions.CONSTANT){
                    
            MapURL neighbor=m.getNeighborTile(mp.getVerticalDirection(), mp.getHorizontalDirection());
            BufferedImage bim1=null, bim2=null;
            
            if (mp.getDeltaVertical()>0){
                bim1=getCachedMap(mi);    //0,dv X hor-1,ver-1        0,0      X hor-1,(ver+dv)-1
                bim2=getFullMap(neighbor);//0,0  X hor-1,dv-1         0,ver+dv X hor-1,ver-1
            }else{
                bim1=getFullMap(neighbor);    
                bim2=getCachedMap(mi);
            }
            
            comp=new BufferedImage(bim1.getWidth(),bim1.getHeight(),BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d=comp.createGraphics();
            int ver=bim1.getHeight();
            int hor=bim1.getWidth();
            
            int dv=mp.getDeltaVertical();
            
            if (dv<0){
                dv=ver+dv;
            }
            

//                              //dest                      //source
            g2d.drawImage(bim1, 0, 0,       hor, ver-dv,    0, dv, hor, ver, null);  //0,dv,hor-1,ver-1
            g2d.drawImage(bim2, 0, ver-dv,  hor, ver,       0, 0,  hor, dv, null);

        }else if (mp.getVerticalDirection()==Directions.CONSTANT){
            
            MapURL neighbor=m.getNeighborTile(mp.getVerticalDirection(), mp.getHorizontalDirection());
            BufferedImage bim1=null, bim2=null;
            
            if (mp.getDeltaHorizontal()>0){
                bim1=getCachedMap(mi);    //0,dv X hor-1,ver-1        0,0      X hor-1,(ver+dv)-1
                bim2=getFullMap(neighbor);//0,0  X hor-1,dv-1         0,ver+dv X hor-1,ver-1
            }else{
                bim1=getFullMap(neighbor);    
                bim2=getCachedMap(mi);
            }
            
            comp=new BufferedImage(bim1.getWidth(),bim1.getHeight(),BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d=comp.createGraphics();
            int ver=bim1.getHeight();
            int hor=bim1.getWidth();
            
            int dh=mp.getDeltaHorizontal();
            
            if (dh<0){
                dh=hor+dh;
            }
          
            g2d.drawImage(bim1, 0,      0, hor-dh, ver,    dh, 0, hor, ver, null);  
            g2d.drawImage(bim2, hor-dh, 0, hor,    ver,    0, 0,  dh, ver, null);
            
            
        }else{
            
            BufferedImage bim1=null, bim2=null, bim3=null, bim4=null;
            
            if (mp.getDeltaHorizontal()>0){
                
                if (mp.getDeltaVertical()>0){
                    bim1=getCachedMap(mi);    
                    bim2=getFullMap(m.getNeighborTile(Directions.CONSTANT, mp.getHorizontalDirection()));
                    bim3=getFullMap(m.getNeighborTile(mp.getVerticalDirection(), Directions.CONSTANT));
                    bim4=getFullMap(m.getNeighborTile(mp.getVerticalDirection(), mp.getHorizontalDirection()));
                }else{
                    bim1=getFullMap(m.getNeighborTile(mp.getVerticalDirection(), Directions.CONSTANT));
                    bim2=getFullMap(m.getNeighborTile(mp.getVerticalDirection(), mp.getHorizontalDirection()));
                    bim3=getCachedMap(mi);
                    bim4=getFullMap(m.getNeighborTile(Directions.CONSTANT, mp.getHorizontalDirection()));
                }
                
            }else{
                if (mp.getDeltaVertical()>0){
                    bim1=getFullMap(m.getNeighborTile(Directions.CONSTANT, mp.getHorizontalDirection()));
                    bim2=getCachedMap(mi);
                    bim3=getFullMap(m.getNeighborTile(mp.getVerticalDirection(), mp.getHorizontalDirection()));
                    bim4=getFullMap(m.getNeighborTile(mp.getVerticalDirection(), Directions.CONSTANT));
                }else{
                    bim1=getFullMap(m.getNeighborTile(mp.getVerticalDirection(), mp.getHorizontalDirection()));
                    bim2=getFullMap(m.getNeighborTile(mp.getVerticalDirection(), Directions.CONSTANT));
                    bim3=getFullMap(m.getNeighborTile(Directions.CONSTANT, mp.getHorizontalDirection()));
                    bim4=getCachedMap(mi);    
                }
            }
            
            comp=new BufferedImage(bim1.getWidth(),bim1.getHeight(),BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d=comp.createGraphics();
            int ver=bim1.getHeight();
            int hor=bim1.getWidth();
            
            int dh=mp.getDeltaHorizontal();
            int dv=mp.getDeltaVertical();
            
            if (dh<0){
                dh=hor+dh;
            }
            
            if (dv<0){
                dv=ver+dv;
            }
            
            g2d.drawImage(bim1, 0,      0, hor-dh, ver-dv,    dh, dv, hor, ver, null);  //0,dv,hor-1,ver-1
            g2d.drawImage(bim2, hor-dh, 0, hor,    ver-dv,    0,  dv,  dh, ver, null);
            g2d.drawImage(bim3, 0, ver-dv,  hor-dh, ver,      dh, 0,  hor, dv, null);
            g2d.drawImage(bim4, hor-dh, ver-dv,  hor, ver,    0, 0,  dh, dv, null);
        }
        
        return comp;
    }
    
    private BufferedImage downloadMap(MapURL mapurl, String fs) {
        
        BufferedImage bim=MapDownloader.downloadMap(mapurl.getAbsoluteURLString()); //******
        
        if (bim==null){
            return null;
        }
            
        MapInfo minfo=new MapInfo(mapurl.clone(),fs);    //Eğer limitse çıkarılmalı     //******

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

        maps.add(minfo);

        return bim;
        
    }
    
    private BufferedImage getCachedMap(MapInfo m) {
        m.incrementUsageCount();
        String fname=m.getImageFileName();
        try {
            return ImageIO.read(new File(getImagePath()+fname));
        } catch (IOException ex) {
            Logger.getLogger(MemoryMapCache.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private BufferedImage getFullMap(MapURL mapurl) {
        
        //String mstr=mapurl.getAbsoluteURLString();
        MapInfo m=find(mapurl);
        if (m==null){

            String fs;
            if (mapurl.getFormat()==null || mapurl.getFormat().equals("")){
                fs="png";
            }else{
                fs=mapurl.getFormat();
            }

            return downloadMap(mapurl,fs);

        }else{

            return getCachedMap(m);
            
        }
    }
    
    @Override
    public void removeLeastUsed(){
        
        int index=-1,eks=100000;
        for (int i=0;i<maps.size();i++){
            
            MapInfo m=maps.get(i);
            if (m.getUsageCount()<eks){
                //Önce en az kullanılma sayısını bul, sonra o sayıda kullanılan ilk elemanı sil
                eks=m.getUsageCount();
                index=i;
                
            }
        }
        if (index>0){
            MapInfo m=maps.get(index);
            maps.remove(index);
            m.deleteImageFile(getImagePath());
        }
        
        
    }
    
    @Override
    public MapInfo find(MapURL mapurl){
        
        for (int i=0;i<maps.size();i++){
            if (maps.get(i).getMapObject().getAbsoluteURLString().equals(mapurl.getAbsoluteURLString())){
                return maps.get(i);
            }
        }
        return null;
    }

    @Override
    public void close() {
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(getCacheFileAbsoluteName());
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(maps);
            oos.close();
            //System.out.println("Done");
        } catch (IOException ex) {
            Logger.getLogger(MemoryMapCache.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fout.close();
            } catch (IOException ex) {
                Logger.getLogger(MemoryMapCache.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public final String getCacheFileAbsoluteName() {
        return getPath()+"mapdata";
    }
    
}
