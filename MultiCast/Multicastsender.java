package MultiCast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Multicastsender {
    public static void main(String[] args) {
        InetAddress in =null;
        int port=0;
        byte ttl = (byte) 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            in = InetAddress.getByName("224.2.2.1");
            port = 3333;
            ttl=64;

    }catch(NumberFormatException | IndexOutOfBoundsException | UnknownHostException ex)
    {
        System.err.println(ex);
        System.err.println("Usage: Java MultiCast Sender multicast_address port ttl");
        System.exit(1);
    }

    try(MulticastSocket ms = new MulticastSocket()){
        ms.setTimeToLive(ttl);
        ms.joinGroup(in);

        for(int i= 1;i<10;i++)
        {
            byte[] data = br.readLine().getBytes();
            DatagramPacket dp = new DatagramPacket(data,data.length,in,port);
            ms.send(dp);
        }
     ms.leaveGroup(in);
    }catch(SocketException ex)
    {
        System.err.println(ex);
    }catch(IOException ex)
    {
        System.err.println(ex);
    }
}
}