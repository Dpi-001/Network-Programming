import java.net.*;

public class Multicastreceiver {
    public static void main(String[] args) {

        MulticastSocket socket = null;

        try {
            // Create multicast socket on port
            socket = new MulticastSocket(4446);

            // Join multicast group
            InetAddress group = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(group);

            byte[] buffer = new byte[1024];

            // Create DatagramPacket
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Receive packet
            socket.receive(packet);

            String received =
                    new String(packet.getData(), 0, packet.getLength());

            System.out.println("Received: " + received);

            // Leave multicast group
            socket.leaveGroup(group);

        } catch (Exception e) {
            System.out.println("Error in Multicast Receiver: " + e);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}

