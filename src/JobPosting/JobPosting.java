//Junyoung.Kim 109309965 R06 junyoung.kim@stonybrook.edu
import java.io.Serializable;

/**
 * Created by ADMIN on 2014-11-28.
 */
public class JobPosting implements Serializable {
    private String jobTitle;
    private int salary;
    private int perWeek;
    private int vacationDays;

    /**
     * A method that makes a jobPosting
     * @param jobTitle Title
     * @param salary Salary
     * @param perWeek Perweek
     * @param vacationDays vacationDays
     */
    public JobPosting(String jobTitle, int salary, int perWeek, int vacationDays)
    {
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.perWeek = perWeek;
        this.vacationDays = vacationDays;
    }

    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }
    public void setSalary(int salary)
    {
        this.salary = salary;
    }
    public void setPerWeek(int perWeek)
    {
        this.perWeek = perWeek;
    }
    public void setVacationDays(int vacationDays)
    {
        this.vacationDays = vacationDays;
    }
    public String getJobTitle()
    {
        return jobTitle;
    }
    public int getSalary()
    {
        return salary;
    }
    public int getPerWeek()
    {
        return perWeek;
    }
    public int getVacationDays()
    {
        return vacationDays;
    }
}
