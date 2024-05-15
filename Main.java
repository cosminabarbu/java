import Models.Author;
import Models.Cart;
import Models.Customer.Customer;
import Models.Customer.Member;
import Models.Customer.Regular;
import Models.Customer.Student;
import Models.Items.Book;
import Models.Items.Vinyl;
import Models.Publisher;
import Models.Section;
import Service.AuditService.AuthorService;

import java.util.*;

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
        //List<Member>


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
                                    "\n\"3\": Update book.\n\"4\": Delete book.\n\"5\": Find authors with most books." +
                                    "\n\"6\": Find books by title.\n\"7\": Find book by section.\n\"8\": Get all books." );
                            option = scanner.nextLine();
                            System.out.println("You chose: " + option);
                            switch (Integer.parseInt(option)) {
                                case 1:
                                    Book bookAdd = bookService.addBook();
                                    System.out.println("Book added: " + bookAdd);
                                    break;
                                case 2:
                                    Map<Integer, Book> booksGet = bookService.getAll();
                                    for(Book book : booksGet.values()) {
                                        System.out.println(book);
                                    }
                                    System.out.println("Enter the ID of the book: ");
                                    String bookGetId = scanner.nextLine();
                                    Book bookGet = bookService.getBook(Integer.parseInt(bookGetId));
                                    System.out.println("Book found: " + bookGet);
                                    break;
                                case 3:
                                    System.out.println("Enter the ID of the book to be changed: ");
                                    String bookUpdateId = scanner.nextLine();
                                    System.out.println("Enter the book's new title: " );
                                    String title = scanner.nextLine();
                                    System.out.println("Enter the book's new price: ");
                                    String price = scanner.nextLine();
                                    System.out.println("Enter the book's new stock: ");
                                    String stock = scanner.nextLine();
                                    System.out.println("Enter the book's new rating: ");
                                    String rating = scanner.nextLine();
                                    System.out.println("Enter the book's new author: ");
                                    Author author = authorService.getAuthor(scanner.nextLine());
                                    System.out.println("Enter the book's new section: ");
                                    Section section = Section.valueOf(scanner.nextLine().toUpperCase());
                                    System.out.println("Enter the book's new publisher: ");
                                    Publisher publisher = publisherService.getPublisher(scanner.nextLine());
                                    System.out.println("Enter the book's new number of pages: ");
                                    String pageNo = scanner.nextLine();
                                    System.out.println("Enter the book's new year: ");
                                    String year = scanner.nextLine();
                                    Book newBook = bookService.updateBook(Integer.parseInt(bookUpdateId), new Book(title, Integer.parseInt(price), Integer.parseInt(stock), Double.parseDouble(rating),
                                            author, section, publisher, Integer.parseInt(pageNo), Integer.parseInt(year) ));
                                    System.out.println("New book info: " + newBook);
                                    break;
                                case 4:
                                    Map<Integer, Book> booksDelete = bookService.getAll();
                                    for(Book book : booksDelete.values()) {
                                        System.out.println(book);
                                    }
                                    System.out.println("Enter the ID of the book: ");
                                    String bookDeleteId = scanner.nextLine();
                                    Book bookDelete = bookService.getBook(Integer.parseInt(bookDeleteId));
                                    bookService.deleteBook(Integer.parseInt(bookDeleteId));
                                    System.out.println("Book found: " + bookDelete);
                                    break;
                                case 5:
                                    Map<Integer, Book> booksFindAuthor = bookService.getAll();
                                    List<Book> booksList = new ArrayList<>(booksFindAuthor.values());
                                    List<Author> authorsFindAuthor = bookService.findAuthorWithMostBooks(booksList);
                                    System.out.println("The authors found: ");
                                    for(Author authorBest : authorsFindAuthor){
                                        System.out.println(authorBest);
                                    }
                                    break;
                                case 6:
                                    System.out.println("Enter the title of the book to be found: ");
                                    String titleToBeFound = scanner.nextLine().toLowerCase();
                                    List<Book> booksFindByTitle = bookService.findBookByTitle(titleToBeFound);
                                    break;
                                case 7:
                                    System.out.println("Enter the section of the book to be found: ");
                                    String sectionToBeFound = scanner.nextLine().toLowerCase();
                                    List<Book> booksFindBySection = bookService.findBooksBySection(sectionToBeFound);
                                    break;
                                case 8:
                                    Map<Integer, Book> booksGetAll = bookService.getAll();
                                    for(Book book : booksGetAll.values()) {
                                        System.out.println(book);
                                    }
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("\nEnter your choice: \n\"0\": Exit.\n\"1\": Add vinyl.\n\"2\" Get vinyl." +
                                    "\n\"3\": Update vinyl.\n\"4\": Delete vinyl.\n\"5\": Find vinyls by title." +
                                    "\n\"6\": Find vinyls by genre.\n\"7\": Get all vinyls." );
                            option = scanner.nextLine();
                            System.out.println("You chose: " + option);
                            switch (Integer.parseInt(option)) {
                                case 1:
                                    Vinyl vinylAdd = vinylService.addVinyl();
                                    System.out.println("Vinyl added: " + vinylAdd);
                                    break;
                                case 2:
                                    Map<Integer, Vinyl> vinylsGet = vinylService.getAll();
                                    for(Vinyl vinyl : vinylsGet.values()) {
                                        System.out.println(vinyl);
                                    }
                                    System.out.println("Enter the ID of the vinyl: ");
                                    String vinylGetId = scanner.nextLine();
                                    Vinyl vinylGet = vinylService.getVinyl(Integer.parseInt(vinylGetId));
                                    System.out.println("Vinyl found: " + vinylGet);
                                    break;
                                case 3:
                                    System.out.println("Enter the ID of the vinyl to be changed: ");
                                    String vinylUpdateId = scanner.nextLine();
                                    System.out.println("Enter the vinyl's new title: " );
                                    String title = scanner.nextLine();
                                    System.out.println("Enter the vinyl's new price: ");
                                    String price = scanner.nextLine();
                                    System.out.println("Enter the vinyl's new stock: ");
                                    String stock = scanner.nextLine();
                                    System.out.println("Enter the vinyl's new rating: ");
                                    String rating = scanner.nextLine();
                                    System.out.println("Enter the vinyl's new singer: ");
                                    String singer = scanner.nextLine();
                                    System.out.println("Enter the vinyl's new genre: ");
                                    String genre = scanner.nextLine();
                                    System.out.println("Is the new vinyl in special edition? : ");
                                    String specialEdition = scanner.nextLine();
                                    Vinyl newVinyl = vinylService.updateVinyl(Integer.parseInt(vinylUpdateId), new Vinyl(title, Integer.parseInt(price), Integer.parseInt(stock), Double.parseDouble(rating),
                                            singer, genre, Boolean.parseBoolean(specialEdition) ));
                                    System.out.println("New vinyl info: " + newVinyl);
                                    break;
                                case 4:
                                    Map<Integer, Vinyl> vinylsDelete = vinylService.getAll();
                                    for(Vinyl vinyl : vinylsDelete.values()) {
                                        System.out.println(vinylsDelete);
                                    }
                                    System.out.println("Enter the ID of the vinyl to be deleted: ");
                                    String vinylDeleteId = scanner.nextLine();
                                    Vinyl vinylDelete = vinylService.getVinyl(Integer.parseInt(vinylDeleteId));
                                    vinylService.deleteVinyl(Integer.parseInt(vinylDeleteId));
                                    System.out.println("Vinyl found: " + vinylDelete);
                                    break;
                                case 5:
                                    System.out.println("Enter the title of the vinyl to be found: ");
                                    String titleToBeFound = scanner.nextLine().toLowerCase();
                                    List<Vinyl> vinylsFindByTitle = vinylService.findVinylByTitle(titleToBeFound);
                                    break;
                                case 6:
                                    System.out.println("Enter the genre of the vinyl to be found: ");
                                    String genreToBeFound = scanner.nextLine().toLowerCase();
                                    List<Vinyl> vinylsFindByGenre = vinylService.findVinylByGenre(genreToBeFound);
                                    break;

                                case 7:
                                    Map<Integer, Vinyl> vinylsGetAll = vinylService.getAll();
                                    for(Vinyl vinyl : vinylsGetAll.values()) {
                                        System.out.println(vinyl);
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
                case 4:
                    System.out.println("Enter the type of customer:\n\"1\": Regular.\n\"2\": Member.\n\"3\": Student.");
                    option = scanner.nextLine();
                    System.out.println("You chose: " + option);
                    switch (Integer.parseInt(option)) {
                        case 1:
                            System.out.println("\nEnter your choice: \n\"0\": Exit.\n\"1\": Add regular customer.\n\"2\" Get regular customer." +
                                    "\n\"3\": Update the address of regular customer.\n\"4\": Delete regular customer.\n\"5\": Get all regular customers.");
                            option = scanner.nextLine();
                            System.out.println("You chose: " + option);
                            switch (Integer.parseInt(option)) {
                                case 1:
                                    Regular regular = regularService.addCustomer();
                                    System.out.println("Regular customer added:" + regular);
                                    break;
                                case 2:
                                    System.out.print("Enter customer ID: ");
                                    int customerId = Integer.parseInt(scanner.nextLine());
                                    Regular customer = regularService.getCustomer(customerId);
                                    if (customer != null) {
                                        System.out.println("Regular customer details:");
                                        System.out.println(customer);
                                    } else {
                                        System.out.println("Regular customer not found.");
                                    }
                                    break;
                                case 3:
                                    System.out.print("Enter customer ID: ");
                                    int updateCustomerId = Integer.parseInt(scanner.nextLine());
                                    System.out.print("Enter new address: ");
                                    String newAddress = scanner.nextLine();
                                    regularService.updateCustomerAddress(updateCustomerId, newAddress);
                                    Regular regularUpdate = regularService.getCustomer(updateCustomerId);
                                    System.out.println("Customer address updated: " + regularUpdate);
                                    break;
                                case 4:
                                    Map<Integer, Regular> regularsDelete = regularService.getAllRegulars();
                                    for(Regular regularDelete : regularsDelete.values()) {
                                        System.out.println(regularDelete);
                                    }
                                    System.out.print("Enter customer ID to delete: ");
                                    int deleteCustomerId = Integer.parseInt(scanner.nextLine());
                                    regularService.delete(deleteCustomerId);
                                    Regular regularDelete = regularService.getCustomer(deleteCustomerId);
                                    System.out.println("Customer deleted: " + regularDelete);
                                    break;
                                case 5:
                                    System.out.println("All regular customers:");
                                    for (Regular regCustomer : regularService.getAllRegulars().values()) {
                                        System.out.println(regCustomer);
                                    }
                            }
                            break;
                        case 2:
                            System.out.println("\nEnter your choice: \n\"0\": Exit.\n\"1\": Add member customer.\n\"2\" Get member customer." +
                                    "\n\"3\": Update the address of member customer.\n\"4\": Delete member customer.\n\"5\": Renew membership for customer.\n\"6\": Get all member customers.");
                            option = scanner.nextLine();
                            System.out.println("You chose: " + option);
                            switch (Integer.parseInt(option)) {
                                case 1:
                                    Member member = memberService.addMember();
                                    System.out.println("Member customer added:" + member);
                                    break;
                                case 2:
                                    System.out.print("Enter customer ID: ");
                                    int customerId = Integer.parseInt(scanner.nextLine());
                                    Member memberGet = memberService.getMember(customerId);
                                    if (memberGet != null) {
                                        System.out.println("Member customer details:");
                                        System.out.println(memberGet);
                                    } else {
                                        System.out.println("Member customer not found.");
                                    }
                                    break;
                                case 3:
                                    System.out.print("Enter customer ID: ");
                                    int updateCustomerId = Integer.parseInt(scanner.nextLine());
                                    System.out.print("Enter new address: ");
                                    String newAddress = scanner.nextLine();
                                    memberService.updateMemberAddress(updateCustomerId, newAddress);
                                    Member memberUpdate = memberService.getMember(updateCustomerId);
                                    System.out.println("Customer address updated: " + memberUpdate);
                                    break;
                                case 4:
                                    Map<Integer, Member> membersDelete = memberService.getAllMembers();
                                    for(Member memberDelete : membersDelete.values()) {
                                        System.out.println(memberDelete);
                                    }
                                    System.out.print("Enter customer ID to delete: ");
                                    int deleteCustomerId = Integer.parseInt(scanner.nextLine());
                                    Member memberDelete = memberService.getMember(deleteCustomerId);
                                    memberService.deleteMember(deleteCustomerId);
                                    System.out.println("Customer deleted: " + memberDelete);
                                    break;
                                case 5:
                                    System.out.println("Enter customer ID to renew membership: ");
                                    int renewMemberId = Integer.parseInt(scanner.nextLine());
                                    memberService.checkAndRenewMembership(renewMemberId);
                                    Member memberRenew = memberService.getMember(renewMemberId);
                                    System.out.println("Member customer renewed: " + memberRenew);
                                    break;
                                case 6:
                                    System.out.println("All member customers:");
                                    for (Member memberCustomer : memberService.getAllMembers().values()) {
                                        System.out.println(memberCustomer);
                                    }
                                    break;
                            }
                            break;
                        case 3:
                            System.out.println("\nEnter your choice: \n\"0\": Exit.\n\"1\": Add student customer.\n\"2\" Get student customer." +
                                    "\n\"3\": Update the address of student customer.\n\"4\": Delete student customer.\n\"5\": Get all student customers.");
                            option = scanner.nextLine();
                            System.out.println("You chose: " + option);
                            switch (Integer.parseInt(option)) {
                                case 1:
                                    Student student = studentService.addStudent();
                                    System.out.println("Student customer added:" + student);
                                    break;
                                case 2:
                                    System.out.print("Enter customer ID: ");
                                    int customerId = Integer.parseInt(scanner.nextLine());
                                    Student studentGet = studentService.getStudent(customerId);
                                    if (studentGet != null) {
                                        System.out.println("Student customer details:");
                                        System.out.println(studentGet);
                                    } else {
                                        System.out.println("Student customer not found.");
                                    }
                                    break;
                                case 3:
                                    System.out.print("Enter customer ID: ");
                                    int updateCustomerId = Integer.parseInt(scanner.nextLine());
                                    System.out.print("Enter new address: ");
                                    String newAddress = scanner.nextLine();
                                    studentService.updateStudentAddress(updateCustomerId, newAddress);
                                    Student studentUpdate = studentService.getStudent(updateCustomerId);
                                    System.out.println("Customer address updated: " + studentUpdate);
                                    break;
                                case 4:
                                    Map<Integer, Student> studentsDelete = studentService.getAllStudents();
                                    for(Student studentDelete : studentsDelete.values()) {
                                        System.out.println(studentDelete);
                                    }
                                    System.out.print("Enter customer ID to delete: ");
                                    int deleteCustomerId = Integer.parseInt(scanner.nextLine());
                                    Student studentDelete = studentService.getStudent(deleteCustomerId);
                                    studentService.deleteStudent(deleteCustomerId);
                                    System.out.println("Customer deleted: " + studentDelete);
                                    break;
                                case 5:
                                    System.out.println("All student customers:");
                                    for (Student studentCustomer : studentService.getAllStudents().values()) {
                                        System.out.println(studentCustomer);
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
                case 5:
                    

            }
        } while (Integer.parseInt(option) != 0);
        scanner.close();








//        Cart cart = new Cart(customer, books, vinyls);
//        System.out.println(cart);

    }
}
