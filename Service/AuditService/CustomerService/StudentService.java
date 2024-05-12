package Service.AuditService.CustomerService;

import Models.Customer.Member;
import Models.Customer.Student;
import Management.Customer.StudentManagement;
import Service.AuditService.WriteService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class StudentService {
    private StudentManagement studentManagement;

    public StudentService() {
        this.studentManagement = new StudentManagement();
    }

    public Student addStudent() {
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();

        System.out.println("Enter the first name of the student: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter the last name of the student: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter the birthday of the student (YYYY-MM-DD): ");
        String birthdayStr = scanner.nextLine();
        Date birthday = parseDate(birthdayStr); // Conversia string-ului la Date

        System.out.println("Enter the address of the student: ");
        String address = scanner.nextLine();
        scanner.nextLine(); // Consumăm newline-ul rămas
        System.out.println("Enter the student ID: ");
        int studentId = scanner.nextInt();
        System.out.println("Enter the student's university name : ");
        String university = scanner.nextLine();

        Student student = new Student(firstName, lastName, birthday, address, studentId, university);
        Student result = studentManagement.add(student);
        writeService.writeAction("added student");
        return result;

    }

        public Student getStudent(int studentId) {
        return studentManagement.get(studentId);
        }

        public void deleteStudent(int studentId) {
        WriteService writeService = new WriteService();

        studentManagement.delete(studentId);
        writeService.writeAction("deleted student");
        }

    private Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            return null;
        }
    }
}
