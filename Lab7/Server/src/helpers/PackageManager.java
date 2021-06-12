package helpers;

import commands.Command;

import java.io.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class PackageManager {
    Connector connector;
    DatagramChannel channel;
    Container container;

    public PackageManager(int port) {
        connector = new Connector();
        channel = connector.connect(port);

        try {
            channel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Получаем команду с аргументами от клиента
    public Container receiveCommand() {
        return receive(channel, connector.getSocketAddress());
    }

    //Получаем данные от клиента и пихаем в контейнер
    public Container receive(DatagramChannel channel, SocketAddress a) {
        byte[] byteArray = new byte[16384];
        try {
            ByteBuffer buffer = ByteBuffer.wrap(byteArray);
            a = channel.receive(buffer);
            byteArray = buffer.array();
            ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
            ObjectInput in = new ObjectInputStream(bis);
            Object o = in.readObject();
            return new Container(o, a);
        } catch (StreamCorruptedException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void send(DatagramChannel channel, Container container) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(container.getObject());
            out.flush();
            byte[] byteArray = bos.toByteArray();
            ByteBuffer buffer = ByteBuffer.wrap(byteArray);
            channel.send(buffer, container.getAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Container getContainer() {
        return container;
    }

    public Container getPackage() {
        this.container = receive(channel, connector.getSocketAddress());
        return this.container;
    }

    public boolean isCommand(Container container) {
        try {
            Command command = (Command) container.getObject();
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public boolean isServerUser(Container container) {
        try {
            ServerUser serverUser = (ServerUser) container.getObject();
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public String getLogin(Container container) {
        return (String) container.getObject();
    }

    public Connector getConnector() {
        return connector;
    }

    public DatagramChannel getChannel() {
        return channel;
    }


}
