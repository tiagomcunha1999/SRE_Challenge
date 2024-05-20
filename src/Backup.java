
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import resources.MonitorData;

public class Backup {

    private static List<MonitorData> dataList = new ArrayList<>();

    // Static block to read and deserialize the info from Results.txt
    static {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("Results.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" is ");
                if (parts.length == 2) {
                    dataList.add(new MonitorData(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to save the info in a backup CSV file
    public static void backupToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("url,status\n");  // Write CSV header
            for (MonitorData item : dataList) {
                writer.write(item.getUrl() + "," + item.getStatus() + "\n");
            }
        }
    }


    // Save the info in a txt File
    public static void backupToTxt(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (MonitorData item : dataList) {
                writer.write("URL: " + item.getUrl() + ", Status: " + item.getStatus() + "\n");
            }
        }
    }

/*         // Method to copy file content exactly as it is
        public static void copyFile(String sourcePath, String destPath) throws IOException {
            Files.copy(Paths.get(sourcePath), new FileOutputStream(destPath));
        } */
}