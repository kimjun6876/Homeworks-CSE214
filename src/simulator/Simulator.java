import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Junyoung Kim 109309965 junyoung.kim@stonybrook.edu R06
 */
public class Simulator {
     private static Project project;
    private static Project temp1;
     private static ProjectQueue projectsQueue;
    private static ProjectQueue activeProject;
     private static int numEmployees;
    public static int minProjectTime ;
    private static int maxProjectTime;
    private static int maxProjectEmployees;
    private static double arrivalProb;
    private static int[] vacationDays = new int[100];
    private static String vacations;
    private static String[] vacationString;
    private static int countVacation = 0;
    private static int duration ;

    private static int numFreeEmployees;
    private static int projectsCompleted = 0;
    private static int totalTimeWaited = 0;
    ArrayList<Project> activeProjects = new ArrayList<Project>();
// I checked the vacation day with O(1) time. I put every vacations into the Array and found with for loop.
    public Simulator() {
    }

    public static void main(String[] args) throws FullQueueException, EmptyQueueException {
        projectsQueue = new ProjectQueue();
        activeProject = new ProjectQueue();

        try {
            System.out.print("Enter the number of employees : ");
            Scanner input = new Scanner(System.in);
            numEmployees = input.nextInt();
            System.out.print("Enter the minimum project time : ");
            minProjectTime = input.nextInt();
            System.out.print("Enter the maximum project time : ");
            maxProjectTime = input.nextInt();

            if(minProjectTime > maxProjectTime)
            {
                System.out.println("The minimum project time can't be larger than the maximum project time");
                throw new InputMismatchException();
            }
            System.out.print("Enter the maximum number of employees for a project : ");
            maxProjectEmployees = input.nextInt();
            if(maxProjectEmployees > numEmployees)
            {
                System.out.println("The maximum number of employees can't be larger than the total number of Employees");
                throw new InputMismatchException();
            }
            System.out.print("Enter the arrival probability : ");
            arrivalProb = input.nextDouble();
            if(arrivalProb > 1 || arrivalProb <= 0)
            {
                System.out.println("Probability can't be larger than 1 or smaller than 0");
                throw new InputMismatchException();
            }
            input.nextLine();
            System.out.print("Enter the list of vacation days, separated by space : ");
            vacations = input.nextLine();
            vacationString = vacations.split(" ");
            for (int i = 0; i < vacationString.length; i++) {
                vacationDays[i] = Integer.parseInt(vacationString[i]);
            }
            if(vacationDays[0] != 0) {
                int first = vacationDays[0];
                int start = 1;
                while (vacationDays[start] != 0) {
                    if(first > vacationDays[start]) {
                        System.out.println("The list of the vacations is not sorted.");
                        throw new IllegalArgumentException();
                    }
                    first = vacationDays[start];
                    start ++;
                }
            }
            System.out.print("Enter the duration of the simulation : ");
            duration = input.nextInt();


            Simulator newSim = new Simulator();
            newSim.simulate(duration);


        }
        catch(InputMismatchException e )
        {
        }
        catch(IllegalArgumentException f)
        {
        }
    }

    /**
     * A method that simulates the project
     * @param duration the parameter that indicates how long the program is going to run.
     * @return the average time
     * @throws FullQueueException if the queue is full, throw exception.
     * @throws EmptyQueueException if the queue is empty, throw exception.
     */
    public double simulate(int duration) throws FullQueueException, EmptyQueueException {
        int tempEmp;
        int tempDays;
        int worksDay=0;
        int prCount=0;
        int errorArray[] = new int[1000];
        int arrayCount=0;
        boolean vacation;
        Project tempProject;
        numFreeEmployees = numEmployees;
        //System.out.println("left" + numFreeEmployees);
        for(int i = 0; i < duration ; i ++)
        {
            vacation = false;
           for(int j = 0; j < vacationDays.length; j++) {
                if (vacationDays[j] == i+1)
                {
                    vacation = true;
                }
            }
            if(vacation == true)
            {
                System.out.println("Day " + (i+1) + "\nVacation day\n");
            }
            else
            {
                if(i != 0 && prCount != 0 && projectsQueue.peek().getNumEmployees() <= numFreeEmployees)
                {
                    tempProject = projectsQueue.dequeue();
                    activeProjects.add(tempProject);
                    numFreeEmployees = numFreeEmployees - tempProject.getNumEmployees();
                    prCount--;

                }
            if(Math.random() <= arrivalProb)
            {
                tempEmp = (int)(Math.random()*(maxProjectEmployees)+1);
                do
                {
                    tempDays = (int)(Math.random()*(maxProjectTime)+1);
                }while(minProjectTime > tempDays);
                System.out.println("Day " + (i+1));
                System.out.println("A project arrives requiring " + tempEmp + " employees for " + tempDays + " days");
                project = new Project((i+1),tempEmp,tempDays);

                if(tempEmp > numFreeEmployees)               //size is bigger
                {
                    projectsQueue.enqueue(project);
                    prCount++;
                }
                else
                {
                    activeProjects.add(project);
                    numFreeEmployees = numFreeEmployees - tempEmp;
                }
                if(activeProjects == null)
                    System.out.println("Active projects: []");
                for(int j = 0; j < activeProjects.size(); j++) {
                    activeProjects.get(j).works();
                    if(activeProjects.get(j).getworkDay() == 0)/*activeProjects.get(j).getTime()) - 1 - (worksDay - activeProjects.get(j).getDayArrived()) < 1*/ {
                        System.out.println("A project was completed, It arrived in the queue " + ((i+1)-activeProjects.get(j).getDayArrived()) + " days ago.");
                        numFreeEmployees = numFreeEmployees + activeProjects.get(j).getNumEmployees();
                        projectsCompleted++;
                        totalTimeWaited += ((i+1)-activeProjects.get(j).getDayArrived());
                        System.out.println("Projects completed: " + projectsCompleted + "\nTotal time waited: " + totalTimeWaited);
                        activeProjects.remove(j);
                        j--;

                    }
                }
                for(int j = 0; j < activeProjects.size(); j++) {
                    if (j == 0)
                        System.out.print("Active projects: [");
                    System.out.print("(" + activeProjects.get(j).getDayArrived() + ", " + activeProjects.get(j).getNumEmployees() + ", " + activeProjects.get(j).getworkDay() + ")");
                    if (j < activeProjects.size() - 1)
                        System.out.print(", ");
                    if (j == activeProjects.size() - 1)
                        System.out.println("]");

                }
                System.out.println("Free Employees: " + numFreeEmployees);
                for(int j = 0; j < prCount; j++) {
                    if (j == 0 && projectsQueue != null)
                        System.out.print("Project queue: [");
                    tempProject = projectsQueue.dequeue();
                    System.out.print("(" + tempProject.getDayArrived() + ", " + tempProject.getNumEmployees() + ", " + tempProject.getTime() + ")");
                    projectsQueue.enqueue(tempProject);
                    if (j == prCount - 1)
                        System.out.println("]");
                    else if(j != prCount -1 && j == 0)
                        System.out.print(", ");
                    if (j != 0 && j < prCount-1 )
                        System.out.print(", ");
                }
                if(prCount == 0)
                    System.out.println("Project queue: []");
            }
            else
            {
                System.out.println("Day " + (i+1));
                System.out.println("No project arrives");
                if(activeProjects.size() == 0)
                    System.out.println("Active projects: []");

                for(int j = 0; j < activeProjects.size(); j++) {

                    activeProjects.get(j).works();
                    if (activeProjects.get(j).getworkDay() == 0) {
                        if(activeProjects.size() == 1)
                            System.out.println("Active projects: []");
                        System.out.println("A project was completed, It arrived in the queue " + ((i+1)-activeProjects.get(j).getDayArrived())+ " days ago.");
                        numFreeEmployees = numFreeEmployees + activeProjects.get(j).getNumEmployees();
                        projectsCompleted++;
                        totalTimeWaited += ((i+1)-activeProjects.get(j).getDayArrived());
                        System.out.println("Projects completed: " + projectsCompleted + "\nTotal time waited: " + totalTimeWaited);
                        activeProjects.remove(j);
                        j--;

                    }
                }
                for(int j = 0; j < activeProjects.size(); j++)
                {
                    if(j == 0 )
                        System.out.print("Active projects: [");
                    System.out.print("(" + activeProjects.get(j).getDayArrived() + ", " + activeProjects.get(j).getNumEmployees() + ", " +  activeProjects.get(j).getworkDay() + ")");
                    if(j < activeProjects.size()-1)
                        System.out.print(", ");
                    if(j == activeProjects.size()-1)
                        System.out.println("]");
                }
                System.out.println("Free Employees: " + numFreeEmployees);
                for(int j = 0; j < prCount; j++) {
                    if (j == 0 && projectsQueue != null)
                        System.out.print("Project queue: [");
                    tempProject = projectsQueue.dequeue();
                    System.out.print("(" + tempProject.getDayArrived() + ", " + tempProject.getNumEmployees() + ", " + tempProject.getTime() + ")");
                    projectsQueue.enqueue(tempProject);
                    if (j == prCount - 1)
                        System.out.print("]");
                    else if(j != prCount -1 && j == 0)
                        System.out.print(", ");
                    if (j != 0 && j < prCount-1 )
                        System.out.print(", ");

                }
                if(prCount == 0)
                    System.out.println("Project queue: []");
            }
            System.out.println("\n");
        }
        }
        double d = (totalTimeWaited*1.0)/projectsCompleted;
        String average = String.format("%5.2f", d);
        System.out.println(projectsCompleted+" projects completed. Average waiting time was " + average + " days per project");
        return d;
    }
}



//문제점 : 하나만 찾아내는거