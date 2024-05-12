package Models;
import Models.Customer.Customer;
import Models.Customer.Member;
import Models.Items.Book;
import Models.Items.Vinyl;

import java.util.ArrayList;
import java.util.Date;
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
        this.customer = customer;
        this.books = new ArrayList<>(books);
        this.vinyls = new ArrayList<>(vinyls);
        updateBookStock();
        updateVinylStock();
    }

    public Cart(Member customer, List<Book> books, List<Vinyl> vinyls) {
        this.customer = customer;
        this.books = new ArrayList<>(books);
        this.vinyls = new ArrayList<>(vinyls);

        if (customer.isMembershipExpired(customer)) {
            System.out.println("Cannot create cart. Membership is expired.");
        } else {
            updateBookStock();
            updateVinylStock();
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

    public void addBook(Book book) {
        if (book.getStock() > 0) {
            books.add(book);
            book.setStock(book.getStock() - 1);
            System.out.println(book.getTitle() + " added to cart. New stock: " + book.getStock());
        } else {
            System.out.println("Book " + book.getTitle() + " is out of stock.");
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

    public void addVinyl(Vinyl vinyl) {
        if (vinyl.getStock() > 0) {
            vinyls.add(vinyl);
            vinyl.setStock(vinyl.getStock() - 1);
            System.out.println(vinyl.getTitle() + " added to cart. New stock: " + vinyl.getStock());
        } else {
            System.out.println("Vinyl " + vinyl.getTitle() + " is out of stock.");
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
}
