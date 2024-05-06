package Models;
import java.util.Date;

class Student extends Customer {
    private int studentId;
    private String university;

    public Student(String firstName, String lastName, Date birthday, int studentId, String university) {
        super(firstName, lastName, birthday);
        this.studentId = studentId;
        this.university = university;
    }

    @Override
    public double getDiscount() {
        return 0.7;
    }
}
