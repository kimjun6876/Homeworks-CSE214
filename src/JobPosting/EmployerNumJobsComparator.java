import java.util.Comparator;
//Junyoung.Kim 109309965 R06 junyoung.kim@stonybrook.edu
/**
 * Created by ADMIN on 2014-12-01.
 */
public class EmployerNumJobsComparator implements Comparator<Employer> {
    /**
     * A method that compares
     * @param o1 employer1
     * @param o2 employer2
     * @return one has upper one
     */
    public int compare(Employer o1, Employer o2)
    {
        if(o1.size() == o2.size())
            return 0;
        else
            return o1.size()-o2.size();
    }
}