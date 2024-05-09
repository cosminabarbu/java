package Models.Customer;
import java.util.Date;

public abstract class Customer {
    private String firstName;
    private String lastName;
    private Date birthday;
    private String address;
    private int customerId;
    private static int nextId = 1;

    public Customer() {
        this.customerId = nextId;
        nextId++;
    }

    public Customer(String firstName, String lastName, Date birthday, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.customerId = nextId++;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public void printDetails() {
        System.out.println("Customer Details:");
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Birthday: " + birthday);
        System.out.println("Address: " + address);
    }

    public abstract double getDiscount();

}
