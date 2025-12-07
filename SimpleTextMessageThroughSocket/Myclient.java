import java.net.*;
import java.io.*;

class Myclient {
    public static void main(String[] args) throws IOException, Exception {
        Socket s = new Socket("localhost", 3333);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = "", str2 = "";
        while (!str1.equals("stop")) {
            str2 = br.readLine();
            dout.writeUTF(str2);
            dout.flush();
            str1 = din.readUTF();
            System.out.println("Server says : " + str1);
        }
        din.close();
        dout.close();
        s.close();
    }
}