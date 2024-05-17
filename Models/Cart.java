package Models;
import Models.Customer.Customer;
import Models.Customer.Member;
import Models.Items.Book;
import Models.Items.Vinyl;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Customer customer;
    private List<Book> books;
    private List<Vinyl> vinyls;

    public Cart() {
        this.books = new ArrayList<>();
        this.vinyls = new ArrayList<>();
    }

    public Cart(Customer customer, List<Book> books, List<Vinyl> vinyls) {
        this();
        this.customer = customer;
        addBooksToCart(books);
        addVinylsToCart(vinyls);
    }

    public Cart(Member customer, List<Book> books, List<Vinyl> vinyls) {
        this();
        this.customer = customer;

        if (customer.isMembershipExpired(customer)) {
            System.out.println("Cannot create cart. Membership is expired.");
        } else {
            addBooksToCart(books);
            addVinylsToCart(vinyls);
        }
    }

    private void addBooksToCart(List<Book> books) {
        for (Book book : books) {
            if (book.isInStock()) {
                this.books.add(book);
                book.setStock(book.getStock() - 1);
            } else {
                System.out.println("Book " + book.getTitle() + " is out of stock and was not added to the cart.");
            }
        }
    }

    private void addVinylsToCart(List<Vinyl> vinyls) {
        for (Vinyl vinyl : vinyls) {
            if (vinyl.isInStock()) {
                this.vinyls.add(vinyl);
                vinyl.setStock(vinyl.getStock() - 1);
            } else {
                System.out.println("Vinyl " + vinyl.getTitle() + " is out of stock and was not added to the cart.");
            }
        }
    }

    private void updateBookStock() {
        for (Book book : books) {
            book.setStock(book.getStock() - 1);
        }
    }

    private void updateVinylStock() {
        for (Vinyl vinyl : vinyls) {
            vinyl.setStock(vinyl.getStock() - 1);
        }
    }
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

    public List<Vinyl> getVinyls() {
        return vinyls;
    }

    public void setVinyls(List<Vinyl> vinyls) {
        this.vinyls = vinyls;
    }

    public boolean addBook(Book book) {
        if (book.isInStock()) {
            books.add(book);
            book.setStock(book.getStock() - 1);
            System.out.println(book.getTitle() + " added to cart. New stock: " + book.getStock());
            return true;
        } else {
            System.out.println("Book " + book.getTitle() + " is out of stock.");
            return false;
        }
    }

    public boolean deleteBook(Book book) {
        if (books.remove(book)) {
            book.setStock(book.getStock() + 1);
            System.out.println(book.getTitle() + " removed from cart. New stock: " + book.getStock());
            return true;
        } else {
            System.out.println("Book not found in cart.");
            return false;
        }
    }

    public boolean addVinyl(Vinyl vinyl) {
        if (vinyl.isInStock()) {
            vinyls.add(vinyl);
            vinyl.setStock(vinyl.getStock() - 1);
            System.out.println(vinyl.getTitle() + " added to cart. New stock: " + vinyl.getStock());
            return true;
        } else {
            System.out.println("Vinyl " + vinyl.getTitle() + " is out of stock.");
            return false;
        }
    }

    public boolean deleteVinyl(Vinyl vinyl) {
        if (vinyls.remove(vinyl)) {
            vinyl.setStock(vinyl.getStock() + 1);
            System.out.println(vinyl.getTitle() + " removed from cart. New stock: " + vinyl.getStock());
            return true;
        } else {
            System.out.println("Vinyl not found in cart.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "customer=" + customer +
                ", books=" + books +
                ", vinyls=" + vinyls +
                '}' + "\n";
    }
}
