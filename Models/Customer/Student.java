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
    public void printDetails() {
        super.printDetails();
        System.out.println("Student ID: " + studentId);
        System.out.println("University: " + university);
    }

    @Override
    public double getDiscount() {
        return 0.7;
    }
}
