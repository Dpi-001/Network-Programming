import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPServer {

    private final static int PORT = 13;
    private final static Logger audit = Logger.getLogger("requests");
    private final static Logger errors = Logger.getLogger("errors");

    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket(PORT);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("UDP Server running on port " + PORT + "...");

            while (true) {
                try {
                    // Receive packet
                    DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(request);

                    String clientMsg = new String(request.getData(), 0, request.getLength(), StandardCharsets.US_ASCII);
                    System.out.println("Client Says: " + clientMsg);

                    // Server reply
                    System.out.print("Enter reply: ");
                    String reply = br.readLine();

                    byte[] data = reply.getBytes(StandardCharsets.US_ASCII);
                    DatagramPacket response = new DatagramPacket(
                            data, data.length, request.getAddress(), request.getPort()
                    );
                    socket.send(response);

                    audit.info(new Date().toString() + " Request from: " + request.getAddress());

                } catch (IOException | RuntimeException ex) {
                    errors.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }

        } catch (IOException ex) {
            errors.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
