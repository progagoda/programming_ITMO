
import helpers.*;


import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        System.out.println("Начало работы сервера:");
        Controller controller = new Controller();
        //Если пришел пакет с авторизацией/регистрацией, то запускаем новый поток для юзера
        //Убиваем поток когда происходит exit/(если пользователь неактивен в течение 3 минут)
        Container container;
        //Runnable runnable = controller::run(container);
        Runnable runnable;
        //TODO user table from database

        while (true) {
            container = controller.getPackageManager().getPackage();
            Container finalContainer = container;

            if (finalContainer != null) {
                runnable = () -> controller.run(finalContainer);
                ;
                Thread thread = new Thread(runnable);

                if (controller.getPackageManager().isServerUser(container)) {
                    if (controller.isAuthorization(container)) {

                    }
                } else if (controller.getPackageManager().isCommand(container)) {
                      thread.start();
                }
            }

            //
//            if (controller.getPackageManager().isServerUser(container)){
//                if (controller.isAuthorization(container)) {
//                    new Session(runnable, ((ServerUser)container.getObject()).getLogin()).start();
//                }
//            } else if (controller.getPackageManager().isCommand(container)){
//
//            }


        }
    }

    public static int askPort() {
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
            System.exit(0);
        }
        return port;
    }


}

