

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Fetch {

    public void GetUrls(List<String> urlsToFetch) {

        for (String url : urlsToFetch) {
            boolean available = isURLAvailable(url);
            String result = "Availability: " + (available ? "available" : "unavailable");
            System.out.println("URL: " + url + " - " + result);

            // Save to data store
            Save(url, available);
        }
    }

    //Checks the connection to the given URL
    private static boolean isURLAvailable(String urlString) {
        
        try {

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            return (responseCode >= 200 && responseCode < 300);

        } catch (Exception e) {

            return false;

        }
    }

    // Save to data store
    private void Save(String url, boolean available) {
        String result = String.format("%s is %s\n", url, available ? "available" : "unavailable");
        try {
            Files.write(Paths.get("Results.txt"), result.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
