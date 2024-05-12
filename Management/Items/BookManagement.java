package Management.Items;

import Models.Items.Book;
import Models.Section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookManagement {
    private Map<Integer, Book> booksList = new HashMap<>();

    public void add(Book book) {
      booksList.put(book.getItemId(), book);
      System.out.println(booksList);
    }

    public void delete(int bookId){
        if(booksList.containsKey(bookId)){
            booksList.remove(bookId);
            System.out.println("Book with id" + bookId + " was removed");
        } else {
            System.out.println("Book with id" + bookId + " was not found");
        }
    }

    public Book get(int bookId){
        if(booksList.containsKey(bookId)){
            System.out.println("Book with id" + bookId + " found");
            return booksList.get(bookId);
        } else {
            System.out.println("Book with id" + bookId + " was not found");
            return null;
        }
    }

    public void update(int bookId, Book updatedBook){
        if(booksList.containsKey(bookId)){
            booksList.put(bookId, updatedBook);
            System.out.println("Book with id" + bookId + " was updated successfully");
        } else {
            System.out.println("Book with id" + bookId + " was not found");
        }
    }

    public Book findByTitle(String title) {
        for (Book book : booksList.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Book with title containing \"" + title + "\" found");
                return book;
            }
        }
        System.out.println("Book with title containing \"" + title + "\" was not found");
        return null;
    }

    public List<Book> findBySection(Section section) {
        List<Book> booksInSection = new ArrayList<>();
        for (Book book : booksList.values()) {
            if (book.getSection() == section) {
                booksInSection.add(book);
            }
        }
        return booksInSection;
    }
}
