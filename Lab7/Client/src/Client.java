import commands.CheckServer;
import helpers.CommandReader;
import helpers.ServerUser;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
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
                CommandReader commandReader1 = new CommandReader();
                commandReader1.send(new CheckServer(), clientSocket, socketAddress);
                commandReader1.checkFeedback(clientSocket);
            ServerUser serverUser = new ServerUser();
            CommandReader commandReader = new CommandReader();
            String answer;
                while(true){
                    //Хотите авторизоваться
                    System.out.println("Вы хотите авторизоваться? (y/n)");

                    answer = consoleScanner.nextLine().trim();

                    if (answer.equals("y")){
                        //Авторизация
                        System.out.println("Запущен процесс авторизации ...");
                        serverUser.setAuthorized(true);

                        try {
                            System.out.print("Введите логин: ");
                            serverUser.setLogin(consoleScanner.nextLine().trim());

                            if (serverUser.getLogin().equals("")){
                                throw new NullPointerException();
                            }

                            System.out.print("Введите пароль: ");
                            serverUser.setPassword(consoleScanner.nextLine().trim());
                        } catch (NoSuchElementException e) {
                            System.out.println("Завершение работы...");
                            System.exit(0);
                        } catch (NullPointerException e){
                            System.out.println("Пустой логин - плохо!");
                            continue;
                        }

                        //Проверка существования пользователя на сервере
                        if (commandReader.checkServerUser(serverUser, clientSocket, socketAddress)){
                            break;
                        }
                        System.out.println("Что-то пошло не так. Попробуйте снова");
                    } else if (answer.equals("n")) {
                        //Хотите зарегистироваться
                        System.out.println("Вы хотите зарегистрироваться? (y/n)");
                        answer = consoleScanner.nextLine().trim();
                        if (answer.equals("y")){
                            //Регистрация
                            System.out.println("Запущен процесс регистрации ...");
                            serverUser.setAuthorized(false);
                            serverUser.setId(Math.abs(new Random().nextInt()));
                            try {
                                System.out.print("Введите логин: ");
                                serverUser.setLogin(consoleScanner.nextLine());

                                if (serverUser.getLogin().equals("")){
                                    throw new NullPointerException();
                                }

                                System.out.print("Введите пароль: ");
                                serverUser.setPassword(consoleScanner.nextLine());
                            } catch(NoSuchElementException e){
                                System.out.println("Завершение работы...");
                                System.exit(0);
                            } catch(NullPointerException e){
                                System.out.println("Пустой логин - плохо!");
                                continue;
                            }
                            commandReader.registerServerUser(serverUser, clientSocket, socketAddress);
                            commandReader.receive(clientSocket);
                            continue;
                        } else if (answer.equals("n")){
                            continue;
                        }

                    }
                }

                System.out.println("Добро пожаловать " + serverUser.getLogin());

            while (true) {
                try {
                    System.out.print("Введите команду: ");
                    String inputCommand = consoleScanner.nextLine().trim();
                    if (input.isEmpty()) {
                        continue;
                    }
                    commandReader.parseCommand(inputCommand.split(" "), clientSocket, socketAddress, consoleScanner,serverUser.getLogin());
                } catch (NoSuchElementException e) {
                    System.out.println("Завершение программы...");
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            System.out.println("Беда");
        }
    }
}







