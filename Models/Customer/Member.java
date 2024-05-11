package Models.Customer;

import java.util.Date;

public class Member extends Customer {
    private Date subscriptionDate;

    public Member() {
    }

    public Member(String firstName, String lastName, Date birthday, String address,  int memberId, Date subscriptionDate) {
        super(firstName, lastName, birthday, address);
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", customerId=" + customerId +
                "subscriptionDate=" + subscriptionDate +
                '}';
    }

    @Override
    public double getDiscount() {
        Date currentDate = new Date();
        long diffInMilliseconds = currentDate.getTime() - subscriptionDate.getTime();
        long diffInMonths = diffInMilliseconds / (1000L * 60 * 60 * 24 * 30);

        if (diffInMonths < 1) {
            return 0.9;
        } else if (diffInMonths < 6) {
            return 0.8;
        } else {
            return 0.7;
        }
    }
}