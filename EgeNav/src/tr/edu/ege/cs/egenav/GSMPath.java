package tr.edu.ege.cs.egenav;

import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 16.Nis.2014, 11:53:33
 */
public class GSMPath {
    
    private GSMPathStyle ps;
    private ArrayList<Coordinate> path=new ArrayList<Coordinate>();

    public GSMPath(GSMPathStyle ps) {
        this.ps = ps;
    }

    public void addPathPoint(Coordinate c){
        path.add(c);
    }
    
    public void appendPath(ArrayList<Coordinate> p){
        path.addAll(p);
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
        
        for (int i=0;i<path.size();i++){
            p=p+ path.get(i).toString()+"|";
        }
        
        while (p.endsWith("|")){
            p=p.substring(0, p.lastIndexOf("|"));
        }
        
        return p;
    }
    
    
}
