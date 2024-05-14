package Service.AuditService.CustomerService;

import Models.Customer.Member;
import Models.Customer.Regular;
import Management.Customer.RegularManagement;
import Service.AuditService.WriteService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RegularService {
    private RegularManagement regularManagement;
    private static RegularService instance;

    private RegularService() {
        this.regularManagement = new RegularManagement();
    }

    public static RegularService getInstance() {
        if (instance == null) {
            synchronized (RegularService.class) {
                if (instance == null) {
                    instance = new RegularService();
                }
            }
        }
        return instance;
    }

    public Regular addCustomer() {
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();

        System.out.println("Enter the first name of the customer: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter the last name of the customer: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter the birthday of the customer (YYYY-MM-DD): ");
        String birthdayStr = scanner.nextLine();
        Date birthday = parseDate(birthdayStr);

        System.out.println("Enter the address of the customer: ");
        String address = scanner.nextLine();
        scanner.nextLine();
        Regular regular = new Regular(firstName, lastName, birthday, address);
        Regular result = regularManagement.add(regular);
        writeService.writeAction("added student");
        return result;
    }

    public Regular getCustomer(int customerId) {
        return regularManagement.get(customerId);
    }

    public void delete(int customerId) {
        WriteService writeService = new WriteService();

        regularManagement.delete(customerId);
        writeService.writeAction("deleted customer");
    }

    public void updateCustomerAddress(int customerId, String newAddress) {
        WriteService writeService = new WriteService();

        Regular regular = regularManagement.get(customerId);
        if (regular != null) {
            regularManagement.updateAddress(regular, newAddress);
            writeService.writeAction("Customer address updated");
        } else {
            System.out.println("Customer with ID " + customerId + " does not exist.");
        }
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            return null;
        }
    }
}