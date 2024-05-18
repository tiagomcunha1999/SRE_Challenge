

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Fetch {

    public void GetUrls(boolean outputToConsole) {

        List<String> urlsToFetch = new ArrayList<>();

        try {
            // Get the uls from the file
            InputStream inputStream = Main.class.getResourceAsStream("resources/urls.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                urlsToFetch.add(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String url : urlsToFetch) {
            boolean available = isURLAvailable(url);
            String result = "Availability: " + (available ? "available" : "unavailable");
            System.out.println("URL: " + url + " - " + result);
            // Output to console if the flag is set
            if (outputToConsole) {
                System.out.println(result);
            }

            // Save to data store
            DataStore.save(url + " - " + result);
        }
    }

    private static boolean isURLAvailable(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }
}
