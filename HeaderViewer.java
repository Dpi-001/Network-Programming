//return headers of multiple URLs entered by the user

import java.io.*;
import java.net.*;
import java.util.*;

public class HeaderViewer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter URLs separated by space:");
        String input = scanner.nextLine();
        scanner.close();

        // Split input into individual URLs
        String[] urls = input.trim().split("\\s+");

        for (String urlString : urls) {
            try {
                URL url = new URL(urlString);
                URLConnection connection = url.openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0"); // prevent server blocking

                System.out.println("--------------------------------------------------");
                System.out.println("URL: " + urlString);
                System.out.println("Content-type: " + connection.getContentType());

                if (connection.getContentEncoding() != null) {
                    System.out.println("Content-encoding: " + connection.getContentEncoding());
                }
                if (connection.getDate() != 0) {
                    System.out.println("Date: " + new Date(connection.getDate()));
                }
                if (connection.getLastModified() != 0) {
                    System.out.println("Last modified: " + new Date(connection.getLastModified()));
                }
                if (connection.getExpiration() != 0) {
                    System.out.println("Expiration date: " + new Date(connection.getExpiration()));
                }
                if (connection.getContentLength() != -1) {
                    System.out.println("Content-length: " + connection.getContentLength());
                }

            } catch (MalformedURLException e) {
                System.err.println(urlString + " is not a valid URL.");
            } catch (IOException e) {
                System.err.println("Error connecting to " + urlString + ": " + e.getMessage());
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Finished checking headers.");
    }
}
// Enter URL: https://www.wikipedia.org/