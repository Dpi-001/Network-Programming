// Sends a message to the multicast group.
import java.net.*;

public class Multicastsender {
    public static void main(String[] args) {

        MulticastSocket socket = null;

        try {
            // Multicast group address
            InetAddress group = InetAddress.getByName("230.0.0.1");

            // Create multicast socket
            socket = new MulticastSocket();

            String message = "Hello Multicast Group";
            byte[] buffer = message.getBytes();

            // Create DatagramPacket
            DatagramPacket packet =
                    new DatagramPacket(buffer, buffer.length, group, 4446);

            // Send packet
            socket.send(packet);

            System.out.println("Message sent to multicast group");

        } catch (Exception e) {
            System.out.println("Error in Multicast Sender: " + e);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}