package Management.Customer;
import Models.Customer.Student;
import java.util.HashMap;
import java.util.Map;

public class StudentManagement {
    Map<Integer, Student> studentsList = new HashMap<>();

    public void add(Student student) {
        studentsList.put(student.getCustomerId(), student);
    }

    public void delete(int studentId) {
        if (studentsList.containsKey(studentId)) {
            studentsList.remove(studentId);
        } else {
            System.out.println("Student with ID " + studentId + " does not exist.");
        }
    }

    public Student get(int studentId) {
        return studentsList.get(studentId);
    }

    public void updateAddress(int studentId, String newAddress) {
        Student student = studentsList.get(studentId);
        if (student != null) {
            student.setAddress(newAddress);
        } else {
            System.out.println("Student with ID " + studentId + " does not exist.");
        }
    }
}
