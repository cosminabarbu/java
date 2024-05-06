package Models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

abstract class Customer {
    private String firstName;
    private String lastName;
    private Date birthday;
    private int customerId;
    private static int nextId = 1;
    private List<Book> books;

    public Customer(String firstName, String lastName, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.customerId = nextId++;
        this.books = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Customer.nextId = nextId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public abstract double getDiscount();

//    public void addBook(Book book) {
//        books.add(book);
//    }
//
//    public void removeBook(Book book) {
//        books.remove(book);
//    }
}
