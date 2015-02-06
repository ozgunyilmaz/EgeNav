package tr.edu.ege.cs.egenav.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Özgün Yılmaz
 * Created on 06.Haz.2014, 14:37:14
 */
public class NavInfoList {
    
    private ArrayList<NavInfo> nav=new ArrayList<NavInfo>();
    
    public NavInfoList(String fileName){
        
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) { // while loop begins here
                //System.out.println(line);
                String[] st=line.split(",");
                double lat=Double.parseDouble(st[0]);
                double lon=Double.parseDouble(st[1]);
                long time;
                if (st.length>2){
                    time=Long.parseLong(st[2]);
                }else{
                    time=0;
                }
                nav.add(new NavInfo(lat,lon,time));
                
            } // end while 
        } catch (IOException ex) {
            Logger.getLogger(NavInfoList.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex, "Error occurred", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public ArrayList<NavInfo> getNav() {
        return nav;
    }
    
    public NavInfo get(int i){
        return nav.get(i);
    }
    
    public int size(){
        return nav.size();
    }
    
}
