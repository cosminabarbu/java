package Service.DataService;

import Models.Author;
import Models.Items.Book;
import Models.Publisher;
import Models.Section;
import Management.AuthorManagement;
import Management.PublisherManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private AuthorManagement authorManagement = new AuthorManagement();
    private PublisherManagement publisherManagement = new PublisherManagement();

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

        return books;
    }
}