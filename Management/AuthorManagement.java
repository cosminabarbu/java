package Management;

import Models.Author;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class AuthorManagement {
    private List<Author> authorsList;

    private AuthorManagement() {
        this.authorsList = new ArrayList<Author>();
    }

    public void add(Author author) {
        authorsList.add(author);
    }

    public void delete(String name){
        Iterator<Author> iterator = authorsList.iterator();
        while(iterator.hasNext()){
            Author author = iterator.next();
            if(author.getName().equals(name)){
                iterator.remove();
                System.out.println("Author" + name + "was deleted successfully");
            }
        }
        System.out.println("Author" + name + "was not found");
    }

    public Author getAuthor(String name){
        Iterator<Author> iterator = authorsList.iterator();
        while(iterator.hasNext()){
            Author author = iterator.next();
            if(author.getName().equals(name)){
                return author;
            }
        }
        return null;
    }

    public Author getAuthor(int index){
        return authorsList.get(index);

    }

    public void update(String name, Author updatedAuthor){
        Iterator<Author> iterator = authorsList.iterator();
        while(iterator.hasNext()){
            Author author = iterator.next();
            if(author.getName().equals(name)){
                author.setName(name);
            }
        }
    }

}
