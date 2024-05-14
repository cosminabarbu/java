package Service.AuditService;

import Management.AuthorManagement;
import Models.Author;
import Models.Publisher;
import Management.PublisherManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PublisherService {
    private PublisherManagement publisherManagement;
    private ArrayList<Author> authorsList;
    private AuthorManagement authorManagement = new AuthorManagement();
    private static PublisherService instance;

    private PublisherService() {
        this.publisherManagement = new PublisherManagement();
        this.authorsList = new ArrayList<>();
    }

    public static PublisherService getInstance() {
        if (instance == null) {
            synchronized (PublisherService.class) {
                if (instance == null) {
                    instance = new PublisherService();
                }
            }
        }
        return instance;
    }

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
        publisherManagement.loadPublishersFromCSV(publishers);
        return publishers;
    }

    public Publisher addPublisher() {
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();

        System.out.println("Enter the name of the publisher:");
        String name = scanner.nextLine();
        ArrayList<Author> authors = inputAuthors();
        Publisher publisher = new Publisher(name, authors);
        Publisher result = publisherManagement.add(publisher);
        writeService.writeAction("added publisher");
        return result;
    }

    // Method to input authors for the publisher
    private ArrayList<Author> inputAuthors() {
        ArrayList<Author> authors = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of authors for this publisher:");
        int numAuthors = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numAuthors; i++) {
            System.out.println("Enter author " + (i + 1) + " name:");
            String authorName = scanner.nextLine();
            Author author = getAuthorByName(authorName);
            if (author != null) {
                authors.add(author);
            } else {
                System.out.println("Author not found. Please add the author first.");
            }
        }

        return authors;
    }

    private Author getAuthorByName(String name) {
        for (Author author : authorsList) {
            if (author.getName().equalsIgnoreCase(name)) {
                return author;
            }
        }
        return null;
    }

    public Publisher getPublisher(String name) {
        return publisherManagement.get(name);
    }

    public void updatePublisher(String name, Publisher updatedPublisher) {
        WriteService writeService = new WriteService();
        publisherManagement.update(name, updatedPublisher);
        writeService.writeAction("Publisher updated: " + name);
    }

    public void deletePublisher(String name) {
        WriteService writeService = new WriteService();
        publisherManagement.delete(name);
        writeService.writeAction("Publisher deleted: " + name);
    }

    public void addAuthorToPublisher(Publisher publisher, Author author) {
        WriteService writeService = new WriteService();
        publisherManagement.addAuthorToPublisher(publisher, author);
        writeService.writeAction("Author added.");
    }

    public void removeAuthorFromPublisher(Publisher publisher, Author author) {
        WriteService writeService = new WriteService();
        publisherManagement.removeAuthorFromPublisher(publisher, author);
        writeService.writeAction("Author removed.");
    }
}
