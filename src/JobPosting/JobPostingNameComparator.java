import java.util.Comparator;
//Junyoung.Kim 109309965 R06 junyoung.kim@stonybrook.edu

/**
 * Created by ADMIN on 2014-12-01.
 */
public class JobPostingNameComparator implements Comparator<JobPosting> {
    /**
     * A method that compares two
     * @param o1 jobposting1
     * @param o2 jobposting2
     * @return better one
     */
    public int compare(JobPosting o1, JobPosting o2) {
        return (o1.getJobTitle().compareTo(o2.getJobTitle()));
    }
}
