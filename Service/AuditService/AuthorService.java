package Service.AuditService;

import Models.Author;
import Management.AuthorManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthorService {
    private AuthorManagement authorManagement;
    private static AuthorService instance;

    private AuthorService() {
        this.authorManagement = new AuthorManagement();
    }

    public static AuthorService getInstance() {
        if (instance == null) {
            synchronized (AuthorService.class) {
                if(instance == null){
                    instance = new AuthorService();
                }
            }
        }
        return instance;
    }

    public List<Author> readAuthorsFromCSV(String filePath) {
        List<Author> authors = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String nationality = parts[1];
                Integer birthyear = Integer.parseInt(parts[2]);

                Author author = new Author(name, nationality, birthyear);
                authors.add(author);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        authorManagement.loadAuthorsFromCSV(authors);
        return authors;
    }

    public Author addAuthor(){
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();
        System.out.println("Enter the name of the author: ");
        String name = scanner.nextLine();
        System.out.println("Enter the nationality of the author: ");
        String nationality = scanner.nextLine();
        System.out.println("Enter the birth year of the author: ");
        int birthYear = scanner.nextInt();
        Author author = new Author(name, nationality, birthYear);
        Author result = authorManagement.add(author);

        writeService.writeAction("Author added");
        return result;

    }

    public Author getAuthor(String name){
        Author author = authorManagement.get(name);
        return author;
    }

    public void updateAuthor(String name, Author author){
        WriteService writeService = new WriteService();
        authorManagement.update(name, author);
        writeService.writeAction("Author updated");

    }

    public void deleteAuthor(String name){
        WriteService writeService = new WriteService();
        authorManagement.delete(name);
        writeService.writeAction("Author deleted");
    }

    public List<Author> findAuthorByNationality(String nationality){
        WriteService writeService = new WriteService();
        List<Author> authors = authorManagement.findByNationality(nationality);
        authorManagement.findByNationality(nationality);
        writeService.writeAction("Authors searched by nationality");
        return authors;
    }
}
