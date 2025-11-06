import java.net.*;

public class NetworkInterfaceByAddress {
    public static void main(String[] args) {
        try {
      
            InetAddress local = InetAddress.getByName("127.0.0.1");

       
            NetworkInterface ni = NetworkInterface.getByInetAddress(local);

            if (ni == null) {
                System.err.println("That's weird. No local loopback address.");
            } else {
                System.out.println("Network interface for " + local + ":");
                System.out.println(ni);
            }
        } catch (SocketException ex) {
            System.err.println("Could not list network interfaces: " + ex.getMessage());
        } catch (UnknownHostException ex) {
            System.err.println("That's weird. Could not lookup 127.0.0.1: " + ex.getMessage());
        }
    }
}
