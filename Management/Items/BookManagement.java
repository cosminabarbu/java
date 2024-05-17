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

    public void loadBooks(List<Book> books) {
        for (Book book : books) {
            this.booksList.put(book.getItemId(), book);
        }
    }

    public Book add(Book book) {
      booksList.put(book.getItemId(), book);
      //System.out.println(booksList);
      return book;
    }

    public void delete(int bookId){
        if(booksList.containsKey(bookId)){
            booksList.remove(bookId);
            System.out.println("Book with ID" + bookId + " was removed");
        } else {
            System.out.println("Book with ID" + bookId + " was not found");
        }
    }

    public Book get(int bookId){
        if(booksList.containsKey(bookId)){
            System.out.println("Book with ID" + bookId + " found");
            return booksList.get(bookId);
        } else {
            System.out.println("Book with ID" + bookId + " was not found");
            return null;
        }
    }

    public Map<Integer, Book> getAllBooks(){
        return booksList;
    }

    public Book update(int bookId, Book updatedBook){
        if(booksList.containsKey(bookId)){
            booksList.put(bookId, updatedBook);
            System.out.println("Book with ID" + bookId + " was updated successfully");
            return booksList.get(bookId);
        } else {
            System.out.println("Book with ID" + bookId + " was not found");
            return null;
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

    public List<Book> findBySection(String section) {
        List<Book> booksInSection = new ArrayList<>();
        for (Book book : booksList.values()) {
            if (book.getSection().toString().equals(section.toUpperCase())) {
                System.out.println("Book with section containing \"" + section + "\" found: " + book.getTitle());
                booksInSection.add(book);
            }
        }

        if (booksInSection.isEmpty()) {
            System.out.println("No books with section containing \"" + section + "\" were found");
        }

        return booksInSection;
    }

        public List<Author> findAuthorWithMostBooks(List<Book> books) {
            Map<Author, Integer> authorBookCount = new HashMap<>();
            for (Book book : books) {
                Author author = book.getAuthor();
                authorBookCount.put(author, authorBookCount.getOrDefault(author, 0) + 1);
            }

            List<Author> authorWithMostBooks = new ArrayList<Author>();
            int maxBooks = 0;

            for (Map.Entry<Author, Integer> entry : authorBookCount.entrySet()) {
                if (entry.getValue() > maxBooks) {
                    maxBooks = entry.getValue();
                }
            }
            for(Map.Entry<Author, Integer> entry : authorBookCount.entrySet()) {
                if(entry.getValue() == maxBooks) {
                    authorWithMostBooks.add(entry.getKey());
                }
            }

            return authorWithMostBooks;
        }
    }

