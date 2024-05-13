package Service.AuditService;

import Management.CartManagement;
import Management.Customer.MemberManagement;
import Management.Customer.RegularManagement;
import Management.Customer.StudentManagement;
import Models.Cart;
import Models.Customer.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class CartService {

    private CartManagement cartManagement;
    private ArrayList<Customer> customers;
    private MemberManagement memberManagement;
    private RegularManagement regularManagement;
    private StudentManagement studentManagement;

    public CartService() {
        this.cartManagement = new CartManagement();
    }

    public void addCart() {
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();

        System.out.println("Select the type of customer: ");
        int option = scanner.nextInt();
        if(option == 1){
            System.out.println("You chose member: ");


        }

    }


}
