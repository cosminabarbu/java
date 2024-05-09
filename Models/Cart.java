package Models;
import Models.Customer.Customer;
import Models.Items.Book;
import Models.Items.Vinyl;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Customer customer;
    private List<Book> books;
    private List<Vinyl> vinyls;

    public Cart() {
    }

    public Cart(Customer customer) {
        this.customer = customer;
        this.books = new ArrayList<>();
        this.vinyls = new ArrayList<>();
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

//    @Override
//    public double getTotal() {
//        double total = 0;
//        for (Book book : books) {
//            total += book.getPrice();
//        }
//        double discount = customer.getDiscount();
//        total *= discount;
//        return total;
//    }
}
