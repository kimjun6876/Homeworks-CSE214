import java.util.ArrayList;
import java.util.Scanner;

/**
 * Junyoung Kim 109309965 junyoung.kim@stonybrook.edu R06
 */
public class Project {
    private int dayArrived;
    private int numEmployees;
    private int time;
    private int workDay;


    public Project(int dayArrived, int numEmployees, int time) {
        this.numEmployees = numEmployees;
        this.dayArrived = dayArrived;
        this.time = time;
        this.workDay = time;
    }
    public void works(){
        workDay--;
    }
    public int getTime(){return time;}
    public int getDayArrived(){return dayArrived;}
    public int getNumEmployees(){return numEmployees;}
    public int getworkDay(){return workDay;}

}