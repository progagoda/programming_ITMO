import collection.GeneralColl;
import helpers.CommandReader;
import helpers.FileManager;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args)  throws IOException {
        Scanner consoleScanner = new Scanner(System.in);
        //String address = "127.0.0.1";
        int port = 0;
        String input;
        try {
            //System.out.println("Введите адресс:");
            //address = consoleScanner.nextLine();
            System.out.print("Введите порт: ");
            input = consoleScanner.nextLine();
            try {
                port = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Неправильный порт");
                System.exit(1);
            }
            SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName("localhost"), port);
            DatagramSocket clientSocket = new DatagramSocket();
            System.out.println("Начало работы программы:");
            while (true) {
                try {
                    System.out.print("Введите команду: ");
                    String inputCommand = consoleScanner.nextLine().trim();
                    if (input.isEmpty()) {
                        continue;
                    }
                    CommandReader commandReader = new CommandReader();
                    commandReader.parseCommand(inputCommand.split(" "), clientSocket, socketAddress, consoleScanner);
                } catch (NoSuchElementException e) {
                    System.out.println("Завершение программы...");
                    System.exit(1);
                }
            }
        } catch (IOException e) {}
    }
}







