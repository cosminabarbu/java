package Models;
import java.util.ArrayList;

public class Publisher {
    private String name;
    private ArrayList<Author> authors;

    public Publisher() {
    }

    public Publisher(String name, ArrayList<Author> authors) {
        this.name = name;
        this.authors = authors;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public ArrayList<Author> getAuthors() {

        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {

        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void deleteAuthor(Author author) {
        authors.remove(author);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", authors=" + authors +
                '}' + "\n";
    }


}
