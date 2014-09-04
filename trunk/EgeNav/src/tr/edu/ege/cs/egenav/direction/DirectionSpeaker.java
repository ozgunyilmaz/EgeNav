package tr.edu.ege.cs.egenav.direction;

import com.sun.speech.freetts.Voice;

/**
 * @author Özgün Yılmaz
 * Created on 04.Eyl.2014, 15:15:30
 */
public class DirectionSpeaker extends Thread{
    
    String ins;
    Voice voice;
    
    public DirectionSpeaker(Voice voice,String ins){
        
        this.voice=voice;
        
        
        while(ins.indexOf("<")>=0){
            int i=ins.indexOf("<");
            int j=ins.indexOf(">");
            ins=ins.substring(0,i)+ins.substring(j+1);
        }
        
        this.ins=ins;
        
    }
    
    @Override
    public void run(){
        
        //System.out.println(ins);
        voice.speak(ins);
        
    }
    
}
