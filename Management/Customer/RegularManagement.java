package Management.Customer;
import Models.Customer.Regular;
import java.util.HashMap;
import java.util.Map;

public class RegularManagement {
    Map<Integer, Regular> regularsList = new HashMap<>();

    public void add(Regular regular) {
        regularsList.put(regular.getCustomerId(), regular);
    }

    public void delete(int regularId) {
        if (regularsList.containsKey(regularId)) {
            regularsList.remove(regularId);
        } else {
            System.out.println("Regular with ID " + regularId + " does not exist.");
        }
    }

    public Regular get(int regularId) {
        return regularsList.get(regularId);
    }

    public void updateAddress(int regularId, String newAddress) {
        Regular regular = regularsList.get(regularId);
        if (regular != null) {
            regular.setAddress(newAddress);
        } else {
            System.out.println("Regular with ID " + regularId + " does not exist.");
        }
    }
}
