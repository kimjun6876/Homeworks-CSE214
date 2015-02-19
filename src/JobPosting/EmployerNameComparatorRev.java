import java.util.Comparator;
//Junyoung.Kim 109309965 R06 junyoung.kim@stonybrook.edu

/**
 * Created by ADMIN on 2014-11-28.
 */
public class EmployerNameComparatorRev implements Comparator<Employer> {
    /**
     * A method that compares two of them which one has upper character
     * @param o1 employer1
     * @param o2 employer 2
     * @return the one has upper one
     */
    public int compare(Employer o1, Employer o2)
    {
        return (o2.getName().compareTo(o1.getName()));
    }
    /*public boolean equals(Object obj)
    {
        String temp = (String)obj;
        if(temp.compareTo())
    }*/
}
