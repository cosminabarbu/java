package Service.AuditService.ItemService;

import Management.AuthorManagement;
import Management.PublisherManagement;
import Models.Items.Book;
import Management.Items.BookManagement;
import Models.Author;
import Models.Publisher;
import Models.Section;
import Service.AuditService.WriteService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BookService {
    private BookManagement bookManagement;
    private ArrayList<Author> authorsList;
    private ArrayList<Publisher> publishersList;
    private AuthorManagement authorManagement = new AuthorManagement();
    private PublisherManagement publisherManagement = new PublisherManagement();
    private static BookService instance;

    private BookService() {
        this.bookManagement = new BookManagement();
        this.authorsList = new ArrayList<>();
        this.publishersList = new ArrayList<>();
    }

    public static BookService getInstance() {
        if (instance == null) {
            synchronized (BookService.class) {
                if (instance == null) {
                    instance = new BookService();
                }
            }
        }
        return instance;
    }

    public List<Book> readBooksFromCSV(String filePath, List<Author> authorsList, List<Publisher> publishersList, Set<Section> sections){
        List<Book> books = new ArrayList<>();
        authorManagement.loadAuthors(authorsList);
        publisherManagement.loadPublishers(publishersList);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String title = parts[0];
                double price = Double.parseDouble(parts[1]);
                int stock = Integer.parseInt(parts[2]);
                double rating = Double.parseDouble(parts[3]);
                String authorName = parts[4];
                String sectionName = parts[5];
                String publisherName = parts[6];
                int pageNo = Integer.parseInt(parts[7]);
                int year = Integer.parseInt(parts[8]);

                Author author = authorManagement.get(authorName);
                Section section = Section.valueOf(sectionName.toUpperCase());
                Publisher publisher = publisherManagement.get(publisherName);

                if (sections.contains(section)) {
                    Book book = new Book(title, price, stock, rating, author, section, publisher, pageNo, year);
                    books.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookManagement.loadBooks(books);
        return books;
    }

    public Book addBook() {
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();

        System.out.println("Enter the title of the book:");
        String title = scanner.nextLine();
        System.out.println("Enter the price of the book:");
        String price = scanner.nextLine();
        System.out.println("Enter the stock of the book:");
        String stock = scanner.nextLine();
        System.out.println("Enter the rating of the book:");
        String rating = scanner.nextLine();
        System.out.println("Enter the author of the book:");
        Author author = authorManagement.get(scanner.nextLine());
        System.out.println("Enter the section of the book:");
        Section section = getSectionByName(scanner.nextLine());
        System.out.println("Enter the publisher of the book:");
        Publisher publisher = publisherManagement.get(scanner.nextLine());
        System.out.println("Enter the number of pages of the book:");
        String pageNo = scanner.nextLine();
        System.out.println("Enter the year of publication of the book:");
        String year = scanner.nextLine();

        Book book = new Book(title, Double.parseDouble(price), Integer.parseInt(stock), Double.parseDouble(rating), author, section, publisher, Integer.parseInt(pageNo), Integer.parseInt(year));
        bookManagement.add(book);

        writeService.writeAction("Book added");
        return book;
    }

//    private Author getAuthorByName(String name) {
//        for (Author author : authorsList) {
//            if (author.getName().equalsIgnoreCase(name)) {
//                return author;
//            }
//        }
//        return null;
//    }

    private Section getSectionByName(String name) {
        for (Section section : Section.values()) {
            if (section.name().equalsIgnoreCase(name)) {
                return section;
            }
        }
        return null;
    }

//    private Publisher getPublisherByName(String name) {
//        for (Publisher publisher : publishersList) {
//            if (publisher.getName().equalsIgnoreCase(name)) {
//                return publisher;
//            }
//        }
//        return null;
//    }

    public Book getBook(int bookId) {
        return bookManagement.get(bookId);
    }

    public Map<Integer, Book> getAll(){
        return bookManagement.getAllBooks();
    }

    public Book updateBook(int bookId, Book updatedBook) {
        WriteService writeService = new WriteService();
        Book newBook = bookManagement.update(bookId, updatedBook);
        writeService.writeAction("Book updated");
        return newBook;
    }

    public void deleteBook(int bookId) {
        WriteService writeService = new WriteService();
        bookManagement.delete(bookId);
        writeService.writeAction("Book deleted");
    }

    public List<Author> findAuthorWithMostBooks(List<Book> books){
        WriteService writeService = new WriteService();
        List<Author> authorSearched =  bookManagement.findAuthorWithMostBooks(books);
        writeService.writeAction("Author with most books found");
        return authorSearched;
    }

    public List<Book> findBookByTitle(String title){
        WriteService writeService = new WriteService();
        List<Book> titleBooks = bookManagement.findByTitle(title);
        writeService.writeAction("Books found by title");
        return titleBooks;
    }

    public List<Book> findBooksBySection(String section){
        WriteService writeService = new WriteService();
        List<Book> sectionBooks = bookManagement.findBySection(section);
        writeService.writeAction("Books found by section");
        return sectionBooks;
    }
}
