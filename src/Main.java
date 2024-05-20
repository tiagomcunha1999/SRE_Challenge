import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import resources.MonitorData;

public class Main {

    private static List<MonitorData> datastore = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        
        if (args.length > 0) { // Code with arguments

            String command = args[0];
            
            switch (command) {

                case "backup": // Case when user try backup

                    if (args.length < 2) { // Warning user to put the file path
                        System.out.println("Usage: java Main backup <file path>");
                        return;
                    }

                    String backupPath = args[1];

                    try {
                        Backup.backupToFile(backupPath);
                        System.out.println("Backup saved to " + backupPath);
                    } catch (IOException e) {
                        System.err.println("Backup failed: " + e.getMessage());
                    }
                    break;


                case "restore":

                    if (args.length < 2) {
                        System.out.println("Usage: java Main restore <file path>");
                        return;
                    }else{
                    
                        String restorePath = "args[1]";

                        Restore.restoreFromFile(restorePath);
                        System.out.println("Data restored from " + restorePath);
                    }

                break;

                    default:
                    System.out.println("Unknown command: " + command);
                    break;
            }

        } else { //Code without pass arguments

            List<String> urlsToFetch = new ArrayList<>();
            int interval = 5;

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

            // Ensure the list is not empty
            if (urlsToFetch.isEmpty()) {
                System.out.println("The URLs list is empty!");
            } else {
                Fetch fetch = new Fetch();
                fetch.GetUrls(urlsToFetch);

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


