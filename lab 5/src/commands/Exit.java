package commands;

import collection.Receiver;
import helpers.Messages;

import java.util.Scanner;

/**
 * Класс команды exit
 */
public class Exit extends Command {

    public Exit(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("exit : завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 1) {
            Messages.normalMessageOutput("Выходим из программы, спасибо, что ВЫ есть");
            receiver.exit();
        } else {
            Messages.normalMessageOutput("Ошибка ввода аргументов, попробуйте еще раз");
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}

