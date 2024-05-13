package Service.AuditService;

import Management.CartManagement;
import Models.Cart;

import java.util.Scanner;

public class CartService {

    private CartManagement cartManagement;

    public CartService() {
        this.cartManagement = new CartManagement();
    }

    public Cart addCart(){
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();

        System.out.println("Enter the customer: ");
        Customer
    }

}
