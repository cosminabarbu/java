package Models.Items;

public class LibraryItem implements Item {
    protected String title;
    protected double price;
    protected int stock;
    protected double rating;
    protected int itemId;
    protected static int nextId = 1;

    public LibraryItem() {
        this.itemId = nextId;
        nextId++;
    }

    public LibraryItem(String title, double price, int stock, double rating) {
        this.title = title;
        this.price = price;
        this.stock = stock;
        this.rating = rating;
        this.itemId = nextId ++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean isInStock(){
        return stock != 0;
    }

    @Override
    public boolean hasGoodRating(){
        return rating >= 3;
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", rating=" + rating +
                ", itemId=" + itemId +
                '}';
    }
}

