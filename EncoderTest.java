import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EncoderTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== URL Encoder ===");
        System.out.println("Enter a URL to encode (type 'exit' to quit)\n");

        try {
            while (true) {
                System.out.print("Enter URL: ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                // Encode the URL
                String encodedURL = URLEncoder.encode(input, "UTF-8");
                System.out.println("Encoded URL: " + encodedURL + "\n");
            }
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Broken VM does not support UTF-8");
        }

        scanner.close();
    }
}
