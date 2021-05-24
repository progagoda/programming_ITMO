package helpers;
import collection.GeneralColl;
import commands.Command;

import java.io.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Controller {
    Connector connector;
    DatagramChannel channel;
    CommandReceiver commandReceiver;
    CommandInvoker commandInvoker;

    public Controller(int port){
        connector = new Connector();
        channel = connector.connect(port);

        try {
            channel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        commandReceiver = new CommandReceiver();
        commandInvoker = new CommandInvoker(commandReceiver);
    }

    public void run(){
        Container container;
        String feedBack;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            container = receiveCommand();
            //execute
            try {
                //send result
                feedBack = commandInvoker.execute((Command) container.getObject());
                send(channel, new Container(feedBack, container.getAddress()));
            } catch (NullPointerException e) {}
            try {
                if (reader.ready())
                    if (reader.readLine().trim().split(" ")[0].equals("save")) System.out.println(commandReceiver.save());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("Завершение работы программы...");
            }
        }
    }

    //Получаем команду с аргументами от клиента
    public Container receiveCommand() {
        return receive(channel, connector.getSocketAddress());
    }

    //Получаем данные от клиента и пихаем в контейнер
    private Container receive(DatagramChannel channel, SocketAddress a) {
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
}
