import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        
        
        List<String> urlsToFetch = new ArrayList<>();

        try {
            // Get the urls from the file
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


        if (args.length > 0) { // Code with arguments

            String command = args[0];
            
            switch (command) {

                case "fetch": // Save the results
                
                    if (!urlsToFetch.isEmpty()) {
                        Fetch fetch = new Fetch();
                        fetch.GetUrls(urlsToFetch);
                    } else {
                        System.out.println("The URL list is empty!");
                    }
                    break;

                case "live": // Save the results with live checking having interval 

                    if (args.length < 2) {
                        System.out.println("Usage: java Main live <interval in seconds>");
                        return;
                    }

                    int interval = Integer.parseInt(args[1]);

                    if (!urlsToFetch.isEmpty()) {

                        Live live = new Live();
                        live.startLiveCheck(urlsToFetch, interval);

                        try {
                            Thread.sleep(20000); // Wait 20 secs until stopping the live search
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        live.stop();
                    } else {
                        System.out.println("The URL list is empty!");
                    }
                    break;

                case "history": // Exibe o histórico de resultados
                    History.displayHistory("live_check_results.txt");
                    break;

                case "restore":

                    if (args.length < 2) {
                        System.out.println("Usage: java Main restore <file path>");
                        return;
                    }else{
                    
                        String restorePath = args[1];

                        Restore.restoreFromFile(restorePath);
                        System.out.println("Data restored from " + restorePath);
                    }

                break;

                    default:
                    System.out.println("Unknown command: " + command);
                    break;
            }

        } else { //Code without pass arguments

            // Ensure the list is not empty
            if (urlsToFetch.isEmpty()) {
                System.out.println("The URLs list is empty!");
            } else {
                
                Fetch fetch = new Fetch();
                fetch.GetUrls(urlsToFetch);

                int interval = 5;

                Live live = new Live();
                live.startLiveCheck(urlsToFetch, interval);

                try {
                    Thread.sleep(20000); // Wait 20 secs until stopping the live search
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                live.stop();

                History.displayHistory("Results.txt");
            }
        }
    }
}


