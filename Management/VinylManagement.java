package Management;

import Models.Vinyl;
import java.util.HashMap;
import java.util.Map;

public class VinylManagement {
    private Map<Integer, Vinyl> vinylsList = new HashMap<>();

    public void add(Vinyl vinyl) {
        vinylsList.put(vinyl.getItemId(), vinyl);
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

}
