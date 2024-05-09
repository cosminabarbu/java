package Models;

public class LibraryItem implements Item{
    private String title;
    private double price;
    private int stock;

    public LibraryItem() {}

    public LibraryItem(String title, double price, int stock) {
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String name) {
        this.title = title;
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
        System.out.println("Details about" + title);
        System.out.println("Price: " + price);
        System.out.println("Stock: " + stock);
    }
}

