
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class History {

    public static void displayHistory(String filePath) {
        
        List<String[]> rows = readDataFromFile(filePath);

        if (rows.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        printTable(rows);
    }


    public static List<String[]> readDataFromFile(String filePath) {

        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            //Read line by line
            while ((line = br.readLine()) != null) {

                // Adjusting to the format "http://example.com is available"
                String[] parts = line.split(" is ");
                // Checks if the line was correctly divided into two parts
                if (parts.length == 2) {
                    String url = parts[0].trim(); // Remove whitespace (trim()) from URL and status
                    String status = parts[1].trim();
                    rows.add(new String[]{url, status}); //Adds the URL and status as a string array to the rows list
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    //Defining the table format and filling it with data
    public static void printTable(List<String[]> rows) {
        String format = "| %-50s | %-15s |%n";

        System.out.format("+----------------------------------------------------+-----------------+%n");
        System.out.format("| URL                                                | Status          |%n");
        System.out.format("+----------------------------------------------------+-----------------+%n");

        for (String[] row : rows) {
            System.out.format(format, row[0], row[1]);
        }

        System.out.format("+----------------------------------------------------+-----------------+%n");
    }
}