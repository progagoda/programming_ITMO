
import helpers.Connector;
import helpers.Container;
import helpers.Controller;

import java.net.BindException;
import java.nio.channels.DatagramChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {
        public static void main(String[] args) {
            System.out.println("Начало работы сервера:");
            Controller controller = new Controller();
            controller.run();
        }

        public static int askPort(){
            Scanner consoleScanner = new Scanner(System.in);
            int port = 0;
            String input;
            System.out.print("Введите порт: ");
            try {
                input = consoleScanner.nextLine();
                port = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Неправильный порт");
                System.exit(1);
            } catch (NoSuchElementException e) {
                System.out.println("Завершение работы сервера...");
                System.exit(1);
            }
            return port;
        }


    }

