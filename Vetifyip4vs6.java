import java.net.*;
import java.util.Scanner;

public class Vetifyip4vs6 {

    public static int getVersion(InetAddress ia) {
        byte[] address = ia.getAddress();
        if (address.length == 4) return 4;
        else if (address.length == 16) return 6;
        else return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter hostname or IP address: ");
        String input = sc.nextLine();

        try {
            InetAddress address = InetAddress.getByName(input);
            int version = getVersion(address);

            System.out.println("Host Name: " + address.getHostName());
            System.out.println("IP Address: " + address.getHostAddress());
            System.out.println("IP Version: IPv" + version);
        } catch (UnknownHostException e) {
            System.out.println("Invalid address or hostname!");
        }

        sc.close();
    }
}
