import Models.Author;
import Models.Cart;
import Models.Customer.Customer;
import Models.Customer.Member;
import Models.Items.Book;
import Models.Items.Vinyl;
import Service.AuditService.AuthorService;

import java.util.ArrayList;
import java.util.List;
import Models.Customer.Customer;

public class Main {
    public static void main(String[] args) {
        AuthorService authorService = new AuthorService();
        List<Author> authors = authorService.readAuthorsFromCSV("Files/Database/Author.csv");

        // Display the read authors
        for (Author author : authors) {
            System.out.println(author);

        }

        authorService.findAuthorByNationality("Italian");

        Customer customer = new Member();
        System.out.println(customer);

        List<Book> books = new ArrayList<>();
        List<Vinyl> vinyls = new ArrayList<>();

        Cart cart = new Cart(customer, books, vinyls);
        System.out.println(cart);


    }
}
