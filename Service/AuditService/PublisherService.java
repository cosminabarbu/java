package Service.AuditService;

import Models.Author;
import Models.Publisher;
import Management.PublisherManagement;
import Service.AuditService.WriteService;

import java.util.ArrayList;
import java.util.Scanner;

public class PublisherService {
    private PublisherManagement publisherManagement;
    private ArrayList<Author> authorsList;

    public PublisherService() {
        this.publisherManagement = new PublisherManagement();
        this.authorsList = new ArrayList<>();
    }

    public void addPublisher() {
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();

        System.out.println("Enter the name of the publisher:");
        String name = scanner.nextLine();
        ArrayList<Author> authors = inputAuthors();
        Publisher publisher = new Publisher(name, authors);
        publisherManagement.add(publisher);
        writeService.writeAction("added publisher");
    }

    // Method to input authors for the publisher
    private ArrayList<Author> inputAuthors() {
        ArrayList<Author> authors = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of authors for this publisher:");
        int numAuthors = scanner.nextInt();
        scanner.nextLine(); // Consume newline

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
}
