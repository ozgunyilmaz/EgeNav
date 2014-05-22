package tr.edu.ege.cs.egenav.ui;

/**
 * @author Özgün Yılmaz
 * Created on 22.May.2014, 15:13:07
 */
public abstract class NavigationInformationAdapter implements NavigationInformation{

    @Override
    public void setLatitude(double lat){}

    @Override
    public void setLongitude(double lon){}

    @Override
    public void setHeading(double heading){}

    @Override
    public void setSpeed(double speed){}

    @Override
    public void setTotalDistance(double dist){}

    @Override
    public void setTimeElapsed(double timeElapsed){}

    @Override
    public void setAverageSpeed(double averageSpeed){}
    
    @Override
    public void setInstructions(String ins){}
    
}
