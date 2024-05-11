package Models.Customer;
import java.util.Date;

public class Student extends Customer {
    private int studentId;
    private String university;

    public Student() {
    }

    public Student(String firstName, String lastName, Date birthday, String address, int studentId, String university) {
        super(firstName, lastName, birthday, address);
        this.studentId = studentId;
        this.university = university;
    }

    @Override
    public String toString() {
        return "Student{" +
                "address='" + address + '\'' +
                ", birthday=" + birthday +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", university='" + university + '\'' +
                ", studentId=" + studentId +
                ", customerId=" + customerId +
                '}';
    }

    @Override
    public double getDiscount() {
        return 0.7;
    }
}
