package Service.AuditService;

import Models.Author;
import Management.AuthorManagement;
import java.util.Scanner;

public class AuthorService {
    private AuthorManagement authorManagement;

    public AuthorService() {
        this.authorManagement = new AuthorManagement();
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

        writeService.writeAction("added author");
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

    public void findAuthorByNationality(String nationality){
        WriteService writeService = new WriteService();
        authorManagement.findByNationality(nationality);
        writeService.writeAction("find author by nationality");
    }
}
