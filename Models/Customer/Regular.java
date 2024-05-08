package Models.Customer;
import java.util.Date;

class Regular extends Customer {

    public Regular() {
    }

    public Regular(String firstName, String lastName, Date birthday) {
        super(firstName, lastName, birthday);
    }

    @Override
    public double getDiscount() {
        return 1.0;
    }
}
