package Management;

import Models.Publisher;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;

public class PublisherManagement {
    private List<Publisher> publishersList;

    public void add(Publisher publisher){
        this.publishersList.add(publisher);
        System.out.println(publishersList);
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
        ListIterator<Publisher> iterator = publishersList.listIterator();
        while(iterator.hasNext()){
            Publisher publisher = iterator.next();
            if(publisher.getName().equals(name)){
                int index = iterator.previousIndex();
                iterator.set(updatedPublisher);
                return;
            }
        }
    }
}
