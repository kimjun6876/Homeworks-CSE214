//Junyoung.Kim 109309965 R06 junyoung.kim@stonybrook.edu
import java.io.*;
import java.util.ArrayList;

/**
 * Created by ADMIN on 2014-11-28.
 */
public class Employer extends ArrayList<JobPosting> implements Serializable{

    private String name;

    /**
     * A method that makes a new Employer Array
     */
    public Employer()
    {
    }

    /**
     * A method that sets the name
     * @param name name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * A method that gets the name
     * @return name
     */
    public String getName()
    {
        return name;
    }

}
