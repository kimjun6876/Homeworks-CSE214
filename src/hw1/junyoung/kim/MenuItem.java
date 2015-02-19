/**
 * Junyoung Kim 109309965 R06
 */
public class MenuItem {
    private String name;
    private String description;
    private Double price;

    public MenuItem(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public void setPrice(Double price)
    {
        this.price = price;
    }
}