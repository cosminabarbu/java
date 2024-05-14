package Management.Items;

import Models.Customer.Member;
import Models.Items.Book;
import Models.Items.Vinyl;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class VinylManagement {
    private Map<Integer, Vinyl> vinylsList = new HashMap<>();

    public VinylManagement() {
        this.vinylsList = new HashMap<>();
    }

    public void loadVinylsFromCSV(List<Vinyl> vinyls) {
        for (Vinyl vinyl : vinyls) {
            this.vinylsList.put(vinyl.getItemId(), vinyl);
        }
    }

    public Vinyl add(Vinyl vinyl) {
        vinylsList.put(vinyl.getItemId(), vinyl);
        System.out.println(vinylsList);
        return vinyl;
    }

    public Vinyl get(int vinylId) {
        if(vinylsList.containsKey(vinylId)) {
            System.out.println("Vinyl with id " + vinylId + " was found");
            return vinylsList.get(vinylId);
        } else {
            System.out.println("Vinyl with id " + vinylId + " was not found");
            return null;
        }
    }

    public Map<Integer, Vinyl> getAll(){
        return vinylsList;
    }

    public void delete(int vinylId) {
        if(vinylsList.containsKey(vinylId)) {
            vinylsList.remove(vinylId);
            System.out.println("Vinyl with id " + vinylId + " was removed");
        } else {
            System.out.println("Vinyl with id " + vinylId + " was not found");
        }
    }

    public void update(int vinylId, Vinyl updatedVinyl) {
        if(vinylsList.containsKey(vinylId)) {
            vinylsList.put(vinylId, updatedVinyl);
            System.out.println("Vinyl with id " + vinylId + " was updated successfully");
        } else {
            System.out.println("Vinyl with id " + vinylId + " was not found");
        }
    }

    public List<Vinyl> findByTitle(String title) {
        List<Vinyl> matchingVinyls = new ArrayList<>();

        for (Vinyl vinyl : vinylsList.values()) {
            if (vinyl.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Vinyl with title containing \"" + title + "\" found: " + vinyl.getTitle());
                matchingVinyls.add(vinyl);
            }
        }

        if (matchingVinyls.isEmpty()) {
            System.out.println("No vinyls with title containing \"" + title + "\" were found");
        }

        return matchingVinyls;
    }

    public List<Vinyl> findByGenre(String genre) {
        List<Vinyl> foundVinyls = new ArrayList<>();

        for (Vinyl vinyl : vinylsList.values()) {
            if (vinyl.getGenre().toLowerCase().contains(genre.toLowerCase())) {
                foundVinyls.add(vinyl);
            }
        }

        if (!foundVinyls.isEmpty()) {
            System.out.println("Vinyls with genre containing \"" + genre + "\" found:");
            for (Vinyl foundVinyl : foundVinyls) {
                System.out.println(foundVinyl);
            }
        } else {
            System.out.println("No vinyls with genre containing \"" + genre + "\" found");
        }

        return foundVinyls;
    }
}
