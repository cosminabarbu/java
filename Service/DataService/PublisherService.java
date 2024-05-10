package Service.DataService;

import Models.Author;
import Models.Publisher;
import Management.AuthorManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PublisherService {
    private List<Author> authorsList = new ArrayList<>();
    private AuthorManagement authorManagement = new AuthorManagement();

    public List<Publisher> readPublishersFromCSV(String filePath) {
        List<Publisher> publishers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String publisherName = parts[0];

                Publisher publisher = new Publisher(publisherName, new ArrayList<>());

                for (int i = 1; i < parts.length; i++) {
                    String authorName = parts[i];
                    Author author = authorManagement.get(authorName);

                    if (author != null) {
                        publisher.addAuthor(author);
                    } else {
                        System.out.println("Author not found for publisher " + publisherName + ": " + authorName);
                    }
                }

                publishers.add(publisher);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return publishers;
    }
}