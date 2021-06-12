package helpers;

import commands.Command;

import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Controller {
    CommandReceiver commandReceiver;
    CommandInvoker commandInvoker;
    PackageManager packageManager;
    int port = 8340;

    public Controller() {
        packageManager = new PackageManager(port);
        commandReceiver = new CommandReceiver();
        commandInvoker = new CommandInvoker(commandReceiver);
    }

    public void run(Container container) {
        //Container container;
        String feedBack;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Runnable runnable = () -> readCommand(reader);
        Executor thread = Executors.newCachedThreadPool();
        while (true) {
//            ExecutorService executorService = Executors.newCachedThreadPool();
//            executorService.execute();

            //receive
            //container = packageManager.receiveCommand();

            //execute
            thread.execute(runnable);
            try {
                //send result
                feedBack = commandInvoker.execute((Command) container.getObject());
                packageManager.send(packageManager.getChannel(), new Container(feedBack, container.getAddress()));
            } catch (NullPointerException | InterruptedException e) {
            }
            Thread.currentThread().stop();
        }
    }

    public void readCommand(BufferedReader reader) {
//        try {
//            if (reader.ready())
//                if (reader.readLine().trim().split(" ")[0].equals("save")) System.out.println(commandReceiver.save());
//                else System.out.println("Такой команды нет");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NullPointerException e) {
//            System.out.println("Завершение работы программы...");
//            System.exit(0);
//        }
        while (true) {
            try {
                if (reader.readLine().trim().split(" ")[0].equals("save")) System.out.println(commandReceiver.save());
                else System.out.println("Такой команды нет");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("Завершение работы программы...");
                System.exit(0);
            }
        }
    }

    //Авторизация
    public boolean isAuthorization(Container container) {
        try {
            ServerUser serverUser = (ServerUser) container.getObject();
            boolean isUser = commandReceiver.getDatabaseManager().checkUser(serverUser);

            if (serverUser.getAuthorized()) {
                if (isUser) {
                    packageManager.send(packageManager.getChannel(), new Container(true, container.getAddress()));
                } else {
                    packageManager.send(packageManager.getChannel(), new Container(false, container.getAddress()));
                }
            } else {
                //Если юзера не существует, то его создаем
                if (!isUser) {
                    if (commandReceiver.getDatabaseManager().checkLogin(serverUser)) {
                        commandReceiver.getDatabaseManager().registerUser(serverUser);
                        packageManager.send(packageManager.getChannel(), new Container("Пользователь " + serverUser.getLogin() + " успешно зарегистрирован", container.getAddress()));
                    } else {
                        System.out.print("");
                        packageManager.send(packageManager.getChannel(), new Container("Пользователь  c логином " + serverUser.getLogin() + " уже существует, придумайте другой логин", container.getAddress()));
                    }
                }
                return false;
            }
            return isUser;
        } catch (NullPointerException e) {
            return false;
        }

    }


    public PackageManager getPackageManager() {
        return packageManager;
    }

}
