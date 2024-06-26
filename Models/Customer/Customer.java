package Models.Customer;
import java.util.Date;

public abstract class Customer {
    protected String firstName;
    protected String lastName;
    protected Date birthday;
    protected String address;
    protected int customerId;
    protected static int nextId = 1;

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

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", customerId=" + customerId +
                '}' + "\n";
    }

    public abstract double getDiscount();

}
