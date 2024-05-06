package Models;
import java.util.Date;

class Regular extends Customer {
    public Regular(String firstName, String lastName, Date birthday) {
        super(firstName, lastName, birthday);
    }

    @Override
    public double getDiscount() {
        return 1.0;
    }
}