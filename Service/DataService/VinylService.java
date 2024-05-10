package Service.DataService;


import Models.Items.Vinyl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VinylService {

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

        return vinyls;
    }
}