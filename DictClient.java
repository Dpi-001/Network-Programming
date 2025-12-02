import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DictClient {

    public static final String SERVER = "dict.org";
    public static final int PORT = 2628;
    public static final int TIMEOUT = 15000;

    public static void main(String[] args) {

        Socket socket = null;
        Scanner sc = new Scanner(System.in);

        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(SERVER, PORT), TIMEOUT);
            socket.setSoTimeout(TIMEOUT);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), "UTF-8"));

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            String greeting = reader.readLine();
            System.out.println("Connected to DICT Server:");
            System.out.println(greeting);

            System.out.print("\nEnter English word to translate to Latin: ");
            String word = sc.nextLine();

            define(word, writer, reader);

            writer.write("QUIT\r\n");
            writer.flush();

        } catch (SocketTimeoutException e) {
            System.out.println("Connection timed out!");
            System.out.println("Reason: Port 2628 is blocked on your network.");
        } catch (IOException ex) {
            System.err.println("Error: " + ex);
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
            }
        }
    }

    static void define(String word, BufferedWriter writer,
                       BufferedReader reader) throws IOException {

        writer.write("DEFINE eng-lat " + word + "\r\n");
        writer.flush();

        String line;

        while ((line = reader.readLine()) != null) {

            if (line.startsWith("250 ")) {  
                return;
            } 
            else if (line.startsWith("552 ")) {  
                System.out.println("No definition found for: " + word);
                return;
            } 
            else if (line.matches("\\d\\d\\d .*")) {
                continue;
            } 
            else if (line.trim().equals(".")) {
                continue;
            } 
            else {
                System.out.println(line);
            }
        }
    }
}
