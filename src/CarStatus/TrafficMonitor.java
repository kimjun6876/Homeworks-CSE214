package CarStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Junyoung Kim 109309965 R06 junyoung.kim@stonybrook.edu
 * Created by ADMIN on 2014-11-17.
 */
public class TrafficMonitor implements Serializable {
    private Hashtable<Integer, CarStatus> cars = new Hashtable<Integer, CarStatus>();
    private Hashtable<String, List<Integer>> roads = new Hashtable<String, List<Integer>>();

    /**
     * A method that adds a new carstatus data into a trafficmonitor
     * @param id id number
     * @param road road name
     * @param position position
     * @param time time
     */
    public void addData(int id, String road, double position, double time){
        if(cars.get(id) != null)
        {
            String tempRoad = cars.get(id).getRoad();
            if(tempRoad.equals(road) == false)
            {
                cars.get(id).setRoad(road);
                roads.get(tempRoad).remove(new Integer(id));
                if(roads.containsKey(road))
                {
                    roads.get(road).add(id);
                }
                else
                {
                    List<Integer> tempList = new ArrayList<Integer>();
                    tempList.add(id);
                    roads.put(road, tempList);
                }
                cars.get(id).setSpeed(-1);
            }
            else
            {

                double tempPo = cars.get(id).getPosition();
                double tempTi = cars.get(id).getTime();
                double averageSpeed = ((double)(position-tempPo))/(time-tempTi);
                cars.get(id).setSpeed(averageSpeed);
                /*if(roads.contains(road))
                {
                    roads.get(road).add(id);
                }
                else
                {
                    List<Integer> tempList = new ArrayList<Integer>();
                    tempList.add(id);
                    roads.put(road, tempList);
                }*/
            }

            cars.get(id).setPosition(position);
            cars.get(id).setTime(time);
        }
        else {
            CarStatus newCar = new CarStatus();
            newCar.setId(id);
            newCar.setRoad(road);
            newCar.setPosition(position);
            newCar.setTime(time);
            cars.put(id, newCar);
            if(roads.containsKey(road))
            {
                roads.get(road).add(id);
            }
            else
            {
                List<Integer> tempList = new ArrayList<Integer>();
                tempList.add(id);
                roads.put(road, tempList);
            }
        }
    }

    /**
     * A method that removes the road of the id number
     * @param id id number
     */
    public void remove(int id){
        roads.get(cars.get(id).getRoad()).remove(new Integer(id));
        cars.remove(id);

    }

    /**
     * A method that gets road name
     * @param id id number
     * @return null if it doesn't have road name
     */
    public String getRoad(int id){
        if(cars.containsKey(id) == false)
        {
            return null;
        }
        else
        {
            return cars.get(id).getRoad();
        }
    }

    /**
     * A method that gets speed
     * @param id id number
     * @return the speed
     */
    public double getSpeed(int id){
        if(cars.containsKey(id) == true)
        {
            if(cars.get(id).getSpeed() == -1)//////////////////////////////////
            {
                return -2;
            }
            else
                return cars.get(id).getSpeed();
        }
        else
        {
            return -1;
        }
    }

    /**
     * A method that gets known speeds
     * @param road road name
     * @return the array of the speeds
     */
    public List<Double> getKnownSpeeds(String road){
        ArrayList<Double> tempArr = new ArrayList<Double>();
        List<Integer> carsId = roads.get(road);
        for(int i = 0; i< carsId.size(); i++)
        {
            if(cars.get(carsId.get(i)).getSpeed() != -1) {
                System.out.println("Car #" + carsId.get(i) +": " +(cars.get(carsId.get(i)).getSpeed()));
                tempArr.add(cars.get(carsId.get(i)).getSpeed());
            }
        }
        return tempArr;
    }

    /**
     * A method that gets average speed
     * @param road road name
     * @return average of speeds
     */
    public double getAverageSpeed(String road){
        ArrayList<Double> tempArr = new ArrayList<Double>();
        List<Integer> carsId = roads.get(road);
        for(int i = 0; i< carsId.size(); i++)
        {
            if(cars.get(carsId.get(i)).getSpeed() != -1) {
                tempArr.add(cars.get(carsId.get(i)).getSpeed());
            }
        }
        double avg = 0;
        for(int i =0; i< tempArr.size(); i++)
        {
            avg += tempArr.get(i);
        }
        return avg/tempArr.size();
    }

    /**
     * A method that saves the information into the file
     * @param filename filename
     */
    public void serialize(String filename)
    {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream fout = new ObjectOutputStream(file);
            fout.writeObject(this); //Writes myTree to “filename.obj”
            fout.close();
        } catch (IOException e){
            // handle IO exceptions here
        }
    }

    /**
     * A method that load the information
     * @param filename filename
     * @return the trafficmonitor
     */
    public static TrafficMonitor deserialize(String filename)
    {
        TrafficMonitor myTree = new TrafficMonitor();
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream fin  = new ObjectInputStream(file);
            myTree = (TrafficMonitor) fin.readObject(); //readObject() returns Object, so must typecast to Tree
            fin.close();
        } catch(IOException e){
            System.out.println("\"" +filename +"\" not found. Using new trafficMonitor.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myTree;
    }
}
