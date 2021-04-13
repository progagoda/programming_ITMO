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

        try {
            if (args.length == 1) {
                String admin = args[1];
                if (!receiver.printFieldDescendingGroupAdmin(admin)) {
                    Messages.normalMessageOutput("В коллекции нет эллементов, так что вывод пуст");
                }
            } else {
                Messages.normalMessageOutput("Неправильный ввод агрументов, попробуйте еще раз");
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            Messages.normalMessageOutput("Нужно ввести аргумент - имя админа группы ");
        }
        }


    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}

