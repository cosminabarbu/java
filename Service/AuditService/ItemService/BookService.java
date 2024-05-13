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
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private BookManagement bookManagement;
    private ArrayList<Author> authorsList;
    private ArrayList<Publisher> publishersList;
    private AuthorManagement authorManagement = new AuthorManagement();
    private PublisherManagement publisherManagement = new PublisherManagement();

    public BookService() {
        this.bookManagement = new BookManagement();
    }

    public List<Book> readBooksFromCSV(String filePath) {
        List<Book> books = new ArrayList<>();

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
                Section section = Section.valueOf(sectionName);;
                Publisher publisher = publisherManagement.get(publisherName);

                Book book = new Book(title, price, stock, rating, author, section, publisher, pageNo, year);
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookManagement.loadBooksFromCSV(books);
        return books;
    }

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();

        System.out.println("Enter the title of the book:");
        String title = scanner.nextLine();
        System.out.println("Enter the price of the book:");
        double price = scanner.nextDouble();
        System.out.println("Enter the stock of the book:");
        int stock = scanner.nextInt();
        System.out.println("Enter the rating of the book:");
        double rating = scanner.nextDouble();
        System.out.println("Enter the author of the book:");
        Author author = getAuthorByName(scanner.nextLine());
        System.out.println("Enter the section of the book:");
        Section section = getSectionByName(scanner.nextLine());
        System.out.println("Enter the publisher of the book:");
        Publisher publisher = getPublisherByName(scanner.nextLine());
        System.out.println("Enter the number of pages of the book:");
        int pageNo = scanner.nextInt();
        System.out.println("Enter the year of publication of the book:");
        int year = scanner.nextInt();

        Book book = new Book(title, price, stock, rating, author, section, publisher, pageNo, year);
        bookManagement.add(book);

        writeService.writeAction("added book" + title);
    }

    private Author getAuthorByName(String name) {
        for (Author author : authorsList) {
            if (author.getName().equalsIgnoreCase(name)) {
                return author;
            }
        }
        return null;
    }

    private Section getSectionByName(String name) {
        for (Section section : Section.values()) {
            if (section.name().equalsIgnoreCase(name)) {
                return section;
            }
        }
        return null;
    }

    private Publisher getPublisherByName(String name) {
        for (Publisher publisher : publishersList) {
            if (publisher.getName().equalsIgnoreCase(name)) {
                return publisher;
            }
        }
        return null;
    }

    public Book getBook(int bookId) {
        return bookManagement.get(bookId);
    }

    public void updateBook(int bookId, Book updatedBook) {
        WriteService writeService = new WriteService();
        bookManagement.update(bookId, updatedBook);
        writeService.writeAction("Book updated: " + updatedBook.getTitle());
    }

    public void deleteBook(int bookId) {
        WriteService writeService = new WriteService();
        bookManagement.delete(bookId);
        writeService.writeAction("Book deleted with id: " + bookId);
    }

    public Author findAuthorWithMostBooks(List<Book> books){
        WriteService writeService = new WriteService();
        Author authorSearched =  bookManagement.findAuthorWithMostBooks(books);
        writeService.writeAction("Author with most books found");
        return authorSearched;
    }
}
