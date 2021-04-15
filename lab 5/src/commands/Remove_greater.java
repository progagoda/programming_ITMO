package commands;

import collection.Receiver;
import helpers.Messages;

import java.util.Scanner;

/**
 * Класс команды remove_greater
 */
public class Remove_greater extends Command {
    public Remove_greater(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("remove_greater {element} : удалить из коллекции все элементы, превышающие  чем заданный");
    }

    @Override
    public void execute(String[] args) {
        this.execute(args, new Scanner(System.in));
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        if (args.length != 1) {
            Messages.normalMessageOutput("Неправильные аргументы, попробуйте еще раз!");
        } else {
            receiver.removeGreaterElements(scanner);
        }
    }
}

