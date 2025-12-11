import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPClient {

    private final static int PORT = 13;

    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket()) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                try {
                    System.out.print("Enter message to send: ");
                    String message = br.readLine();

                    // Send to server
                    InetAddress address = InetAddress.getByName("localhost");
                    byte[] data = message.getBytes(StandardCharsets.US_ASCII);

                    DatagramPacket request = new DatagramPacket(data, data.length, address, PORT);
                    socket.send(request);

                    // Receive reply
                    DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(response);

                    String reply = new String(response.getData(), 0, response.getLength(), StandardCharsets.US_ASCII);
                    System.out.println("Server Says: " + reply);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
