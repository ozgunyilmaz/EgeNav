package tr.edu.ege.cs.egenav;

import java.util.ArrayList;

/**
 * @author Özgün Yılmaz
 * Created on 16.Nis.2014, 13:29:08
 */
public class GSMViewport extends Parameter{

    private ArrayList<Location> loc=new ArrayList<Location>();
    
    @Override
    public String toString() {
        
        String str="visible=";
        for (int i=0;i<loc.size();i++){
            str=str+loc.get(i).toString()+"|";
        }
        
        while (str.endsWith("|")){
            str=str.substring(0, str.lastIndexOf("|"));
        }
        
        return str;
    }
    
}
