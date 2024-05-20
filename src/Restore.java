import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class Restore {

    private static final String DATA_STORE = "Results.txt";

    public static void restoreFromFile(String filePath) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            System.out.println("Backup file does not exist.");
            return;
        }

        List<String> restoredData;
        try {
            restoredData = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Error reading the backup file: " + e.getMessage());
            return;
        }

        List<String> currentData;
        try {
            currentData = Files.readAllLines(Paths.get(DATA_STORE));
        } catch (IOException e) {
            currentData = new ArrayList<>();
        }

        Set<String> mergedData = new LinkedHashSet<>(currentData);
        mergedData.addAll(restoredData);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(DATA_STORE))) {
            for (String line : mergedData) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Data restored successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to the data store: " + e.getMessage());
        }
    }
}
