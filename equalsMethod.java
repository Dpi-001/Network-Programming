import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class equalsMethod {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter first domain: ");
            String domain1 = sc.nextLine();
 `
            System.out.print("Enter second domain: ");//second domain input
            String domain2 = sc.nextLine();

            // Get InetAddress objects
            InetAddress addr1 = InetAddress.getByName(domain1);
            InetAddress addr2 = InetAddress.getByName(domain2);

            // Display IP addresses
            System.out.println(domain1 + " IP: " + addr1.getHostAddress());
            System.out.println(domain2 + " IP: " + addr2.getHostAddress());

            // Compare
            if (addr1.equals(addr2)) {
                System.out.println("Both domains point to the same IP address (equal).");
            } else {
                System.out.println("Domains point to different IP addresses (not equal).");
            }

            // Show hashcode
            System.out.println("HashCode of " + domain1 + ": " + addr1.hashCode());
            System.out.println("HashCode of " + domain2 + ": " + addr2.hashCode());

        } catch (UnknownHostException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
