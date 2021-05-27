package helpers;

import commands.Command;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.Iterator;

public class Controller {
    Connector connector;
    DatagramChannel channel;
    CommandReceiver commandReceiver;
    CommandInvoker commandInvoker;
     int port1 = 8340;
    static int BUF_SZ = 1024;
    class Con {
        ByteBuffer req;
        ByteBuffer resp;
        SocketAddress sa;

        public Con() {
            req = ByteBuffer.allocate(BUF_SZ);
        }
    }


    public Controller(){
          String message="Cервер включен";
        connector= new Connector();
        channel= connector.connect(port1);
      //  send(channel,new Container(message, connector.getSocketAddress()));//вылетает ошибка
        try {
            Selector selector = Selector.open();
            DatagramChannel channel = DatagramChannel.open();
            InetSocketAddress isa = new InetSocketAddress(port1);
            channel.socket().bind(isa);
            channel.configureBlocking(false);
            SelectionKey clientKey = channel.register(selector, SelectionKey.OP_READ);
            clientKey.attach(new Connect());
            while (true) {
                try {
                    selector.select();
                    Iterator selectedKeys = selector.selectedKeys().iterator();
                    while (selectedKeys.hasNext()) {
                        try {
                            SelectionKey key = (SelectionKey) selectedKeys.next();
                            selectedKeys.remove();

                            if (!key.isValid()) {
                                continue;
                            }

                            if (key.isReadable()) {
                                read(key);
                                key.interestOps(SelectionKey.OP_WRITE);
                            } else if (key.isWritable()) {
                                write(key);
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        } catch (IOException e) {
                            System.err.println("Ошибка" + (e.getMessage() != null ? e.getMessage() : ""));
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка " + (e.getMessage() != null ? e.getMessage() : ""));
                }
            }
        } catch (IOException e) {
        }
        //
        commandReceiver = new CommandReceiver();
        commandInvoker = new CommandInvoker(commandReceiver);
    }

    private void read(SelectionKey key) throws IOException {
        DatagramChannel chan = (DatagramChannel)key.channel();
        Con con = (Con)key.attachment();
        con.sa = chan.receive(con.req);
        System.out.println(new String(con.req.array(), "UTF-8"));
        con.resp = Charset.forName( "UTF-8" ).newEncoder().encode(CharBuffer.wrap("send the same string"));
    }

    private void write(SelectionKey key) throws IOException {
        DatagramChannel chan = (DatagramChannel)key.channel();
        Con con = (Con)key.attachment();
        chan.send(con.resp, con.sa);
    }
    class Connect {
        ByteBuffer request;
        ByteBuffer response;
        SocketAddress socketAddress;

        public Connect() {
            request = ByteBuffer.allocate(65535);
        }
    }
    public void run(){
        Container container;
        String feedBack;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            container = receiveCommand();
            //execute
            try {
                //send result
                feedBack = commandInvoker.execute((Command) container.getObject());
                send(channel, new Container(feedBack, container.getAddress()));
            } catch (NullPointerException e) {
            }
            catch (ClassCastException e){
                answer(channel,"I'm ready");
            }
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
    public void answer(DatagramChannel channel, String message){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(message);
            out.flush();
            byte[] byteArray = bos.toByteArray();
            ByteBuffer buffer = ByteBuffer.wrap(byteArray);
            channel.send(buffer, connector.getSocketAddress());
        } catch (Exception e) {
        }
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
