// Example 4-8. A program that lists all the network interfaces and counts them
import java.net.*;
import java.util.*;

public class InterfaceLister {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        int count = 0; 

        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            System.out.println(ni);
            count++; 
        }

        System.out.println("Total number of network interfaces: " + count);
    }
}
