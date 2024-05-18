import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Live {

    //Creates a ScheduledExecutorService with a thread pool containing 1 thread.
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    //Defines the task to be performed periodically 5 sec
    public void startLiveCheck(List<String> urls, int interval) {
        Runnable checkTask = () -> {
            for (String url : urls) {
                boolean isAvailable = checkAvailability(url);
                saveResult(url, isAvailable);
            }
        };

        //Schedules checkTask to run immediately and then every few secs
        ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(checkTask, 0, interval, TimeUnit.SECONDS);

        //Schedules scheduledFuture to be canceled after 15 seconds.
        scheduler.schedule(() -> scheduledFuture.cancel(true), 15, TimeUnit.SECONDS);
    }

    //Checks the connection to the given URL
    private boolean checkAvailability(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // Timeout in milliseconds
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            return (responseCode >= 200 && responseCode < 300);
        } catch (IOException e) {
            return false;
        }
    }

    // Save to data store
    private void saveResult(String url, boolean isAvailable) {
        String result = String.format("%s is %s\n", url, isAvailable ? "available" : "unavailable");
        try {
            Files.write(Paths.get("live_check_results.txt"), result.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Optional stop the search
    public void stop() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
        }
    }

}
