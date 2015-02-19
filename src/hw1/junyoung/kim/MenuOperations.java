import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.lang.*;
import java.util.*;
import java.util.InputMismatchException;
/**
 * Junyoung Kim 109309965 R06
 */

public class MenuOperations {
    private static Menu menu;
    private static Menu order;
    private static int key = 0;
    public static void main(String[] args)
    {
        menu = new Menu();
        order = new Menu();
        int ordersize = 0;
        while(key == 0) {

            System.out.print("A) Add Item \n" +
                    "G) Get Item \n" +
                    "R) Remove Item \n" +
                    "P) Print All Items \n" +
                    "S) Size \n" +
                    "D) Update description \n" +
                    "C) Update price \n" +
                    "O) Add to order \n" +
                    "I) Remove from order \n" +
                    "V) View order \n" +
                    "Q) Quit \n\n" +
                    "Select an operation: ");
            Scanner input = new Scanner(System.in);
            String option = input.nextLine();
            if (option.equals("A")) {
                int checking = 0;
                System.out.print("\nEnter the name: ");
                String name = input.nextLine();
                System.out.print("\nEnter the description: ");
                String description = input.nextLine();
                System.out.print("\nEnter the price: ");
                double price = 0;
                int position = 0;
                try {
                    do {
                        price = input.nextDouble();


                        if (price > 0)
                            checking = 0;
                        else if (price <= 0) {
                            System.out.print("Invalid price\n");
                            checking = 1;
                        }
                    }
                    while (checking != 0);

                    System.out.print("\nEnter the position: ");
                    do {
                        position = input.nextInt();
                        if (position > 0 && position <= 50)
                            checking = 0;
                        else if (position <= 0 || position > 50) {
                            System.out.print("Invalid position\n");
                            checking = 1;
                        }
                    }
                    while (checking != 0);

                    menu.addItem(new MenuItem(name, description, price), position);
                    System.out.println("\nAdded \"" + name + ": " + description + "\" for $" + price + " at position " + position);

                } catch (IllegalArgumentException e) {
                    System.out.print("Not a valid a name or description");
                } catch (FullListException e) {
                    System.out.print("List is fulled");
                } catch(InputMismatchException e){
                    System.out.print("Invalid price or position\n");
                }



            } else if (option.equals("G")) {
                System.out.print("\nEnter the position: ");
                try {
                    int position = input.nextInt();
                    if(position > menu.size() )
                        throw new IllegalArgumentException();
                    String sth = "";
                    sth += String.format("%-10s%-15s%-35s%-6s", "#", "Name", "Description", "Price");
                    System.out.print(sth);
                    System.out.print("\n---------------------------------------------------------------------------------\n");
                    sth = "";
                        sth += String.format("%-10d%-15s%-35s%-6.2f\n", position, menu.getItem(position).getName(), menu.getItem(position).getDescription(), menu.getItem(position).getPrice());

                    System.out.print(sth);
                }catch (IllegalArgumentException e) {
                    System.out.print("Invalid position\n");
                }catch(InputMismatchException e){
                    System.out.print("Invalid position\n");
                }

            } else if (option.equals("R")) {
                System.out.print("\nEnter the position: ");
                int position = input.nextInt();
                try {
                    System.out.print("Removed \"" +menu.getItem(position).getName()+"\"\n");
                    menu.removeItem(position);
                } catch (IllegalArgumentException e) {
                    System.out.println("No Item in position " + position);
                }


            } else if (option.equals("P")) {
                menu.printAllItems();
            }
            else if(option.equals("S")){
                System.out.print("There are " + menu.size() + " Items in the menu\n");
            }
            else if(option.equals("D")){
                int position = 0;
                try {
                    System.out.print("\nEnter the position: ");
                    position = input.nextInt();
                    if(position > menu.size() )
                        throw new IllegalArgumentException();
                    System.out.print("\nEnter the new description: ");
                    String Desc = input.next();

                    menu.getItem(position).setDescription(Desc);
                    System.out.println("New description set.");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Invalid position");
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid position");
                }
            }
            else if(option.equals("C")){
                try{
                    System.out.print("\nEnter the name of the item: ");
                    String Name = input.nextLine();
                    System.out.print("\nEnter the new price: ");
                    double Price = input.nextDouble();
                    menu.getItemByName(Name).setPrice(Price);
                    if(Price < 0 )
                        throw new Exception();
                    System.out.println("\nChanged the price of \"" + Name + "\" to $" + Price);
                }
                catch (Exception e){
                    System.out.println("Not a valid Price or Name");
                }
            }
            else if(option.equals("O")){
                System.out.print("\nEnter position of item to add to order: ");
                int position = input.nextInt();
                try {
                    order.addItem(new MenuItem(menu.getItem(position).getName(), menu.getItem(position).getDescription(), menu.getItem(position).getPrice()),order.size()+1);
                    System.out.println("Added \"" +  order.getItem(order.size()).getName() + "\" to order");

                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid position");
                } catch (FullListException e) {
                    System.out.println("Full list");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid position");
                }
            System.out.println("size is" + order.size());
            }
            else if(option.equals("I")){
                System.out.print("\nEnter the position: ");
                double position = input.nextDouble();
                try {

                    System.out.println("Removed \"" + order.getItem((int)position).getName()+ "\" from order.");
                    order.removeItem((int)position);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid Position");
                } catch (InputMismatchException d){
                    System.out.println("Invalid Position");
                }
                System.out.println("size is" + order.size());

            }
            else if(option.equals("V"))
            {
                System.out.println("ORDER: ");
                order.printAllItems();
            }
            else if(option.equals("Q"))
            {
                return ;
            }
            else
            {
                System.out.println("No such operation");
            }
        }
    }
}
