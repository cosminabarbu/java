package Models.Customer;
import java.util.Date;

public class Regular extends Customer {

    public Regular() {
    }

    public Regular(String firstName, String lastName, Date birthday, String address) {
        super(firstName, lastName, birthday, address);
    }

    @Override
    public String toString() {
        return "Regular{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", customerId=" + customerId +
                '}';
    }

    @Override
    public double getDiscount() {
        return 1.0;
    }
}
