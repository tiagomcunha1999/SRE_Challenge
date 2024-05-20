import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class Restore {

    private static final String dataStore = "Results.txt";

    public static void restoreFromFile(String filePath) {
         
        try {
            Path path = Paths.get(filePath);

            // Checks if the backup file exists
            if (!Files.exists(path)) { 
                System.out.println("Backup file does not exist: " + path.toString());
                return;
            }

            // Reads all lines from the backup file and stores it in a list of strings
            List<String> restoredData = Files.readAllLines(path);

            List<String> currentData;

            // Try to read all lines from the existing data store file
            try {
                currentData = Files.readAllLines(Paths.get(dataStore));
            } catch (IOException e) {
                currentData = new ArrayList<>();
            }

            // Creates a Set that combines current data and restored data, eliminating duplicates
            Set<String> mergedData = new LinkedHashSet<>(currentData);
            mergedData.addAll(restoredData);

            // Writes all lines of the merged set
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(dataStore))) {
                for (String line : mergedData) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error writing to the data store: " + e.getMessage());
            }
        } catch (InvalidPathException e) {
            System.out.println("Invalid file path: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading the backup file: " + e.getMessage());
        }
    }
}
