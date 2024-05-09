package Management;

import Models.Book;
import Models.Cart;
import Models.Customer.Customer;
import Models.Vinyl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartManagement {
    private List<Cart> cartsList = new ArrayList<Cart>();

    public double getTotal(int cartIndex) {
        if (cartIndex < 0 || cartIndex >= cartsList.size()) {
            System.out.println("Invalid cart index.");
            return 0;
        }

        Cart cart = cartsList.get(cartIndex);
        double total = 0;
        for (Book book : cart.getBooks()) {
            total += book.getPrice();
        }
        double discount = cart.getCustomer().getDiscount();
        total *= discount;
        return total;
    }

    public List<Book> bestsellerBooks() {
        Map<Book, Integer> bookFrequency = new HashMap<>();

        for (Cart cart : cartsList) {
            for (Book book : cart.getBooks()) {
                bookFrequency.put(book, bookFrequency.getOrDefault(book, 0) + 1);
            }
        }

        return getBestsellers(bookFrequency);
    }

    public List<Vinyl> bestsellerVinyls() {
        Map<Vinyl, Integer> vinylFrequency = new HashMap<>();

        for (Cart cart : cartsList) {
            for (Vinyl vinyl : cart.getVinyls()) {
                vinylFrequency.put(vinyl, vinylFrequency.getOrDefault(vinyl, 0) + 1);
            }
        }

        return getBestsellers(vinylFrequency);
    }

    private <T> List<T> getBestsellers(Map<T, Integer> frequencyMap) {
        List<T> bestsellers = new ArrayList<>();
        int maxFrequency = 0;

        for (int frequency : frequencyMap.values()) {
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
            }
        }

        for (Map.Entry<T, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                bestsellers.add(entry.getKey());
            }
        }

        return bestsellers;
    }

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
