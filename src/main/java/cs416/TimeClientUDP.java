package cs416;

import java.io.IOException;
import java.net.*;

public class TimeClientUDP {
    private static final int PORT_NUM = 37;
    private final DatagramSocket socket;
    private final InetAddress serverIP;

    public TimeClientUDP() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        serverIP = InetAddress.getByName("time.nist.gov");
    }

    public static void main(String[] args) {
        try {
            TimeClientUDP client = new TimeClientUDP();
            byte[] receivedData = client.queryTime();
            System.out.println(new String(receivedData));
        }
        catch(IOException error) {
            error.printStackTrace();
        }
    }

    private byte[] queryTime() throws IOException {
        DatagramPacket timeRequest = new DatagramPacket(new byte[1024], 1024, serverIP, PORT_NUM);
        socket.send(timeRequest);
        DatagramPacket reply = new DatagramPacket(new byte[1024], 1024);
        socket.receive(reply);
        return reply.getData();
    }
}
