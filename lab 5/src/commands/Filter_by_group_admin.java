package commands;

import java.util.Scanner;
import  collection.Receiver;
import helpers.Messages;

public class Filter_by_group_admin extends Command {

    public Filter_by_group_admin(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("filter : вывести элемент коллекции по его groupAdmin");
    }

    @Override
    public void execute(String[] args) {
            if (args.length != 2) {
                Messages.normalMessageOutput("Неправильно введены аргументы, попробуйте еще раз!");
            } else {
                receiver.printFieldDescendingGroupAdmin(args[1]);
            }
        }


    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}

