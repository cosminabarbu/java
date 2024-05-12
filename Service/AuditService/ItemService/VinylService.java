package Service.AuditService.ItemService;

import Management.Items.VinylManagement;
import Models.Items.Vinyl;
import Service.AuditService.WriteService;

import java.util.Scanner;

public class VinylService {
    private VinylManagement vinylManagement;

    public VinylService() {
        vinylManagement = new VinylManagement();
    }

    public Vinyl addVinyl(){
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();
        System.out.println("Enter the title of the vinyl:");
        String title = scanner.nextLine();
        System.out.println("Enter the price of the vinyl:");
        double price = scanner.nextDouble();
        System.out.println("Enter the stock of the vinyl:");
        int stock = scanner.nextInt();
        System.out.println("Enter the rating of the vinyl:");
        double rating = scanner.nextDouble();
        System.out.println("Enter the singer name:");
        String singer = scanner.nextLine();
        System.out.println("Enter the genre:");
        String genre = scanner.nextLine();
        System.out.println("Is it in limited edition?");
        Boolean limitedEdition = scanner.nextBoolean();
        Vinyl vinyl = new Vinyl(title, price, stock, rating, singer, genre, limitedEdition);
        Vinyl result = vinylManagement.add(vinyl);

        writeService.writeAction("added vinyl");
        return result;
    }

    public Vinyl getVinyl(int vinylId){
        Vinyl vinyl = vinylManagement.get(vinylId);
        return vinyl;
    }

    public void updateVinyl(int vinylId, Vinyl vinyl){
        WriteService writeService = new WriteService();
        vinylManagement.update(vinylId, vinyl);
        writeService.writeAction("vinyl updated");
    }

    public void deleteVinyl(int vinylId){
        WriteService writeService = new WriteService();
        vinylManagement.delete(vinylId);
        writeService.writeAction("vinyl deleted");
    }

}
