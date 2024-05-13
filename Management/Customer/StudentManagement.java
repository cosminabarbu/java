package Management.Customer;
import Models.Customer.Member;
import Models.Customer.Student;
import java.util.HashMap;
import java.util.Map;

public class StudentManagement {
    Map<Integer, Student> studentsList = new HashMap<>();

    public Student add(Student student) {
        studentsList.put(student.getCustomerId(), student);
        return student;
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

    public Map<Integer, Student> getAll(){
        return studentsList;
    }

    public void updateAddress(Student student, String newAddress) {
        if (student != null) {
            student.setAddress(newAddress);
        } else {
            System.out.println("Given student does not exist.");
        }
    }
}
