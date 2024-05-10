package Service;

import Management.AuthorManagement;
import Models.Items.Item;
import Models.Items.Author;
import java.io.IOException;
import java.util.ArrayList;
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
    }
}
