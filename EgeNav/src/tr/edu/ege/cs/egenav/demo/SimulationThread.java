package tr.edu.ege.cs.egenav.demo;

import java.util.logging.Level;
import java.util.logging.Logger;
import tr.edu.ege.cs.egenav.Location;
import tr.edu.ege.cs.egenav.ui.MapPanel;

/**
 * @author Özgün Yılmaz
 * Created on 06.Haz.2014, 14:43:26
 */
public class SimulationThread extends Thread{
    
    private boolean realTime;
    private NavInfoList list;
    private MapPanel mp;
    //private volatile Thread blinker=this;
    private volatile boolean flag=true;


    
    public SimulationThread(MapPanel mp, NavInfoList list){
        this.mp=mp;
        this.list=list;
        realTime=false;
    }
    
    public SimulationThread(MapPanel mp, NavInfoList list, boolean realTime){
        this.mp=mp;
        this.list=list;
        this.realTime=realTime;
    }
    
    @Override
    public void run(){
        
        //Thread thisThread = Thread.currentThread();
        
        int i=0;
        
        while (flag && i<list.size()) {

            NavInfo n=list.get(i);
            if (realTime){
                try {
                    sleep(n.getTime());
                } catch (InterruptedException ex) {
                    Logger.getLogger(SimulationThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (flag){
                mp.updateLocation(new Location(n.getLat(),n.getLon()));
            }
            
            i++;
        }
        
    }
    
    public void stop2() {
        flag = false;
        mp.clearNavigationHistory();
        mp.repaint();
    }


}
