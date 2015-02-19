import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by ADMIN on 2014-10-31.
 */
public class TrafficDriver {
    public static void main(String[] args) throws IOException {
        int key = 0;
        String selection;
        String road;
        double speed;
        String direction;
        int directionNumber = 0;
        TrafficTree newTree = TrafficTree.deserialize("tree.obj");
        Scanner input = new Scanner(System.in);

        System.out.print("A) Add a speed \n" +
                "F) load a text file \n" +
                "R) find a road \n" +
                "I) print all roads (inorder) \n" +
                "P) print all roads (preorder) \n" +
                "D) depth \n" +
                "Q) Quit and save to \"tree.obj\" \n\n");
        try {while(key == 0) {

                System.out.print("Enter a selection: ");
                selection = input.nextLine();
                if (selection.equals("A") || selection.equals("a")) {
                    System.out.print("\nEnter the road name: ");
                    road = input.nextLine();
                    System.out.print("Enter the speed: ");
                    speed = input.nextDouble();
                    System.out.print("Enter the direction: ");
                    direction = input.next();
                    if (direction.equals("N") || direction.equals("n"))
                        directionNumber = 1;
                    else if (direction.equals("S") || direction.equals("s"))
                        directionNumber = 0;
                    input.nextLine();
               /* else
                {
                    throw new IllegalArgumentException();
                }*/
                    newTree.addSpeed(road, speed, directionNumber);
                    System.out.println("Added speed " + speed + ", direction " + direction + " to " + road);
                }
                if (selection.equals("I") || selection.equals("i")) {
                    newTree.printInorder(newTree.getRoot());
                }
                if (selection.equals("P") || selection.equals("p")) {
                    newTree.printPreorder(newTree.getRoot());
                }
                if (selection.equals("D") || selection.equals("d")) {
                    System.out.println("The maximum depth of the tree is " + newTree.depth(newTree.getRoot()));
                }
                if (selection.equals("R") || selection.equals("r")) {
                    System.out.print("Enter the road name: ");
                    road = input.nextLine();
                    System.out.print("Enter the direction: ");
                    direction = input.nextLine();
                    if (direction.equals("N") || direction.equals("n"))
                        directionNumber = 1;
                    else if (direction.equals("S") || direction.equals("s"))
                        directionNumber = 0;
                    double e = newTree.findSpeed(road, directionNumber);
                    if (e == -2 && (direction.equals("S") || direction.equals("s"))) {
                        System.out.println("There are no cars traveling southbound on " + road);
                    } else if (e == -2 && (direction.equals("N") || direction.equals("n"))) {
                        System.out.println("There are no cars traveling northbound on " + road);
                    } else if (e == -1) {
                        System.out.println("There is no direction that you want to find");
                    } else {
                        String dir = "";
                        if (directionNumber == 1)
                            dir = "northbound";
                        else if (directionNumber == 0)
                            dir = "southbound";
                        System.out.println("The average speed of " + dir + " cars on " + road + " is " + e);
                    }
                }
                if (selection.equals("F") || selection.equals("f")) {
                    System.out.print("Enter the file name: ");
                    String name = input.nextLine();
                    newTree.addSpeeds(name);
                    System.out.println("Loaded data from \"" + name + "\" into the tree");
                }
                if (selection.equals("Q") || selection.equals("q")) {
                    newTree.serialize("tree.obj");
                    System.out.println("Saved tree to " + "tree.obj");
                    return;
                }
            }

        }
        catch(InputMismatchException h)
        {
            System.out.println("wrong input");
        }
    }
}
