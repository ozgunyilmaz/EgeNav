package tr.edu.ege.cs.egenav.ui;

/**
 * @author Özgün Yılmaz
 * Created on 22.May.2014, 15:13:07
 */
public abstract class NavigationInformationAdapter implements NavigationInformation{


    @Override
    public abstract void setLatitude(double lat);
    
    @Override
    public abstract void setLongitude(double lon);

    @Override
    public abstract void setHeading(double heading);

    @Override
    public abstract void setSpeed(double speed);

    @Override
    public abstract void setTotalDistance(double dist);

    @Override
    public abstract void setTimeElapsed(double timeElapsed);

    @Override
    public abstract void setAverageSpeed(double averageSpeed);
    
    @Override
    public abstract void setInstructions(String ins);
    
}
