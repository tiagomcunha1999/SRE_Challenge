import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
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

        Fetch fetch = new Fetch(urlsToFetch); 
        fetch.GetUrls();

    }
}


