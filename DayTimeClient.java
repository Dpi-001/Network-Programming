import java.net.*;
import java.io.*;
import java.util.Scanner;

public class DayTimeClient {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter hostname (example: time.nist.gov): ");
        String hostname = sc.nextLine();

        Socket socket = null;

        try {
            socket = new Socket(hostname, 13);
            socket.setSoTimeout(15000);

            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, "ASCII");

            StringBuilder time = new StringBuilder();

            for (int c = reader.read(); c != -1; c = reader.read()) {
                time.append((char) c);
            }

            System.out.println("Server Time: " + time);

        } catch (IOException ex) {
            System.err.println("Error: " + ex);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    // ignore
                }
            }
        }
    }
}
