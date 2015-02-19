//Junyoung.Kim 109309965 R06 junyoung.kim@stonybrook.edu

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by ADMIN on 2014-12-01.
 */
public class JobPostingViewer implements Serializable{
    static ArrayList<Employer> employees = JobPostingViewer.deserialize("employers.obj");
    public static ArrayList<Employer> deserialize(String filename)
    {
        ArrayList<Employer> myTree = new ArrayList<Employer>();
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream fin  = new ObjectInputStream(file);
            myTree = (ArrayList<Employer>) fin.readObject();
            fin.close();
        } catch(IOException e){
            System.out.println("\"" +filename +"\" not found. Using Employer.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myTree;
    }

    public static void serialize(String filename)
    {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream fout = new ObjectOutputStream(file);
            fout.writeObject(employees); //Writes myTree to “filename.obj”
            fout.close();
        } catch (IOException e){
            // handle IO exceptions here
        }
    }
    public static void main(String[] args) {

        int count1=0, count2=0, count3=0, count4=0, count5=0, count6=0, count7=0;

        int num = 0;
        int count = 0;
        Scanner input = new Scanner(System.in);
        int key = 0;
        String selection;
        System.out.print("A) Add Employer \n" +
                "B) Add Job \n" +
                "C) Remove Employer \n" +
                "D) Remove Job \n" +
                "E) Sort and print Employers \n" +
                "F) Sort and print Jobs \n" +
                "Q) Quit\n");
        try {
            while (key == 0) {
                System.out.print("Enter a selection : ");
                selection = input.nextLine();
                if (selection.equals("A") || selection.equals("a")) {
                    System.out.print("Enter the employer's name : ");
                    String temp = input.nextLine();
                    Employer newEmployer = new Employer();
                    newEmployer.setName(temp);
                    employees.add(newEmployer);
                    //Collections.sort(employees, new EmployerNameComparator());

                } else if (selection.equals("B") || selection.equals("b")) {
                    System.out.print("Enter the number of the employer: ");
                    int tempEmp = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter the job title: ");
                    String tempTitle = input.nextLine();
                    System.out.print("Enter the salary: ");
                    int tempSalary = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter the number of hours per week: ");
                    int tempWeek = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter the number of vacation days per year: ");
                    int tempYear = input.nextInt();
                    input.nextLine();
                    JobPosting newJob = new JobPosting(tempTitle, tempSalary, tempWeek, tempYear);
                    employees.get(tempEmp - 1).add(newJob);
                    System.out.println("Added \"" + tempTitle + "\" to " + employees.get(tempEmp - 1).getName());
                } else if (selection.equals("C") || selection.equals("c")) {
                    System.out.print("Enter the number of the employer to remove: ");
                    int tempNum = input.nextInt();
                    input.nextLine();
                    System.out.println("Removed employer \"" + employees.get(tempNum - 1).getName() + "\"");
                    employees.remove(tempNum - 1);
                } else if (selection.equals("D") || selection.equals("d")) {
                    System.out.print("Enter the number of the employer: ");
                    int tempNum = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter the number of the job to remove: ");
                    int tempRem = input.nextInt();
                    input.nextLine();
                    System.out.println("Removed job \"" + employees.get(tempNum - 1).get(tempRem - 1).getJobTitle() + "\" from employer \"" + employees.get(tempNum - 1).getName() + "\"");
                    employees.get(tempNum - 1).remove(tempRem - 1);
                } else if (selection.equals("E") || selection.equals("e")) {
                    System.out.println("A) Sort by name\nB) Sort by number of jobs");
                    String selection2 = input.nextLine();
                    if (selection2.equals("A") || selection2.equals("a")) {
                        System.out.print("count : " + count);
                            Collections.sort(employees, new EmployerNameComparator());
                        System.out.printf("%-3s%-14s%5s", "#", "Employer", "# Jobs");
                        System.out.print("\n------------------------------------\n");
                        for (int i = 0; i < employees.size(); i++) {
                            System.out.printf("%-3d%-14s%5d", i + 1, employees.get(i).getName(), employees.get(i).size());
                            System.out.println();
                        }
                    } else if (selection2.equals("B") || selection2.equals("b")) {

                        System.out.print("count : " + count);
                            Collections.sort(employees, new EmployerNumJobsComparator());
                        System.out.printf("%-3s%-14s%5s", "#", "Employer", "# Jobs");
                        System.out.print("\n------------------------------------\n");
                        for (int i = 0; i < employees.size(); i++) {
                            System.out.printf("%-3d%-14s%5d", i + 1, employees.get(i).getName(), employees.get(i).size());
                            System.out.println();
                        }
                    }
                } else if (selection.equals("F") || selection.equals("f")) {
                    int tempSalary = 0, tempHours = 0, tempDays = 0;
                    System.out.print("Enter the employer number: ");
                    int tempNum = input.nextInt();
                    input.nextLine();
                    System.out.print("A) Sort by name\n" +
                            "B) Sort by salary\n" +
                            "C) Sort by hours per week\n" +
                            "D) Sort by vacation days per year\n" +
                            "E) Sort by custom preference score\n\n" +
                            "Enter a selection: ");
                    String tempChar = input.nextLine();
                    if (tempChar.equals("A") || tempChar.equals("a")) {
                            Collections.sort((employees.get(tempNum - 1)), new JobPostingNameComparator());
                    } else if (tempChar.equals("B") || tempChar.equals("b")) {
                            Collections.sort((employees.get(tempNum - 1)), new JobPostingSalaryComparator());
                    } else if (tempChar.equals("C") || tempChar.equals("c")) {
                            Collections.sort((employees.get(tempNum - 1)), new JobPostingHoursComparator());
                    } else if (tempChar.equals("D") || tempChar.equals("d")) {
                            Collections.sort((employees.get(tempNum - 1)), new JobPostingVacationComparator());
                    } else if (tempChar.equals("E") || tempChar.equals("e")) {
                        System.out.print("Enter the salary weight: ");
                        tempSalary = input.nextInt();
                        input.nextLine();
                        System.out.print("Enter the hours/week weight: ");
                        tempHours = input.nextInt();
                        input.nextLine();
                        System.out.print("Enter the vacation days/year weight: ");
                        tempDays = input.nextInt();
                        input.nextLine();
                            Collections.sort((employees.get(tempNum - 1)), new JobPostingWeightedComparator(tempSalary, tempHours, tempDays));
                    }
                    System.out.printf("%-3s%-15s%-10s%-6s%-6s", "#", "Job Title", "Salary", "Hours", "Vacation");
                    System.out.print("\n------------------------------------\n");
                    for (int i = 0; i < employees.get(tempNum - 1).size(); i++) {
                        System.out.printf("%-3d%-15s%-10d%-6d%-6d", i + 1, employees.get(tempNum - 1).get(i).getJobTitle(), employees.get(tempNum - 1).get(i).getSalary(), employees.get(tempNum - 1).get(i).getPerWeek(), employees.get(tempNum - 1).get(i).getVacationDays());
                        System.out.println();
                    }
                } else if (selection.equals("Q") || selection.equals("q")) {
                    serialize("employers.obj");
                    System.out.println("Saving employers.obj");
                    return;
                }
            }
        } catch (InputMismatchException h) {
            System.out.println("wrong input");
        }
    }
}
