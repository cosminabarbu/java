package Management;

import Models.Author;
import Models.Customer.Member;
import Models.Items.Book;
import Models.Cart;
import Models.Customer.Customer;
import Models.Items.Vinyl;

import java.util.*;

public class CartManagement {
    private List<Cart> cartsList = new ArrayList<Cart>();

    public Cart addCart(Cart cart) {
        if (cart.getCustomer() instanceof Member) {
            Member member = (Member) cart.getCustomer();
            if (member.isMembershipExpired(member)) {
                System.out.println("Cannot create cart. Membership for member " + member.getCustomerId() + " is expired.");
                return null;
            }
        }
        cartsList.add(cart);
        System.out.println("Cart added: " + cart);
        return cart;
    }

    public void deleteCart(Cart cart) {
        if (cartsList.remove(cart)) {
            System.out.println("Cart removed: " + cart);
        } else {
            System.out.println("Cart not found.");
        }
    }

    public Cart getCart(int index) {
        if (index >= 0 && index < cartsList.size()) {
            return cartsList.get(index);
        } else {
            System.out.println("Invalid index.");
            return null;
        }
    }

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

        for (Vinyl vinyl : cart.getVinyls()) {
            total += vinyl.getPrice();
        }

        double discount = cart.getCustomer().getDiscount();
        total *= discount;
        return total;
    }

    public void addBookToCart(Cart cart, Book book) {
        if (cartsList.contains(cart)) {
            cart.addBook(book);
//            System.out.println(book.getTitle() + " added to cart.");
        } else {
            System.out.println("Cart not found.");
        }
    }

    public void addVinylToCart(Cart cart, Vinyl vinyl) {
        if (cartsList.contains(cart)) {
            cart.addVinyl(vinyl);
//            System.out.println(vinyl.getTitle() + " added to cart.");
        } else {
            System.out.println("Cart not found.");
        }
    }

    public void deleteBookFromCart(Cart cart, Book book) {
        if (cartsList.contains(cart)) {
            if (cart.deleteBook(book)) {
                System.out.println(book.getTitle() + " removed from cart.");
            } else {
                System.out.println("Book not found in cart.");
            }
        } else {
            System.out.println("Cart not found.");
        }
    }

    public void deleteVinylFromCart(Cart cart, Vinyl vinyl) {
        if (cartsList.contains(cart)) {
            if (cart.deleteVinyl(vinyl)) {
                System.out.println(vinyl.getTitle() + " removed from cart.");
            } else {
                System.out.println("Vinyl not found in cart.");
            }
        } else {
            System.out.println("Cart not found.");
        }
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

}
