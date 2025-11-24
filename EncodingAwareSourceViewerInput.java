import java.io.*;
import java.net.*;
import java.util.Scanner;


// Download a web page with the correct character set

public class EncodingAwareSourceViewerInput {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

   
        System.out.print("Enter URL (example: https://example.com): ");
        String urlInput = sc.nextLine();

        try {
            // Default encoding
            String encoding = "ISO-8859-1";

            URL u = new URL(urlInput);
            URLConnection uc = u.openConnection();

            // Get content type and detect charset
            String contentType = uc.getContentType();
            int encodingStart = contentType != null ? contentType.indexOf("charset=") : -1;

            if (encodingStart != -1) {
                encoding = contentType.substring(encodingStart + 8);
            }

            InputStream in = new BufferedInputStream(uc.getInputStream());
            Reader r = new InputStreamReader(in, encoding);

            int c;
            System.out.println("\n===== SOURCE CODE OUTPUT =====\n");

            while ((c = r.read()) != -1) {
                System.out.print((char) c);
            }

            r.close();

        } catch (MalformedURLException ex) {
            System.err.println(urlInput + " is not a parseable URL");
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Server sent an unsupported encoding: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("I/O Error: " + ex.getMessage());
        }
    }
}
