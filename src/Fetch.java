

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Fetch {

    private List<String> urlsToFetch;

    // Constructor to initialize the list
    public Fetch(List<String> urlsToFetch) {
        this.urlsToFetch = urlsToFetch;
    }
    
    public void GetUrls() {

        for (String url : urlsToFetch) {
            boolean available = isURLAvailable(url);
            String result = "Availability: " + (available ? "available" : "unavailable");
            System.out.println("URL: " + url + " - " + result);

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
