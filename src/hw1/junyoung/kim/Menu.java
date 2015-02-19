/**
 * Junyoung Kim 109309965 R06
 */
    public class Menu {
    private MenuItem[] menu;
    public final int MAX_ITEMS = 50;
    public Menu(){
        menu = new MenuItem[MAX_ITEMS];
    }
    public int size = 0;

    /**
     * A method that duplicate the array which called Menuitem.
     * @return
     * the array of duplicated menuitem array.
     */
    public Object clone()
    {
        MenuItem[] duplicate;
        duplicate = new MenuItem[MAX_ITEMS];
        for(int i = 0; i < MAX_ITEMS; i++)
        {
            duplicate[i] = new MenuItem(menu[i].getName(),menu[i].getDescription(),menu[i].getPrice());
        }
        return duplicate;
    }

    /**
     *A method that gets the form of menuitems
     * @return
     * the array of menu item
     */
    public MenuItem[] getMenuItems()
    {
        return menu;
    }

    /**
     * A method that compares MenuItem array to an object
     * @param obj
     * The object that is going to be compared with the MenuItem array
     * @return
     * If obj equals with MenuItem array then return true, if not, false.
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof Menu)
        {
            MenuItem[] checking = ((Menu)obj).getMenuItems();
            if(((Menu)obj).size() != size())
                return false;
            int position = 0;
            for(int i = 0; i < size(); i++) {
                if (!(menu[i].getName().equals(checking[i].getName()) && menu[i].getDescription().equals(checking[i].getDescription())
                        && menu[i].getPrice() == checking[i].getPrice()))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * A method that can get the size of a MenuItem array
     * @return
     * The size of a MenuItem array
     */
    public int size()
    {
        return size;
    }

    /**
     * A method that is going to add new Item into the MenuItem array
     * @param item
     * item being added
     * @param position
     * position being added
     * @throws IllegalArgumentException
     * Exception if the inputted position is invalid.
     * @throws FullListException
     * Exception if the list is fulled
     */
    public void addItem(MenuItem item, int position) throws IllegalArgumentException, FullListException//precondition, catch errors
    {
        if(size() == MAX_ITEMS)
            throw new FullListException();
        if(position > size()+1)
            throw new IllegalArgumentException();
        if(menu[position-1] == null) {
            menu[position-1] = item;
            size++;
            System.out.println("Added" +size);
        }
        else{
            int k = position;
            while(menu[k] != null)
            {
                k++;
            }
            for(int i = k ; i > position-1; i--)
            {
                menu[i] = menu[i-1];
            }
            menu[position-1] = item;
            size++;
            System.out.println("Added" +size);
        }
    }

    /**
     * A method that is going to remove the items that exist in the MenuItem array
     * @param position
     * position being removed
     * @throws IllegalArgumentException
     * Exception if the inputted position is invalid
     */
    public void removeItem(int position) throws IllegalArgumentException {
            if((position > size()+1) || (position < 1))
                throw new IllegalArgumentException();
            int k = position-1;
        while(menu[k] != null)
        {
            k ++;
        }
        for(int i = position-1; i < k-1 ; i++)
        {
            menu[i] = menu[i+1];
        }
        size--;
    }

    /**
     * A method that gets the item in the MenuItem array
     * @param position
     * position being checked
     * @return
     * the item in the inputted position
     * @throws IllegalArgumentException
     * Exception if the position is bigger than the size
     */
    public MenuItem getItem(int position) throws IllegalArgumentException {
        if(position > size())
            throw new IllegalArgumentException();
        return menu[position-1];
    }

    /**
     * A method that is seeking the item by the name of the item
     * @param name
     * name being checked
     * @return
     * the item in the MenuItem array
     * @throws IllegalArgumentException
     * Exceptions if the name is not valid
     */
    public MenuItem getItemByName(String name) throws IllegalArgumentException {
        for(int i = 0; i< size(); i ++)
        {
            if(menu[i].getName().equals(name))
            {
                return menu[i];
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * A method that prints all items in the MenuItem array
     */
    public void printAllItems()
    {
        System.out.print(toString());
    }

    /**
     * A method that convert string into the specific format.
     * @return
     * the converted string in the format
     */
    public String toString()
    {
        String sth = "";
        sth += String.format("%-10s%-15s%-35s%-6s", "#", "Name", "Description", "Price");
        sth += "\n";
        System.out.print(sth);
        sth = "";
        System.out.print("---------------------------------------------------------------------------------\n");
        for(int i = 0; i < size(); i++) {
            sth += String.format("%-10d%-15s%-35s%-6.2f", i+1, menu[i].getName(), menu[i].getDescription(), menu[i].getPrice());
            sth += "\n";
        }
        return sth;
    }
}
