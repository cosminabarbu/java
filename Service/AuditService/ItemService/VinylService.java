package Service.AuditService.ItemService;

import Management.Items.VinylManagement;
import Models.Author;
import Models.Items.Vinyl;
import Service.AuditService.WriteService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VinylService {
    private VinylManagement vinylManagement;
    private static VinylService instance;

    private VinylService() {
        vinylManagement = new VinylManagement();
    }

    public static VinylService getInstance() {
        if (instance == null) {
            synchronized (VinylService.class) {
                if (instance == null) {
                    instance = new VinylService();
                }
            }
        }
        return instance;
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
        vinylManagement.loadVinyls(vinyls);
        return vinyls;
    }

    public Vinyl addVinyl(){
        Scanner scanner = new Scanner(System.in);
        WriteService writeService = new WriteService();
        System.out.println("Enter the title of the vinyl:");
        String title = scanner.nextLine();
        System.out.println("Enter the price of the vinyl:");
        String price = scanner.nextLine();
        System.out.println("Enter the stock of the vinyl:");
        String stock = scanner.nextLine();
        System.out.println("Enter the rating of the vinyl:");
        String rating = scanner.nextLine();
        System.out.println("Enter the singer name:");
        String singer = scanner.nextLine();
        System.out.println("Enter the genre:");
        String genre = scanner.nextLine();
        System.out.println("Is it in limited edition?");
        String limitedEdition = scanner.nextLine();
        Vinyl vinyl = new Vinyl(title, Double.parseDouble(price), Integer.parseInt(stock), Double.parseDouble(rating), singer, genre, Boolean.parseBoolean(limitedEdition));
        Vinyl result = vinylManagement.add(vinyl);

        writeService.writeAction("Vinyl added");
        return result;
    }

    public Vinyl getVinyl(int vinylId){
        Vinyl vinyl = vinylManagement.get(vinylId);
        return vinyl;
    }

    public Map<Integer, Vinyl> getAll() {
        return vinylManagement.getAllVinyls();
    }

    public Vinyl updateVinyl(int vinylId, Vinyl vinyl){
        WriteService writeService = new WriteService();
        Vinyl newVinyl = vinylManagement.update(vinylId, vinyl);
        writeService.writeAction("Vinyl updated");
        return newVinyl;
    }

    public void deleteVinyl(int vinylId){
        WriteService writeService = new WriteService();
        vinylManagement.delete(vinylId);
        writeService.writeAction("Vinyl deleted");
    }

    public List<Vinyl> findVinylByTitle(String title){
        WriteService writeService = new WriteService();
        List<Vinyl> titleVynils = vinylManagement.findByTitle(title);
        writeService.writeAction("Vinyl searched by title");
        return titleVynils;
    }

    public List<Vinyl> findVinylByGenre(String genre){
        WriteService writeService = new WriteService();
        List<Vinyl> genreVynils = vinylManagement.findByGenre(genre);
        writeService.writeAction("Vinyl searched by genre");
        return genreVynils;
    }

}
