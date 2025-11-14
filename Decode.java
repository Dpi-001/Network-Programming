import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Scanner;

public class Decode {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== URL Decoder ===");
        System.out.println("Enter a URL to decode (type 'exit' to quit)\n");

        try {
            while (true) {
                System.out.print("Enter encoded URL: ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                // Decode the input
                String decoded = URLDecoder.decode(input, "UTF-8");
                System.out.println("Decoded URL: " + decoded + "\n");
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Broken VM does not support UTF-8");
        }

        scanner.close();
    }
}
