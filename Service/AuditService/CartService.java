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

import java.util.*;

public class CartService {

    private CartManagement cartManagement;
    private Map<Integer, Member> members;
    private Map<Integer, Regular> regulars;
    private Map<Integer, Student> students;
    private MemberManagement memberManagement;
    private RegularManagement regularManagement;
    private StudentManagement studentManagement;
    private Map<Integer, Book> books;
    private Map<Integer, Vinyl> vinyls;
    private BookManagement bookManagement;
    private VinylManagement vinylManagement;
    private static CartService instance;

    private CartService() {
        this.cartManagement = new CartManagement();
        this.memberManagement = new MemberManagement();
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


        System.out.println("Select the type of customer from 1-3: \n  1. Choose member \n 2. Choose regular \n 3. Choose student");
        int option = scanner.nextInt();
        Customer chosenCustomer;
        Book chosenBook;
        Vinyl chosenVinyl;
        List<Book> cartBooks = new ArrayList<>();
        List<Vinyl> cartViynls = new ArrayList<>();
        if(option == 1){
            System.out.println("You chose member: ");
            System.out.println("Choose from the following members: ");
            members = memberManagement.getAll();
            for(Map.Entry<Integer, Member> member : members.entrySet()){
                System.out.println("member ID: " + member.getKey() + " \n member: " + member.getValue());
            }
            int memberId = scanner.nextInt();
            chosenCustomer  = members.get(memberId);
        } else if (option == 2) {
            System.out.println("You chose regular: ");
            System.out.println("Choose from the following regulars: ");
            regulars = regularManagement.getAll();
            for(Map.Entry<Integer, Regular> regular : regulars.entrySet()){
                System.out.println("regular ID: " + regular.getKey() + " \n regular: " + regular.getValue());
            }
            int regularId = scanner.nextInt();
            chosenCustomer = regulars.get(regularId);
        } else if (option == 3) {
            System.out.println("You chose student: ");
            System.out.println("Choose from the following students: ");
            students = studentManagement.getAll();
            for(Map.Entry<Integer, Student> student : students.entrySet()){
                System.out.println("student ID: " + student.getKey() + " \n student: " + student.getValue());
            }
            int studentId = scanner.nextInt();
            chosenCustomer = students.get(studentId);
        } else {
            System.out.println("Invalid option");
            return null;
        }

        System.out.println("Add books from the following list: ");
        books = bookManagement.getAllBooks();
        int bookId = scanner.nextInt();
        while(bookId != -1 ) {
            for (Map.Entry<Integer, Book> book : books.entrySet()) {
                System.out.println("book ID: " + book.getKey() + " \n book: " + book.getValue());
            }
            chosenBook = books.get(bookId);
            cartBooks.add(chosenBook);
            bookId = scanner.nextInt();

        }

        System.out.println("Add vinyls from the following list: ");
        vinyls = vinylManagement.getAllVinyls();
        int vinylId = scanner.nextInt();
        while(vinylId != -1 ) {
            for (Map.Entry<Integer, Vinyl> vinyl : vinyls.entrySet()) {
                System.out.println("vinyl ID: " + vinyl.getKey() + " \n vinyl: " + vinyl.getValue());
            }
            chosenVinyl = vinyls.get(vinylId);
            cartViynls.add(chosenVinyl);
            vinylId = scanner.nextInt();

        }

        Cart cart = new Cart(chosenCustomer, cartBooks, cartViynls);
        Cart result = cartManagement.addCart(cart);
        writeService.writeAction("added cart");
        return result;
    }

    public Cart getCart(int index) {
        return cartManagement.getCart(index);
    }

    public void deleteCart(Cart cart){
        WriteService writeService = new WriteService();
        cartManagement.deleteCart(cart);
        writeService.writeAction("deleted cart");
    }

    public void addBook(Cart cart, Book book) {
        WriteService writeService = new WriteService();
        cartManagement.addBookToCart(cart, book);
        writeService.writeAction("added book to cart");
    }

    public void addVinyl(Cart cart, Vinyl vinyl) {
        WriteService writeService = new WriteService();
        cartManagement.addVinylToCart(cart, vinyl);
        writeService.writeAction("added vinyl to cart");
    }

    public void deleteBook(Cart cart, Book book) {
        WriteService writeService = new WriteService();
        cartManagement.deleteBookFromCart(cart, book);
        writeService.writeAction("deleted book from cart");
    }

    public void deleteVinyl(Cart cart, Vinyl vinyl) {
        WriteService writeService = new WriteService();
        cartManagement.deleteVinylFromCart(cart, vinyl);
        writeService.writeAction("deleted vinyl from cart");
    }

    public List<Book> bestBooks(){
        WriteService writeService = new WriteService();
        List<Book> bestBooks = cartManagement.bestsellerBooks();
        writeService.writeAction("best books called");
        return bestBooks;
    }

    public List<Vinyl> bestVinyls(){
        WriteService writeService = new WriteService();
        List<Vinyl> bestVinyls = cartManagement.bestsellerVinyls();
        writeService.writeAction("best vinyls called");
        return bestVinyls;
    }

    public double getTotal(int cartIndex){
        WriteService writeService = new WriteService();
        double total = cartManagement.getTotal(cartIndex);
        writeService.writeAction("total called");
        return total;
    }
}
