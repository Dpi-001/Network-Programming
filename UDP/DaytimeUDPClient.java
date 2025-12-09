import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DaytimeUDPClient {

    private final static int PORT = 13;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter hostname (example: time.nist.gov): ");
        String hostname = sc.nextLine();

        try (DatagramSocket socket = new DatagramSocket(0)) {
            socket.setSoTimeout(20000);

            InetAddress host = InetAddress.getByName(hostname);

            DatagramPacket request =
                    new DatagramPacket(new byte[1], 1, host, PORT);

            DatagramPacket response =
                    new DatagramPacket(new byte[1024], 1024);

            socket.send(request);
            socket.receive(response);

            String result = new String(
                    response.getData(), 0, response.getLength(), "US-ASCII"
            );

            System.out.println("Server Time: " + result);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
