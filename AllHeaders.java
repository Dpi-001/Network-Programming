// Example 7-5. Print the entire HTTP header
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class AllHeaders {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter URL: ");
        String urlInput = scanner.nextLine();

        try {
            URL url = new URL(urlInput);
            URLConnection uc = url.openConnection();

            System.out.println("\n=== All Headers ===");

            for (int i = 0; ; i++) {
                String key = uc.getHeaderFieldKey(i);
                String value = uc.getHeaderField(i);

                if (key == null && value == null) break;

                System.out.println((key != null ? key : "Status") + ": " + value);
            }

        } catch (MalformedURLException e) {
            System.err.println("Invalid URL format!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}
