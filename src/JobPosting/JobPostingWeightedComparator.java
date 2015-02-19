import java.util.Comparator;
//Junyoung.Kim 109309965 R06 junyoung.kim@stonybrook.edu
/**
 * Created by ADMIN on 2014-12-01.
 */
public class JobPostingWeightedComparator implements Comparator<JobPosting> {
    int salaryWeight;
    int hoursWeight;
    int vacationWeight;

    /**
     * a method that makes a new Jobpostingweighted
     * @param salary salary
     * @param hours hours
     * @param vacation vacation
     */
    public JobPostingWeightedComparator(int salary, int hours, int vacation)
    {
        this.salaryWeight = salary;
        this.hoursWeight = hours;
        this.vacationWeight = vacation;
    }

    /**
     * A method that compares both of them
     * @param o1 jobposting1
     * @param o2 jobposting2
     * @return preference
     */
    public int compare(JobPosting o1, JobPosting o2)
    {
        int preference1 = o1.getSalary() * salaryWeight + o1.getPerWeek() * hoursWeight + o1.getVacationDays() * vacationWeight;
        int preference2 = o2.getSalary() * salaryWeight + o2.getPerWeek() * hoursWeight + o2.getVacationDays() * vacationWeight;

        return preference1 - preference2;
    }
}
