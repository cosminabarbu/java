package Service.AuditService;

import Management.CartManagement;
import Management.Customer.MemberManagement;
import Management.Customer.RegularManagement;
import Management.Customer.StudentManagement;
import Management.Items.BookManagement;
import Management.Items.VinylManagement;
import Models.Cart;
import Models.Customer.Customer;
import Models.Customer.Member;
import Models.Customer.Regular;
import Models.Customer.Student;
import Models.Items.Book;
import Models.Items.Vinyl;
import Service.AuditService.CustomerService.MemberService;
import Service.AuditService.CustomerService.RegularService;
import Service.AuditService.CustomerService.StudentService;
import Service.AuditService.ItemService.BookService;
import Service.AuditService.ItemService.VinylService;

import java.util.*;

public class CartService {

    private CartManagement cartManagement;
    private Map<Integer, Member> members;
    private Map<Integer, Regular> regulars;
    private Map<Integer, Student> students;
    private MemberService memberService;
    private RegularService regularService;
    private StudentService studentService;
    private BookService bookService;
    private VinylService vinylService;
    private Map<Integer, Book> books;
    private Map<Integer, Vinyl> vinyls;
    private static CartService instance;

    private CartService() {
        this.cartManagement = new CartManagement();
        this.memberService = MemberService.getInstance();
        this.regularService = RegularService.getInstance();
        this.studentService = StudentService.getInstance();
        this.bookService = BookService.getInstance();
        this.vinylService = VinylService.getInstance();
    }

    public static CartService getInstance() {
        if (instance == null) {
            synchronized (CartService.class) {
                if (instance == null) {
                    instance = new CartService();
                }
            }
        }
        return instance;
    }

    public Cart addCart() {
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();


        System.out.println("Select the type of customer from 1-3: \n 1. Choose member \n 2. Choose regular \n 3. Choose student");
        String option = scanner.nextLine();
        Customer chosenCustomer;
        Book chosenBook;
        Vinyl chosenVinyl;
        List<Book> cartBooks = new ArrayList<>();
        List<Vinyl> cartViynls = new ArrayList<>();
        if(Integer.parseInt(option) == 1){
            System.out.println("You chose member: ");
            System.out.println("Choose from the following members: ");
            members = memberService.getAllMembers();
            for(Map.Entry<Integer, Member> member : members.entrySet()){
                System.out.println("Member ID: " + member.getKey() + " \nMember: " + member.getValue());
            }
            String memberId = scanner.nextLine();
            chosenCustomer  = members.get(Integer.parseInt(memberId));
        } else if (Integer.parseInt(option) == 2) {
            System.out.println("You chose regular: ");
            System.out.println("Choose from the following regulars: ");
            regulars = regularService.getAllRegulars();
            for(Map.Entry<Integer, Regular> regular : regulars.entrySet()){
                System.out.println("Regular ID: " + regular.getKey() + " \nRegular: " + regular.getValue());
            }
            String regularId = scanner.nextLine();
            chosenCustomer = regulars.get(Integer.parseInt(regularId));
        } else if (Integer.parseInt(option) == 3) {
            System.out.println("You chose student: ");
            System.out.println("Choose from the following students: ");
            students = studentService.getAllStudents();
            for(Map.Entry<Integer, Student> student : students.entrySet()){
                System.out.println("Student ID: " + student.getKey() + " \nStudent: " + student.getValue());
            }
            String studentId = scanner.nextLine();
            chosenCustomer = students.get(Integer.parseInt(studentId));
        } else {
            System.out.println("Invalid option");
            return null;
        }

        System.out.println("Add books from the following list: ");
        books = bookService.getAll();
        for (Map.Entry<Integer, Book> book : books.entrySet()) {
            System.out.println("Book ID: " + book.getKey() + " \nBook: " + book.getValue());
        }
        String bookId = scanner.nextLine();
        while(Integer.parseInt(bookId) != -1 ) {
            for (Map.Entry<Integer, Book> book : books.entrySet()) {
                System.out.println("Book ID: " + book.getKey() + " \nBook: " + book.getValue());
            }
            chosenBook = books.get(Integer.parseInt(bookId));
            cartBooks.add(chosenBook);
            bookId = scanner.nextLine();

        }

        System.out.println("Add vinyls from the following list: ");
        vinyls = vinylService.getAll();
        for (Map.Entry<Integer, Vinyl> vinyl : vinyls.entrySet()) {
            System.out.println("Vinyl ID: " + vinyl.getKey() + " \nVinyl: " + vinyl.getValue());
        }
        String vinylId = scanner.nextLine();
        while(Integer.parseInt(vinylId) != -1 ) {
            for (Map.Entry<Integer, Vinyl> vinyl : vinyls.entrySet()) {
                System.out.println("Vinyl ID: " + vinyl.getKey() + " \nVinyl: " + vinyl.getValue());
            }
            chosenVinyl = vinyls.get(Integer.parseInt(vinylId));
            cartViynls.add(chosenVinyl);
            vinylId = scanner.nextLine();
        }

        Cart cart = new Cart(chosenCustomer, cartBooks, cartViynls);
        Cart result = cartManagement.addCart(cart);
        writeService.writeAction("Cart added");
        return result;
    }

    public Cart getCart(int index) {
        return cartManagement.getCart(index);
    }

    public void deleteCart(Cart cart){
        WriteService writeService = new WriteService();
        cartManagement.deleteCart(cart);
        writeService.writeAction("Cart deleted");
    }

    public void addBook(Cart cart, Book book) {
        WriteService writeService = new WriteService();
        cartManagement.addBookToCart(cart, book);
        writeService.writeAction("Book added to cart");
    }

    public void addVinyl(Cart cart, Vinyl vinyl) {
        WriteService writeService = new WriteService();
        cartManagement.addVinylToCart(cart, vinyl);
        writeService.writeAction("Vinyl added to cart");
    }

    public void deleteBook(Cart cart, Book book) {
        WriteService writeService = new WriteService();
        cartManagement.deleteBookFromCart(cart, book);
        writeService.writeAction("Book deleted from cart");
    }

    public void deleteVinyl(Cart cart, Vinyl vinyl) {
        WriteService writeService = new WriteService();
        cartManagement.deleteVinylFromCart(cart, vinyl);
        writeService.writeAction("Vinyl deleted from cart");
    }

    public List<Book> bestBooks(){
        WriteService writeService = new WriteService();
        List<Book> bestBooks = cartManagement.bestsellerBooks();
        writeService.writeAction("Best books called");
        return bestBooks;
    }

    public List<Vinyl> bestVinyls(){
        WriteService writeService = new WriteService();
        List<Vinyl> bestVinyls = cartManagement.bestsellerVinyls();
        writeService.writeAction("Best vinyls called");
        return bestVinyls;
    }

    public double getTotal(int cartIndex){
        WriteService writeService = new WriteService();
        double total = cartManagement.getTotal(cartIndex);
        writeService.writeAction("Total called");
        return total;
    }
}
