import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class QueryString {
    private StringBuilder query = new StringBuilder();

    public synchronized void add(String name, String value) {
        query.append('&');
        encode(name, value);
    }

    private synchronized void encode(String name, String value) {
        try {
            query.append(URLEncoder.encode(name, "UTF-8"));
            query.append('=');
            query.append(URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Broken VM does not support UTF-8");
        }
    }

    public String getQuery() {
        return query.toString();
    }
}

public class QueryTest {
    public static void main(String[] args) {
        QueryString qs = new QueryString();

        qs.add("name", "Sudeep");
        qs.add("lang", "Java&Programming");

        System.out.println(qs.getQuery());
    }
}
