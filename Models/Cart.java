package Models;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Customer customer;
    private List<Book> books;

    public Cart() {
    }

    public Cart(Customer customer) {
        this.customer = customer;
        this.books = new ArrayList<>();
    }

    // Getters and setters for properties

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    // Method to add a book to the list of books in the cart
    public void addBook(Book book) {
        books.add(book);
    }

    // Method to remove a book from the list of books in the cart
    public void removeBook(Book book) {
        books.remove(book);
    }
}
