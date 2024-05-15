import Models.Author;
import Models.Cart;
import Models.Customer.Customer;
import Models.Customer.Member;
import Models.Items.Book;
import Models.Items.Vinyl;
import Models.Publisher;
import Models.Section;
import Service.AuditService.AuthorService;

import java.util.ArrayList;
import java.util.EnumSet;
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

        EnumSet<Section> sections = EnumSet.of(Section.FICTION, Section.NONFICTION, Section.DRAMA, Section.POETRY, Section.FOLKTALE );
        List<Author> authors = authorService.readAuthorsFromCSV("Files/Database/Author.csv");
        List<Publisher> publishers = publisherService.readPublishersFromCSV("Files/Database/Publisher.csv", authors);
        List<Book> books = bookService.readBooksFromCSV("Files/Database/Book.csv", authors, publishers, sections);
        List<Vinyl> vinyls = vinylService.readVinylsFromCSV("Files/Database/Vinyl.csv");


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
                            "\n\"3\": Update author.\n\"4\": Delete author.\n\"5\": Find author by nationality." +
                            "\n\"6\": Get all authors.");
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
                        case 6:
                            List<Author> authorsGetAll = authorService.getAll();
                            for(Author author : authorsGetAll) {
                                System.out.println(author);
                            }
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\nEnter your choice: \n\"0\": Exit.\n\"1\": Add publisher.\n\"2\" Get publisher." +
                            "\n\"3\": Update publisher.\n\"4\": Delete publisher.\n\"5\": Add author to pubisher." +
                            "\n\"6\": Remove author from pubisher.\n\"7\": Get all publishers." );
                    option = scanner.nextLine();
                    System.out.println("You chose: " + option);
                    switch (Integer.parseInt(option)) {
                        case 1:
                            Publisher publisher1 = publisherService.addPublisher();
                            System.out.println("Publisher added: " + publisher1);
                            break;
                        case 2:
                            System.out.println("Enter the name of the publisher: ");
                            String name2 = scanner.nextLine();
                            Publisher publisher2 = publisherService.getPublisher(name2);
                            System.out.println("Publisher found: " + publisher2);
                            break;
                        case 3:
                            System.out.println("Enter the name of the publisher to be changed: ");
                            String nameUpdate = scanner.nextLine();
                            System.out.println("Enter new authors list for this publisher.");
                            ArrayList<Author> newAuthors = publisherService.inputAuthors();
                            Publisher newPublisher = new Publisher(nameUpdate, newAuthors);
                            publisherService.updatePublisher(nameUpdate, newPublisher);
                            System.out.println("New publisher info: " + newPublisher);
                            break;
                        case 4:
                            System.out.println("Enter the name of the publisher to be deleted: ");
                            String nameDelete = scanner.nextLine();
                            Publisher publisherDelete = publisherService.getPublisher(nameDelete);
                            publisherService.deletePublisher(nameDelete);
                            System.out.println("Publisher deleted: " + publisherDelete);
                            break;
                        case 5:
                            System.out.println("Enter the name of the publisher to add author to: ");
                            String publisherNameAddAuthor = scanner.nextLine();
                            Publisher publisherAddAuthor = publisherService.getPublisher(publisherNameAddAuthor);
                            System.out.println("Enter the name of the author to be added: ");
                            String authorNameAddAuthor = scanner.nextLine();
                            Author authorAddAuthor = authorService.getAuthor(authorNameAddAuthor);
                            publisherService.addAuthorToPublisher(publisherAddAuthor, authorAddAuthor);
                            System.out.println("Publisher with new author list: " + publisherAddAuthor);
                            break;
                        case 6:
                            System.out.println("Enter the name of the publisher to remove author from: ");
                            String publisherNameDeleteAuthor = scanner.nextLine();
                            Publisher publisherDeleteAuthor = publisherService.getPublisher(publisherNameDeleteAuthor);
                            System.out.println("Enter the name of the author to be deleted: ");
                            String authorNameDeleteAuthor = scanner.nextLine();
                            Author authorDeleteAuthor = authorService.getAuthor(authorNameDeleteAuthor);
                            publisherService.removeAuthorFromPublisher(publisherDeleteAuthor, authorDeleteAuthor);
                            System.out.println("Publisher with new author list: " + publisherDeleteAuthor);
                            break;
                        case 7:
                            List<Publisher> publishersGetAll = publisherService.getAll();
                            for(Publisher publisher : publishersGetAll) {
                                System.out.println(publisher);
                            }
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Enter the type of the item:\n\"1\":Books.\n\"2\": Vinyls. ");
                    option = scanner.nextLine();
                    System.out.println("You chose: " + option);
                    switch (Integer.parseInt(option)) {
                        case 1:
                            System.out.println("\nEnter your choice: \n\"0\": Exit.\n\"1\": Add book.\n\"2\" Get book." +
                                    "\n\"3\": Update book.\n\"4\": Delete book.\n\"5\": Find author with most books." +
                                    "\n\"6\": Find book by title.\n\"7\": Find book by section.\n\"8\": Get all books." );
                            option = scanner.nextLine();
                            System.out.println("You chose: " + option);
                            switch (Integer.parseInt(option)) {
                                case 1:
                                    Book bookAdd = bookService.addBook();
                                    System.out.println("Book added: " + bookAdd);
                                    break;
                            }
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
