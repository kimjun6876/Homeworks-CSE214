package CarStatus;

import CarStatus.TrafficMonitor;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Junyoung Kim 109309965 R06 junyoung.kim@stonybrook.edu
 * Created by ADMIN on 2014-11-17.
 */
public class TrafficDriver {
    public static void main(String[] args)
    {
        int key = 0;
        int id;
        double position;
        double time;
        TrafficMonitor traffic = TrafficMonitor.deserialize("traffic.obj");
        String roadName;
        String selection;
        System.out.print("A) Add data \n" +
                "R) Remove car \n" +
                "D) get road \n" +
                "S) get speed \n" +
                "T) get all speeds \n" +
                "U) get average speed \n" +
                "Q) Quit and save to \"traffic.obj\" \n\n");
        Scanner input = new Scanner(System.in);
        try{while(key == 0)
        {
            System.out.print("Enter a selection: ");
            selection = input.nextLine();
            if(selection.equals("A") || selection.equals("a"))
            {
                System.out.print("\nEnter the car ID number: ");
                id = input.nextInt();
                input.nextLine();
                System.out.print("Enter the road name: ");
                roadName = input.nextLine();
                System.out.print("Enter the position: ");
                position = input.nextDouble();
                input.nextLine();
                System.out.print("Enter the time: ");
                time = input.nextDouble();
                input.nextLine();

                traffic.addData(id, roadName, position, time);
                if(traffic.getSpeed(id) < 0 ) {
                    System.out.println("Added car #" + id + " on " + roadName + ", position = " + position + ", time = " + time);
                }

                else if(traffic.getSpeed(id)>=0 ) {
                    System.out.println("Added car #" + id + " on " + roadName + ", position = " + position + ", time = " + time +", speed = " +String.format("%3.2f",traffic.getSpeed(id)));
                }
            }
            else if(selection.equals("R") || selection.equals("r"))
            {
                System.out.print("Enter the car number: ");
                id = input.nextInt();
                input.nextLine();
                traffic.remove(id);
                System.out.println("Removed Car #"+ id);
            }
            else if(selection.equals("D") || selection.equals("d"))
            {
                System.out.print("Enter the car ID number: ");
                id = input.nextInt();
                input.nextLine();
                System.out.println("Car #" + id + " is on " + traffic.getRoad(id));
            }
            else if(selection.equals("S") || selection.equals("s"))
            {
                System.out.print("Enter the car ID number: ");
                id = input.nextInt();
                input.nextLine();
                if(traffic.getSpeed(id) < 0)
                {
                    System.out.println("The speed of the car #"+id+" is unknown");
                }
                else {
                    System.out.println("The speed of the car #" + id + " is " + String.format("%3.2f",traffic.getSpeed(id)));
                }
            }
            else if(selection.equals("T") || selection.equals("t"))
            {
                System.out.print("Enter the road: ");
                roadName = input.nextLine();
                traffic.getKnownSpeeds(roadName);
                /*List<Double>temp = traffic.getKnownSpeeds(roadName);
                for(int i = 0; i < temp.size() ; i++)
                {
                    System.out.println("Car #" + temp.get(i) +": " +traffic.getSpeed(temp.get(i).intValue()));
                }*/
            }
            else if(selection.equals("U") || selection.equals("u"))
            {
                System.out.print("Enter the road: ");
                roadName = input.nextLine();
                System.out.println("Average speed of cars on Main Street is "+String.format("%3.2f",traffic.getAverageSpeed(roadName)));
            }
            else if(selection.equals("Q") || selection.equals("q"))
            {
                traffic.serialize("traffic.obj");
                System.out.println("Saved TrafficMonitor to traffic.obj");
                return;
            }

        }
    }catch(IllegalArgumentException g)
        {
            System.out.println("wrong number");
        }
        catch(InputMismatchException h)
        {
            System.out.println("wrong input");
        }
        catch(NullPointerException k)
        {
            System.out.println("wrong input");
        }
}}
