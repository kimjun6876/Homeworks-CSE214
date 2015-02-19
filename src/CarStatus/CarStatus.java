package CarStatus;

import java.io.Serializable;

/**
 * Junyoung Kim 109309965 R06 junyoung.kim@stonybrook.edu
 * Created by ADMIN on 2014-11-17.
 */
public class CarStatus implements Serializable {
    private int id;
    private String road;
    private double position;
    private double time;
    private double speed = -2;

    /**
     * A method that sets id
     * @param id id number
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * A method that sets road
     * @param road road name
     */
    public void setRoad(String road)
    {
        this.road = road;
    }

    /**
     * A method that sets position
     * @param position position number
     */
    public void setPosition(double position)
    {
        this.position = position;
    }

    /**
     * A method that sets time
     * @param time time
     */
    public void setTime(double time)
    {
        this.time = time;
    }

    /**
     * A method that sets speed
     * @param speed speed
     */
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    /**
     * A method that gets road
     * @return road name
     */
    public String getRoad()
    {
        return this.road;
    }

    /**
     * A method that gets position
     * @return position
     */
    public double getPosition()
    {
        return this.position;
    }

    /**
     * A method that gets time
     * @return time
     */
    public double getTime()
    {
        return this.time;
    }

    /**
     * A method that gets speed
     * @return speed
     */
    public double getSpeed()
    {
        return this.speed;
    }
}
