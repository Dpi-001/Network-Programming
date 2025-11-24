import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SourceViewerInput {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter URL (example: https://example.com): ");
        String urlInput = sc.nextLine();   

        try {
            
            URL u = new URL(urlInput);

         
            URLConnection uc = u.openConnection();

  
            try (InputStream raw = uc.getInputStream()) {

                InputStream buffer = new BufferedInputStream(raw);

             
                Reader reader = new InputStreamReader(buffer);

                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
            }

        } catch (MalformedURLException ex) {
            System.err.println(urlInput + " is not a valid URL!");
        } catch (IOException ex) {
            System.err.println("Error reading data: " + ex.getMessage());
        }
    }
}
