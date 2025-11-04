import java.io.IOException;
import java.net.InetAddress;

public class isReachable {
    public static void main(String[] args) {
        try {
            String host = "www.google.com"; 
            InetAddress address = InetAddress.getByName(host);

            System.out.println("Pinging " + host + " (" + address.getHostAddress() + ")...");

            // Check if the address is reachable within 1000 milliseconds (1 second)
            boolean reachable = address.isReachable(1000);

            if (reachable) {
                System.out.println(host + " is reachable.");
            } else {
                System.out.println(host + " is not reachable.");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
}
