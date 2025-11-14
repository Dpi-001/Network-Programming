import java.net.*;
import java.util.Scanner;

public class URISplitterDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a URI: ");
        String input = scanner.nextLine();

        try {
            URI u = new URI(input);
            System.out.println("\nThe URI is " + u);

            if (u.isOpaque()) {
                System.out.println("This is an opaque URI.");
                System.out.println("The scheme is " + u.getScheme());
                System.out.println("The scheme specific part is " + u.getSchemeSpecificPart());
                System.out.println("The fragment ID is " + u.getFragment());
            } else {
                System.out.println("This is a hierarchical URI.");
                System.out.println("The scheme is " + u.getScheme());
                try {
                    u = u.parseServerAuthority();
                    System.out.println("The host is " + u.getHost());
                    System.out.println("The user info is " + u.getUserInfo());
                    System.out.println("The port is " + u.getPort());
                } catch (URISyntaxException ex) {
                 
                    System.out.println("The authority is " + u.getAuthority());
                }
                System.out.println("The path is " + u.getPath());
                System.out.println("The query string is " + u.getQuery());
                System.out.println("The fragment ID is " + u.getFragment());
            }
        } catch (URISyntaxException ex) {
            System.err.println(input + " does not seem to be a valid URI.");
        }

        scanner.close();
    }
}
