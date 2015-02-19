import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ADMIN on 2014-10-30.
 */
public class TrafficNode implements Serializable {
    private String road;
    private TrafficNode left;
    private TrafficNode right;
    private ArrayList<Double> South = new ArrayList<Double>();
    private ArrayList<Double> North = new ArrayList<Double>();

    /**
     * A method that makes a new traffic node
     * @param roadTemp Temp road
     * @param speed speed of the road
     * @param way the road
     */
    public TrafficNode(String roadTemp, double speed, int way) {
        this.road = roadTemp;
        if(way == 0)
        {
            South.add(speed);
        }
        else if(way == 1)
        {
            North.add(speed);
        }
    }

    /**
     * A method that puts the speed value into the same road
     * @param speed the speed
     * @param way the road
     */
    public void sameRoad(double speed, int way){
        if(way == 0)
        {
            South.add(speed);
        }
        else if(way == 1)
        {
            North.add(speed);
        }
    }

    /**
     * A method that gets the average speed of the south way road
     * @return the average speed
     */
    public double getAverageSouthSpeed()
    {
        double average = 0;
        if(South.size() == 0)

        {
            return -1;
        }
        for(int i=0; i< South.size(); i++)
        {
            average += South.get(i);
        }
        return average/(South.size());
    }

    /**
     * A method that gets the average speed of the north way road
     * @return the average speed of the north
     */
    public double getAverageNorthSpeed()
    {
        double average = 0;
        if(North.size() == 0)
        {
            return -1;
        }
        for(int i=0; i< North.size(); i++)
        {
            average += North.get(i);
        }

        return average/(North.size());
    }
    public String getRoad()
    {
        return road;
    }
    public void setLeft(TrafficNode newLeft)
    {
        this.left = newLeft;
    }
    public void setRight(TrafficNode newRight)
    {
        this.right = newRight;
    }
    public TrafficNode getLeft()
    {
        return left;
    }
    public TrafficNode getRight()
    {
        return right;
    }
}
