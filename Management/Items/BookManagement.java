package Management.Items;

import Models.Customer.Member;
import Models.Items.Book;
import Models.Section;
import Models.Author;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookManagement {
    private Map<Integer, Book> booksList = new HashMap<>();

    public BookManagement() {
        this.booksList = new HashMap<>();
    }

    public void loadBooksFromCSV(List<Book> books) {
        for (Book book : books) {
            this.booksList.put(book.getItemId(), book);
        }
    }

    public Book add(Book book) {
      booksList.put(book.getItemId(), book);
      System.out.println(booksList);
      return book;
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

    public Map<Integer, Book> getAllBooks(){
        return booksList;
    }

    public void update(int bookId, Book updatedBook){
        if(booksList.containsKey(bookId)){
            booksList.put(bookId, updatedBook);
            System.out.println("Book with id" + bookId + " was updated successfully");
        } else {
            System.out.println("Book with id" + bookId + " was not found");
        }
    }

    public List<Book> findByTitle(String title) {
        List<Book> matchingBooks = new ArrayList<>();

        for (Book book : booksList.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Book with title containing \"" + title + "\" found: " + book.getTitle());
                matchingBooks.add(book);
            }
        }

        if (matchingBooks.isEmpty()) {
            System.out.println("No books with title containing \"" + title + "\" were found");
        }

        return matchingBooks;
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

        public Author findAuthorWithMostBooks(List<Book> books) {
            Map<Author, Integer> authorBookCount = new HashMap<>();
            for (Book book : books) {
                Author author = book.getAuthor();
                authorBookCount.put(author, authorBookCount.getOrDefault(author, 0) + 1);
            }

            Author authorWithMostBooks = null;
            int maxBooks = 0;

            for (Map.Entry<Author, Integer> entry : authorBookCount.entrySet()) {
                if (entry.getValue() > maxBooks) {
                    maxBooks = entry.getValue();
                    authorWithMostBooks = entry.getKey();
                }
            }

            return authorWithMostBooks;
        }
    }

