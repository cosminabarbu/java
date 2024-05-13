package Service.AuditService;

import Management.CartManagement;
import Management.Customer.MemberManagement;
import Management.Customer.RegularManagement;
import Management.Customer.StudentManagement;
import Models.Cart;
import Models.Customer.Customer;
import Models.Customer.Member;
import Models.Customer.Regular;
import Models.Customer.Student;

import java.util.*;

public class CartService {

    private CartManagement cartManagement;
    private Map<Integer, Member> members;
    private Map<Integer, Regular> regulars;
    private Map<Integer, Student> students;
    private MemberManagement memberManagement;
    private RegularManagement regularManagement;
    private StudentManagement studentManagement;


    public CartService() {
        this.cartManagement = new CartManagement();
        this.memberManagement = new MemberManagement();
    }

    public void addCart() {
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();


        System.out.println("Select the type of customer from 1-3: \n  1. Choose member \n 2. Choose regular \n 3. Choose student");
        int option = scanner.nextInt();
        Customer chosenCustomer;
        if(option == 1){
            System.out.println("You chose member: ");
            System.out.println("Choose from the following members: ");
            members = memberManagement.getAll();
            for(Map.Entry<Integer, Member> member : members.entrySet()){
                System.out.println("member ID: " + member.getKey() + " \n member: " + member.getValue());
            }
            int memberId = scanner.nextInt();
             chosenCustomer  = members.get(memberId);
        } else if (option == 2) {
            System.out.println("You chose regular: ");
            System.out.println("Choose from the following regulars: ");
            regulars = regularManagement.getAll();
            for(Map.Entry<Integer, Regular> regular : regulars.entrySet()){
                System.out.println("regular ID: " + regular.getKey() + " \n regular: " + regular.getValue());
            }
            int regularId = scanner.nextInt();
            chosenCustomer = regulars.get(regularId);
        } else if (option == 3) {
            System.out.println("You chose student: ");
            System.out.println("Choose from the following students: ");
            students = studentManagement.getAll();
            for(Map.Entry<Integer, Student> student : students.entrySet()){
                System.out.println("student ID: " + student.getKey() + " \n student: " + student.getValue());
            }
            int studentId = scanner.nextInt();
            chosenCustomer = students.get(studentId);
        } else {
            System.out.println("Invalid option");
        }

    }


}
