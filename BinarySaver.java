// Example 7-3. Downloading a binary file from a website and saving it to disk

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BinarySaver {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file URL: ");
        String urlInput = scanner.nextLine();

        try {
            URL url = new URL(urlInput);
            saveBinaryFile(url);
            System.out.println("File downloaded successfully!");
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL format!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void saveBinaryFile(URL url) throws IOException {

        URLConnection connection = url.openConnection();
        String contentType = connection.getContentType();
        int contentLength = connection.getContentLength();

        // Check that it's not a text file and content length is known
        if (contentType.startsWith("text/") || contentLength == -1) {
            throw new IOException("This is not a binary file or size unknown.");
        }

        try (InputStream raw = connection.getInputStream();
             InputStream in = new BufferedInputStream(raw)) {

            byte[] data = new byte[contentLength];
            int offset = 0;

            while (offset < contentLength) {
                int bytesRead = in.read(data, offset, data.length - offset);
                if (bytesRead == -1) break;
                offset += bytesRead;
            }

            if (offset != contentLength) {
                throw new IOException(
                    "Only read " + offset + " bytes; Expected " + contentLength + " bytes"
                );
            }

            // Extract filename
            String filename = url.getFile();
            filename = filename.substring(filename.lastIndexOf('/') + 1);

            try (FileOutputStream fout = new FileOutputStream(filename)) {
                fout.write(data);
                fout.flush();
            }
        }
    }
}


// output example URL for testing:
// https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf