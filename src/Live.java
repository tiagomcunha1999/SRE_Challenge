import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Live {

     private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

     int interval = 5; 
}
