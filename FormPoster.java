import java.io.*;
import java.net.*;
import java.util.Scanner;

class QueryString {
    private StringBuilder query = new StringBuilder();

    QueryString() {}

    public synchronized void add(String var1, String var2) {
        this.query.append('&');
        this.encode(var1, var2);
    }

    private synchronized void encode(String var1, String var2) {
        try {
            this.query.append(URLEncoder.encode(var1, "UTF-8"));
            this.query.append('=');
            this.query.append(URLEncoder.encode(var2, "UTF-8"));
        } catch (UnsupportedEncodingException var4) {
            throw new RuntimeException("Broken VM does not support UTF-8");
        }
    }

    public String getQuery() {
        return this.query.toString();
    }
}


public class FormPoster {
    private URL url;
    private QueryString query = new QueryString();

    public FormPoster(URL url) {
        if (!url.getProtocol().toLowerCase().startsWith("http")) {
            throw new IllegalArgumentException("Posting only works for http URLs");
        }
        this.url = url;
    }

    public void add(String name, String value) {
        query.add(name, value);
    }

    public URL getURL() {
        return this.url;
    }

    public InputStream post() throws IOException {
        URLConnection uc = url.openConnection();
        uc.setDoOutput(true);

        try (OutputStreamWriter out =
                new OutputStreamWriter(uc.getOutputStream(), "UTF-8")) {

            out.write(query.getQuery());
            out.write("\r\n");
            out.flush();
        }

        return uc.getInputStream();
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter URL (example: http://example.com): ");
            String inputURL = sc.nextLine();
            URL url = new URL(inputURL);

            System.out.print("Enter your name: ");
            String name = sc.nextLine();

            System.out.print("Enter your email: ");
            String email = sc.nextLine();

            FormPoster poster = new FormPoster(url);
            poster.add("name", name);
            poster.add("email", email);

            try (InputStream in = poster.post()) {
                Reader r = new InputStreamReader(in);
                int c;
                while ((c = r.read()) != -1) {
                    System.out.print((char) c);
                }
                System.out.println();
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }

        sc.close();
    }
}
// Enter URL (example: http://example.com): http://www.cafeaulait.org/books/jnp4/postquery.phtml
// Enter your name: Sudeep Lamichhane
// Enter your email: sudip9845197918@gmail.com