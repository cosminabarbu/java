package Management;

import Models.Publisher;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class PublisherManagement {
    private List<Publisher> publishersList;

    private PublisherManagement(){ this.publishersList = new ArrayList<Publisher>();}

    public void add(Publisher publisher){
        this.publishersList.add(publisher);
    }

    public void delete(String name){
        Iterator<Publisher> iterator = publishersList.iterator();
        while(iterator.hasNext()){
            Publisher publisher = iterator.next();
            if(publisher.getName().equals(name)){
                iterator.remove();
                System.out.println("Publisher" + name + " deleted.");
            }
        }
        System.out.println("Publisher" + name + " was not found.");
    }

    public Publisher get(String name){
        Iterator<Publisher> iterator = publishersList.iterator();
        while(iterator.hasNext()){
            Publisher publisher = iterator.next();
            if(publisher.getName().equals(name)){
                return publisher;
            }
        }
        return null;
    }

    public void update(String name, Publisher updatedPublisher){
        Iterator<Publisher> iterator = publishersList.iterator();
        while(iterator.hasNext()){
            Publisher publisher = iterator.next();
            if(publisher.getName().equals(name)){
                publisher.setName(name);
            }
        }
    }
}
