package Management;

import Models.Author;
import Service.AuditService.AuthorService;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;

public class AuthorManagement {
    private List<Author> authorsList;

    public AuthorManagement() {
        this.authorsList = new ArrayList<>();
    }

    public void loadAuthors(List<Author> authors) {
        for(Author author : authors) {
            this.authorsList.add(author);
        }
    }

    public Author add(Author author) {
        authorsList.add(author);
        System.out.println(authorsList);
        return author;
    }

    public void delete(String name){
        Iterator<Author> iterator = authorsList.iterator();
        while(iterator.hasNext()){
            Author author = iterator.next();
            if(author.getName().toLowerCase().equals(name.toLowerCase())){
                iterator.remove();
                System.out.println("Author " + name + " was deleted successfully");
                return;
            }
        }
        System.out.println("Author" + name + "was not found");
    }

    public Author get(String name){
        Iterator<Author> iterator = authorsList.iterator();
        while(iterator.hasNext()){
            Author author = iterator.next();
            if(author.getName().toLowerCase().equals(name.toLowerCase())){
                return author;
            }
        }
        System.out.println("Author " + name + " was not found");
        return null;
    }

    public List<Author> getAllAuthors() {
        return authorsList;
    }

    public void update(String name, Author updatedAuthor){
        ListIterator<Author> iterator = authorsList.listIterator();
        while(iterator.hasNext()){
            Author author = iterator.next();
            if(author.getName().toLowerCase().equals(name.toLowerCase())){
                iterator.set(updatedAuthor);
                return;
            }
        }
        System.out.println("Author " + name + " was not found");
    }

    public List<Author> findByNationality(String nationality) {
        List<Author> foundAuthors = new ArrayList<>();
        for(Author author : authorsList){
            if(author.getNationality().toLowerCase().contains(nationality.toLowerCase())) {
//                System.out.println(author);
                foundAuthors.add(author);
            }
        }
        if(foundAuthors.size() == 0) {
            System.out.println("No "  + nationality + " author was not found");
            return null;
        } else {
            System.out.println("Authors found: ");
            for (Author author : foundAuthors) {
                System.out.println(author);
            }
        }
        return foundAuthors;

    }


}
