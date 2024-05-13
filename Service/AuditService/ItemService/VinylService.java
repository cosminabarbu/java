package Service.AuditService.ItemService;

import Management.Items.VinylManagement;
import Models.Items.Vinyl;
import Service.AuditService.WriteService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VinylService {
    private VinylManagement vinylManagement;

    public VinylService() {
        vinylManagement = new VinylManagement();
    }

    public List<Vinyl> readVinylsFromCSV(String filePath) {
        List<Vinyl> vinyls = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String title = parts[0];
                double price = Double.parseDouble(parts[1]);
                int stock = Integer.parseInt(parts[2]);
                double rating = Double.parseDouble(parts[3]);
                String singer = parts[4];
                String genre = parts[5];
                boolean specialEdition = Boolean.parseBoolean(parts[6]);

                Vinyl vinyl = new Vinyl(title, price, stock, rating, singer, genre, specialEdition);
                vinyls.add(vinyl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        vinylManagement.loadVinylsFromCSV(vinyls);
        return vinyls;
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

    public void findVinylByTitle(String title){
        WriteService writeService = new WriteService();
        vinylManagement.findByTitle(title);
        writeService.writeAction("find vinyl by title");
    }

}
