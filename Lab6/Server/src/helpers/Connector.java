package helpers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class Connector {
    SocketAddress socketAddress;

    public DatagramChannel connect(int port) {
        try {
            socketAddress = new InetSocketAddress(port);
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.bind(socketAddress);
            return datagramChannel;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }
}
