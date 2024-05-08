package Management;

import Models.Book;
import Models.Cart;
import Models.Customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class CartManagement {
    private List<Cart> cartsList = new ArrayList<Cart>();

    public void printCart(int cartIndex) {
        if (cartIndex < 0 || cartIndex >= cartsList.size()) {
            System.out.println("Invalid cart index.");
            return;
        }

        Cart cart = cartsList.get(cartIndex);
        Customer customer = cart.getCustomer();
        customer.printDetails();

        List<Book> books = cart.getBooks();
        System.out.println("Books in Cart:");
        for (Book book : books) {
            book.printDetails();
        }
    }
}
