import java.util.Comparator;
//Junyoung.Kim 109309965 R06 junyoung.kim@stonybrook.edu
/**
 * Created by ADMIN on 2014-12-01.
 */
public class JobPostingHoursComparator implements Comparator<JobPosting> {
    /**
     * A method that compares two
     * @param o1 JobPosting1
     * @param o2 jobposting2
     * @return upper one
     */
    public int compare(JobPosting o1, JobPosting o2) {
        if(o1.getPerWeek() == o2.getPerWeek())
            return 0;
        else
            return o1.getPerWeek() - o2.getPerWeek();
    }
}
