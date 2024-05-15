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

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }
    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public void renewMembership() {
        Date currentDate = new Date();
        long oneMonthInMillis = 30L * 24 * 60 * 60 * 1000;
        long newSubscriptionTime = subscriptionDate.getTime() + oneMonthInMillis;
        subscriptionDate = new Date(newSubscriptionTime);
        System.out.println("Membership renewed. New expiration date: " + subscriptionDate);
    }

    public boolean isMembershipExpired(Member member) {
        Date currentDate = new Date();
        long diffInMilliseconds = currentDate.getTime() - member.getSubscriptionDate().getTime();
        long diffInMonths = diffInMilliseconds / (1000L * 60 * 60 * 24 * 30);

        return diffInMonths >= 1; // If membership is expired (over a month old), return true
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

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", customerId=" + customerId +
                ", subscriptionDate=" + subscriptionDate +
                '}' + "\n";
    }
}