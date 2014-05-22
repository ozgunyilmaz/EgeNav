/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.ege.cs.egenav.ui;

/**
 *
 * @author Özgün Yılmaz
 */
public interface NavigationInformation {
    
    public void setLatitude(double lat);
    public void setLongitude(double lon);
    public void setHeading(double heading);
    public void setSpeed(double speed);
    public void setTotalDistance(double dist);
    public void setTimeElapsed(double timeElapsed);
    public void setAverageSpeed(double averagSpeed);
    public void setInstructions(String ins);
}
