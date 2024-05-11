package Service.AuditService.ItemService;

import Models.Items.Book;
import Management.BookManagement;
import Models.Author;
import Models.Publisher;
import Models.Section;
import Service.AuditService.WriteService;

import java.util.Scanner;
import java.util.ArrayList;

public class BookService {
    private BookManagement bookManagement;
    private ArrayList<Author> authorsList;
    private ArrayList<Publisher> publishersList;

    public BookService() {
        this.bookManagement = new BookManagement();
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
}
