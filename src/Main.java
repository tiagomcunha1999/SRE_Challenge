import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        Fetch fetch = new Fetch(); 
        boolean outputToConsole = Arrays.asList(args).contains("--output");
        
        fetch.GetUrls(outputToConsole);

    }
}


