package Service.AuditService.CustomerService;

import Models.Customer.Regular;
import Management.Customer.RegularManagement;
import Service.AuditService.WriteService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RegularService {
    private RegularManagement regularManagement;

    public RegularService() {
        this.regularManagement = new RegularManagement();
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
        Date birthday = parseDate(birthdayStr); // Conversia string-ului la Date

        System.out.println("Enter the address of the customer: ");
        String address = scanner.nextLine();
        System.out.println("Enter the customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consumăm newline-ul rămas

        Regular regular = new Regular(firstName, lastName, birthday, address);
        Regular result = regularManagement.add(regular);
        writeService.writeAction("added student");
        return result;
    }

    public Regular getCustomer(int regularId) {
        return regularManagement.get(regularId);
    }

    public void delete(int regularId) {
        WriteService writeService = new WriteService();

        regularManagement.delete(regularId);
        writeService.writeAction("deleted customer");
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