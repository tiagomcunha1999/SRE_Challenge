import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        List<String> urlsToFetch = new ArrayList<>();

        int interval = 5; 

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

        //Ensure the list is not empty
        if (urlsToFetch.isEmpty()) {

            System.out.println("The URLs list is empty!");

        } else {

            Fetch fetch = new Fetch(); 
            fetch.GetUrls(urlsToFetch);

            Live live = new Live();
            live.startLiveCheck(urlsToFetch, interval);

            try {
                Thread.sleep(20000); // Wait 20 secs until stop the live shearch
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            live.stop(); 
        }

    }
}


