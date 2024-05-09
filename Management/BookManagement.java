package Management;

import Models.Book;
import java.util.HashMap;
import java.util.Map;

public class BookManagement {
    private Map<Integer, Book> booksList = new HashMap<>();

    public void add(Book book) {
      booksList.put(book.getItemId(), book);
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
}
