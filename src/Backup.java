
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import resources.MonitorData;

public class Backup {

    private static List<MonitorData> data = new ArrayList<>();

    static { // Read the info from Results.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("Results.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" is ");
                if (parts.length == 2) {
                    data.add(new MonitorData(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       
    // Save the info in a backup File
    public static void backupToFile(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (MonitorData item : data) {
                writer.write("URL: " + item.getUrl() + ", Status: " + item.getStatus() + "\n");
            }
        }
    }

    // Save the info in a CSV File
    public static void backupToCSV(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("URL,Status\n");
            for (MonitorData item : data) {
                writer.write(item.getUrl() + "," + item.getStatus() + "\n");
            }
        }
    }

    // Save the info in a txt File
    public static void backupToTxt(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (MonitorData item : data) {
                writer.write("URL: " + item.getUrl() + ", Status: " + item.getStatus() + "\n");
            }
        }
    }
}