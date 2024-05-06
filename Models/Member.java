package Models;
import java.util.Date;

class Member extends Customer {
    private int memberId;
    private Date subscriptionDate;

    public Member(String firstName, String lastName, Date birthday, int memberId, Date subscriptionDate) {
        super(firstName, lastName, birthday);
        this.memberId = memberId;
        this.subscriptionDate = subscriptionDate;
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