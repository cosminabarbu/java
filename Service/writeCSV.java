package Service;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class writeCSV {

    private static final String path  = "files/actions.csv";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        writeLog("something happened");
    }

    public static void writeLog(String action) {
        try (FileWriter writer = new FileWriter(path, true)){
            long currentTimestamp = System.currentTimeMillis();
            String formattedDate = dateFormat.format(new Date(currentTimestamp));
            writer.write(action + ", " + formattedDate + "\n");
            System.out.println("The action " + action + " has been successfully executed.");

        } catch (IOException e) {
            System.err.println("There has been an error with the CSV file");
            e.printStackTrace();

        }
    }

}
