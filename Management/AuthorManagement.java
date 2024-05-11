package Management;

import Models.Author;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;

public class AuthorManagement {
    private List<Author> authorsList;

    public Author add(Author author) {
        authorsList.add(author);
        System.out.println(authorsList);
        return author;
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

    public Author get(String name){
        Iterator<Author> iterator = authorsList.iterator();
        while(iterator.hasNext()){
            Author author = iterator.next();
            if(author.getName().equals(name)){
                return author;
            }
        }
        return null;
    }

    public void update(String name, Author updatedAuthor){
        ListIterator<Author> iterator = authorsList.listIterator();
        while(iterator.hasNext()){
            Author author = iterator.next();
            if(author.getName().equals(name)){
                int index = iterator.previousIndex();
                iterator.set(updatedAuthor);
                return;
            }
        }
    }

}
