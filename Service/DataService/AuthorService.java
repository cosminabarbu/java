package Service.DataService;

import Models.Author;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {

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

        return authors;
    }
}

