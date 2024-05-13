import Models.Author;
import Service.AuditService.AuthorService;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AuthorService authorService = new AuthorService();
        List<Author> authors = authorService.readAuthorsFromCSV("Files/Database/Author.csv");

        // Display the read authors
        for (Author author : authors) {
            System.out.println(author);

        }

        authorService.findAuthorByNationality("Italian");
    }
}
