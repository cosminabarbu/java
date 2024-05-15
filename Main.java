import Models.Author;
import Models.Cart;
import Models.Customer.Customer;
import Models.Customer.Member;
import Models.Items.Book;
import Models.Items.Vinyl;
import Models.Publisher;
import Service.AuditService.AuthorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        List<Publisher> publishers = publisherService.readPublishersFromCSV("Files/Database/Publisher.csv", authors);
//        List<Book> books = bookService.readBooksFromCSV("Files/Database/Book.csv");
//        List<Vinyl> vinyls = vinylService.readVinylsFromCSV("Files/Database/Vinyl.csv");

        System.out.println("Library");
        Scanner scanner = new Scanner(System.in);
        String option;

        do{
            System.out.println("\nEnter your choice: \n\"0\": Exit.\n\"1\": Actions for authors.\n\"2\" Actions for publishers." +
                    "\n\"3\": Actions for items.\n\"4\": Actions for customers." +
                    "\n\"5\": Actions for cart." );
            option = scanner.nextLine();
            System.out.println("You chose: " + option);

            switch (Integer.parseInt(option)) {
                case 1:
                    System.out.println("\nEnter your choice: \n\"0\": Exit.\n\"1\": Add author.\n\"2\" Get author." +
                            "\n\"3\": Update author.\n\"4\": Delete author.\n\"5\": Find author by nationality." );
                    option = scanner.nextLine();
                    System.out.println("You chose: " + option);
                    switch (Integer.parseInt(option)) {
                        case 1:
                            Author author1 = authorService.addAuthor();
                            System.out.println("Author added: " + author1);
                            break;
                        case 2:
                            System.out.println("Enter the name of the author: ");
                            String name2 = scanner.nextLine();
                            Author author2 = authorService.getAuthor(name2);
                            System.out.println("Author found: " + author2);
                            break;
                        case 3:
                            System.out.println("Enter the name of the author to be changed: ");
                            String name3 = scanner.nextLine();
//                            Author author3 = authorService.getAuthor(name3);
                            Author newAuthor = new Author();
                            System.out.println("Enter the author's new name: " );
                            String newName = scanner.nextLine();
                            newAuthor.setName(newName);
                            System.out.println("Enter the author's new nationality: ");
                            String newNationality = scanner.nextLine();
                            newAuthor.setNationality(newNationality);
                            System.out.println("Enter the author's new birth year: ");
                            String newBirthYear = scanner.nextLine();
                            newAuthor.setBirthYear(Integer.parseInt(newBirthYear));
                            authorService.updateAuthor(name3, newAuthor);
                            System.out.println("New author info: " + newAuthor);
                            break;
                        case 4:
                            System.out.println("Enter the name of the author to be deleted: ");
                            String name4 = scanner.nextLine();
                            Author author4 = authorService.getAuthor(name4);
                            authorService.deleteAuthor(name4);
                            System.out.println("Author deleted: " + author4);
                            break;
                        case 5:
                            System.out.println("Enter the nationality of the author to be found: ");
                            String nationality = scanner.nextLine().toLowerCase();
                            List<Author> authors1 = authorService.findAuthorByNationality(nationality);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\nEnter your choice: \n\"0\": Exit.\n\"1\": Add publisher.\n\"2\" Get publisher." +
                            "\n\"3\": Update publisher.\n\"4\": Delete publisher.\n\"5\": Add author to pubisher." +
                            "\n\"6\": Remove author from pubisher." );
                    option = scanner.nextLine();
                    System.out.println("You chose: " + option);
                    switch (Integer.parseInt(option)) {
                        case 1:
                            Publisher publisher1 = publisherService.addPublisher();
                            System.out.println("Author added: " + publisher1);
                            break;
                        case 2:
                            System.out.println("Enter the name of the publisher: ");
                            String name2 = scanner.nextLine();
                            Publisher publisher2 = publisherService.getPublisher(name2);
                            System.out.println("Publisher found: " + publisher2);
                            break;
                        case 3:
                            System.out.println("Enter the name of the author to be changed: ");
                            String name3 = scanner.nextLine();
                            Author author3 = authorService.getAuthor(name3);
                            //Author newAuthor = new Author();
                            System.out.println("Enter the author's new name: " );
                            String newName = scanner.nextLine();
                            author3.setName(newName);
                            System.out.println("Enter the author's new nationality: ");
                            String newNationality = scanner.nextLine();
                            author3.setNationality(newNationality);
                            System.out.println("Enter the author's new birth year: ");
                            int newBirthYear = scanner.nextInt();
                            author3.setBirthYear(newBirthYear);
                            authorService.updateAuthor(name3, author3);
                            System.out.println("New author info: " + author3);
                            break;
                        case 4:
                            System.out.println("Enter the name of the publisher to be deleted: ");
                            String name4 = scanner.nextLine();
                            Publisher publisher4 = publisherService.getPublisher(name4);
                            publisherService.deletePublisher(name4);
                            System.out.println("Publisher deleted: " + publisher4);
                            break;
                        case 5:
                            System.out.println("Enter the name of the publisher to add author to: ");
                            String publisherName5 = scanner.nextLine();
                            Publisher publisher5 = publisherService.getPublisher(publisherName5);
                            System.out.println("Enter the name of the author to be added: ");
                            String authorName5 = scanner.nextLine();
                            Author author5 = authorService.getAuthor(authorName5);
                            publisherService.addAuthorToPublisher(publisher5, author5);

                    }
            }
        } while (Integer.parseInt(option) != 0);
        scanner.close();











//        List<Author> authors = authorService.readAuthorsFromCSV("Files/Database/Author.csv");
//
//

        // Display the read authors
//        for (Author author : authors) {
//            System.out.println(author);
//
//        }
//
//        authorService.findAuthorByNationality("Italian");
//
//
//        Customer customer = new Member();
//        System.out.println(customer);
//
//        List<Book> books = new ArrayList<>();
//        List<Vinyl> vinyls = new ArrayList<>();
//
//        Cart cart = new Cart(customer, books, vinyls);
//        System.out.println(cart);

    }
}
