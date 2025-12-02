import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SocketInfo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter host name or IP address: ");
        String host = sc.nextLine().trim();   

        try {
            Socket theSocket = new Socket();
            theSocket.connect(new InetSocketAddress(host, 80), 5000); 

            System.out.println("Connected to " + theSocket.getInetAddress()
                    + " on port " + theSocket.getPort()
                    + " from port " + theSocket.getLocalPort()
                    + " of " + theSocket.getLocalAddress());

            theSocket.close();
        }
        catch (UnknownHostException ex) {
            System.err.println("Host not found: " + host);
        }
        catch (SocketTimeoutException ex) {
            System.err.println("Connection timed out.");
        }
        catch (SocketException ex) {
            System.err.println("Could not connect to " + host);
        }
        catch (IOException ex) {
            System.err.println("I/O Error: " + ex.getMessage());
        }
    }
}
