import java.net.*;
import java.text.*;
import java.util.Date;
import java.io.*;

public class Daytime {

    public Date getDateFromNetwork() throws IOException, ParseException {

        try (Socket socket = new Socket("time.nist.gov", 13)) {
            socket.setSoTimeout(15000);

            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, "ASCII");

            StringBuilder time = new StringBuilder();
            int c;

            while ((c = reader.read()) != -1) {
                time.append((char) c);
            }

            return parseDate(time.toString());
        }
    }

    static Date parseDate(String s) throws ParseException {

        // Example from NIST:
        // "58923 25-02-15 05:20:44 50 0 0 123.4 UTC(NIST) *"

        String[] pieces = s.split(" ");

        String dateTime = pieces[1] + " " + pieces[2] + " UTC";
        DateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss z");

        return format.parse(dateTime);
    }

    public static void main(String[] args) {
        Daytime dt = new Daytime();
        try {
            Date date = dt.getDateFromNetwork();
            System.out.println("Network Time: " + date);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
