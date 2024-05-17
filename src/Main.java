import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            // Get the uls from the file
            InputStream inputStream = Main.class.getResourceAsStream("/urls.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("URL: " + line);
                // Check availability
                boolean available = isURLAvailable(line);
                System.out.println("Availability: " + (available ? "available " : "unavailable"));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
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


