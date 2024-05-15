package Management;

import Models.Publisher;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;
import Models.Author;

public class PublisherManagement {
    private List<Publisher> publishersList;

    public PublisherManagement() {
        this.publishersList = new ArrayList<>();
    }

    public void loadPublishers(List<Publisher> publishers ) {
        for(Publisher publisher : publishers) {
            this.publishersList.add(publisher);
        }
    }

    public Publisher add(Publisher publisher){
        this.publishersList.add(publisher);
        return publisher;
    }

    public void delete(String name){
        Iterator<Publisher> iterator = publishersList.iterator();
        while(iterator.hasNext()){
            Publisher publisher = iterator.next();
            if(publisher.getName().toLowerCase().equals(name.toLowerCase())){
                iterator.remove();
                System.out.println("Publisher " + name + " deleted.");
                return;
            }
        }
        System.out.println("Publisher " + name + " was not found.");
    }

    public Publisher get(String name){
        Iterator<Publisher> iterator = publishersList.iterator();
        while(iterator.hasNext()){
            Publisher publisher = iterator.next();
            if(publisher.getName().toLowerCase().equals(name.toLowerCase())){
                return publisher;
            }
        }
        return null;
    }

    public List<Publisher> getAllPublishers(){
        return publishersList;
    }

    public void update(String name, Publisher updatedPublisher){
        ListIterator<Publisher> iterator = publishersList.listIterator();
        while(iterator.hasNext()){
            Publisher publisher = iterator.next();
            if(publisher.getName().toLowerCase().equals(name.toLowerCase())){
                iterator.set(updatedPublisher);
                return;
            }
        }
    }

    public void addAuthorToPublisher(Publisher publisher, Author author){
        if(publishersList.contains(publisher)){
            if (!publisher.getAuthors().contains(author)) {
                publisher.addAuthor(author);
                System.out.println(author.getName() + " added to publisher " + publisher.getName());
            } else {
                System.out.println(author.getName() + " is already associated with publisher " + publisher.getName());
            }
        }
        else {
            System.out.println("Publisher was not found.");
        }
    }

    public void removeAuthorFromPublisher(Publisher publisher, Author author){
        if(publishersList.contains(publisher)){
            publisher.deleteAuthor(author);
            System.out.println(author.getName() + " was removed from publisher " + publisher.getName());
        }
        else  {
            System.out.println("Publisher was not found.");
        }
    }
}
