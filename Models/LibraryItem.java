package Models;

public class LibraryItem implements Item{
    private String name;
    private String type;
    private double price;
    private int stock;

    public LibraryItem() {}

    public LibraryItem(String name, String type, double price, int stock) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public void setStock(int stock) {
        this.stock = stock;
    }

    public void printDetails(){
        System.out.println("Details about" + name);
        System.out.println("Type: " + type);
        System.out.println("Price: " + price);
        System.out.println("Stock: " + stock);
    }
}

