package tr.edu.ege.cs.egenav;

import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 16.Nis.2014, 11:53:33
 */
public class GSMPath extends Parameter{
    
    private GSMPathStyle ps;
    
    private String encodedPath;
    private ArrayList<GeoPoint> path=null;

    public GSMPath(GSMPathStyle ps) {
        
        this.ps = ps;
        path=new ArrayList<GeoPoint>();
        
    }

    public GSMPath(GSMPathStyle ps, String encodedPath) {
        this.ps = ps;
        this.encodedPath = encodedPath;
    }

    public String getEncodedPath() {
        return encodedPath;
    }

    

    public void addPathPoint(GeoPoint c){
        path.add(c);
    }
    
    public void appendPath(ArrayList<GeoPoint> p){
        path.addAll(p);
    }
    
    public boolean removePathPoint(GeoPoint c){
        return path.remove(c);
    }
    
    public GeoPoint removePathPoint(int i){
        return path.remove(i);
    }

    public GSMPathStyle getPathStyle() {
        return ps;
    }

    public void setPathStyle(GSMPathStyle ps) {
        this.ps = ps;
    }
    
    @Override
    public String toString(){
        
        String p="path=";
        
        if (getPathStyle()!=null){
            p=p+getPathStyle().toString()+"|";
        }
        
        
        if (path!=null){
            
            for (int i=0;i<path.size();i++){
                p=p+ path.get(i).toString()+"|";
            }
            
        }else{
            p=p+"enc:"+getEncodedPath()+"|";
        }
        
        while (p.endsWith("|")){
            p=p.substring(0, p.lastIndexOf("|"));
        }
        
        return p;
    }
    
    
}
