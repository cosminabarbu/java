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
import Service.AuditService.CartService;
import Service.AuditService.CustomerService.MemberService;
import Service.AuditService.CustomerService.RegularService;
import Service.AuditService.CustomerService.StudentService;
import Service.AuditService.ItemService.BookService;
import Service.AuditService.ItemService.VinylService;
import Service.AuditService.PublisherService;

public class Main {
    public static void main(String[] args) {

        AuthorService authorService = AuthorService.getInstance();
        BookService bookService = BookService.getInstance();
        PublisherService publisherService = PublisherService.getInstance();
        VinylService vinylService = VinylService.getInstance();
        MemberService memberService = MemberService.getInstance();
        RegularService regularService = RegularService.getInstance();
        StudentService studentService = StudentService.getInstance();
        CartService cartService = CartService.getInstance();














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
