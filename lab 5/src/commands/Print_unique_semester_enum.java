package commands;

import collection.Receiver;
import helpers.Messages;

import java.util.Scanner;

public class Print_unique_semester_enum  extends Command {
    public Print_unique_semester_enum(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("head : вывести первый элемент коллекции");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            if (!receiver.print_unique_semester()) {
                Messages.normalMessageOutput("Так коллекция пустая, куда тебе что-то выводить!");
            }
        } else {
            Messages.normalMessageOutput("Неправильный ввод аргументов, жду по новому все");
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}