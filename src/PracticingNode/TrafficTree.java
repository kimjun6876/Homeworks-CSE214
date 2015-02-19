import java.io.*;

/**
 * Created by ADMIN on 2014-10-30.
 */
public class TrafficTree implements Serializable {
    private TrafficNode root;

    private TrafficNode cursor;
    private TrafficNode searchCursor;
    private int countNode = 0;
    private boolean done = false;

    /**
     * A method that makes a new tree
     */
    public TrafficTree()
    {
        root = null;
    }

    /**
     * A method that adds new road information
     * @param road a parameter that implies the road
     * @param speed a parameter that implies the speed
     * @param direction a parameter that implies the direction
     */
    public void addSpeed(String road, double speed, int direction)
    {
        TrafficNode newNode;
        done = false;
        this.searchCursor = this.root;
        //searchNode(road);
        if(root == null)
        {
            newNode = new TrafficNode(road, speed, direction);
            root = newNode;
        }
        else
        {
           cursor = root;
            while(!done) {
                if(road.compareTo(cursor.getRoad()) < 0){
                    if(cursor.getLeft() == null){
                        newNode = new TrafficNode(road, speed, direction);
                        cursor.setLeft(newNode);
                        done = true;
                    }
                    else cursor = cursor.getLeft();
                }
                else if(road.compareTo(cursor.getRoad()) > 0)
                {
                    if(cursor.getRight() == null){
                        newNode = new TrafficNode(road, speed, direction);
                        cursor.setRight(newNode);
                        done = true;
                    }
                    else cursor = cursor.getRight();
                }
                else if(road.compareTo(cursor.getRoad()) == 0)
                {
                    cursor.sameRoad(speed, direction);
                    done = true;
                }
            }
        }
    }

    /**
     * A method that gets the file
     * @param filename A parameter that implies the file name
     * @throws IOException Throw an exception when the program crucshes because of the wrong file name
     */
    public void addSpeeds(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        InputStreamReader inStream = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(inStream);
        int direc;
        String oneLine = reader.readLine();
        while(oneLine != null) {
            String[] info = oneLine.split(",");
            if (info[2].equalsIgnoreCase("N"))
                direc = 1;
            else
                direc = 0;
            double speed = Double.parseDouble(info[1]);
            addSpeed(info[0], speed, direc);
            oneLine = reader.readLine();
        }
    }

    /**
     * A method that getting root of the tree
     * @return the rood
     */
    public TrafficNode getRoot()
    {
        return root;
    }

    /**
     * A method that finds a depth of the tree
     * @param cursor the parameter that let us know the current position.
     * @return the depth of the tree.
     */
    public int depth(TrafficNode cursor)
    {
        int depthLeft = 0;
        int depthRight = 0;
        if(cursor == null)
            return 0;
        else
        {
            if(cursor.getLeft() != null)
                depthLeft += depth(cursor.getLeft()) +1;
            if(cursor.getRight() != null)
                depthRight += depth(cursor.getRight()) +1;
            if(depthLeft >= depthRight)
                return depthLeft;
            else
                return depthRight;
        }
    }

    /**
     * A method that find a speed.
     * @param road A parameter that implies the road
     * @param direction A parameter that implies the direction
     * @return the double that implies it is null or not
     */
    public double findSpeed(String road, int direction)
    {
        if(searchRoad(root,road,direction) == null)
        {
            return -1;
        }
        else if(direction == 1 && searchRoad(root,road,direction).getAverageNorthSpeed() != -1)
        {
            return searchRoad(root,road,direction).getAverageNorthSpeed();
        }
        else if(direction == 0 && searchRoad(root,road,direction).getAverageSouthSpeed() != -1)
        {
            return searchRoad(root,road,direction).getAverageSouthSpeed();
        }
        else if(searchRoad(root,road,direction).getAverageSouthSpeed() == -1)
        {
            return -2;
        }
        else if(searchRoad(root,road,direction).getAverageNorthSpeed() == -1)
        {
            return -2;
        }
        else
            return 0;
    }

    /**
     * A method that finds a specific road.
     * @param cursor current positon
     * @param road a parameter that implies the road
     * @param direction a parameter that implies the direction
     * @return the traffic node
     */
    public TrafficNode searchRoad( TrafficNode cursor,String road, int direction)
    {
        if(cursor == null)
            return null;
        //TrafficNode leftCursor = searchRoad(cursor.getLeft(),road,direction);
        if(cursor.getRoad().equals(road))
        {
            return cursor;
        } else if (road.compareTo(cursor.getRoad()) < 0) {
            return searchRoad(cursor.getLeft(), road, direction);
        } else {
            return searchRoad(cursor.getRight(), road, direction);
        }
       // searchRoad(cursor.getRight(),road,direction);
    }

    /**
     * A method that prints the tree inorder
     * @param cursor current positon
     */
    public void printInorder( TrafficNode cursor)
    {
        if(cursor == null)
            return;
        printInorder(cursor.getLeft());
        if(cursor.getAverageSouthSpeed() == -1 && cursor.getAverageNorthSpeed() == -1)
            System.out.println(cursor.getRoad() +": Avg speed North = (none). Avg speed South = (none).");
        else if(cursor.getAverageSouthSpeed() == -1)
            System.out.println(cursor.getRoad() +": Avg speed North = " + cursor.getAverageNorthSpeed() + ". Avg speed South = (none).");
        else if(cursor.getAverageNorthSpeed() == -1)
            System.out.println(cursor.getRoad() +": Avg speed North = (none). Avg speed South = " + cursor.getAverageSouthSpeed());
        else
            System.out.println(cursor.getRoad() +": Avg speed North = " + cursor.getAverageNorthSpeed() + ". Avg speed South = " + cursor.getAverageSouthSpeed());

        printInorder(cursor.getRight());
    }

    /**
     * A method that prints the tree preorder
     * @param cursor current positon
     */
    public void printPreorder( TrafficNode cursor)
    {
        if(cursor == null)
            return;
        if(cursor.getAverageSouthSpeed() == -1 && cursor.getAverageNorthSpeed() == -1)
            System.out.println(cursor.getRoad() +": Avg speed North = (none). Avg speed South = (none).");
        else if(cursor.getAverageSouthSpeed() == -1)
            System.out.println(cursor.getRoad() +": Avg speed North = " + cursor.getAverageNorthSpeed() + ". Avg speed South = (none).");
        else if(cursor.getAverageNorthSpeed() == -1)
            System.out.println(cursor.getRoad() +": Avg speed North = (none). Avg speed South = " + cursor.getAverageSouthSpeed());
        else
            System.out.println(cursor.getRoad() +": Avg speed North = " + cursor.getAverageNorthSpeed() + ". Avg speed South = " + cursor.getAverageSouthSpeed());

        printPreorder(cursor.getLeft());
        printPreorder(cursor.getRight());
    }

    /**
     * A method that saves the information into the file
     * @param filename filename
     */
    public void serialize(String filename)
    {
       try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream fout = new ObjectOutputStream(file);
            fout.writeObject(this); //Writes myTree to “filename.obj”
            fout.close();
        } catch (IOException e){
            // handle IO exceptions here
        }
    }

    /**
     * A method that load the information
     * @param filename filename
     * @return the tree
     */
    public static TrafficTree deserialize(String filename)
    {
        TrafficTree myTree = new TrafficTree();
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream fin  = new ObjectInputStream(file);
            myTree = (TrafficTree) fin.readObject(); //readObject() returns Object, so must typecast to Tree
            fin.close();
        } catch(IOException e){
            System.out.println("\"" +filename +"\" not found. Using new tree.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myTree;
    }
}
