package Models;
import Models.Customer.Customer;
import Models.Items.Book;

import java.util.List;

public class Library {
    private static Library instance;
    private String name;
    private String address;
    private List<Book> books;
    private List<Customer> customers;
    private List<Cart> carts;

    private Library() {
    }

    public Library(String name, String address, List<Customer> customers, List<Book> books, List<Cart> carts) {
        this.name = name;
        this.address = address;
        this.customers = customers;
        this.books = books;
        this.carts = carts;
    }

    public static Library getInstance() {
        return instance;
    }

    public static void setInstance(Library instance) {
        Library.instance = instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}

