package commands;

import collection.GeneralColl;
import collection.Receiver;
import helpers.Messages;

import java.util.Scanner;

public class Help extends  Command{
    public Help(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("help : вывести справку по доступным командам");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 1) {
            System.out.println();
            receiver.getInfoAboutAllCommands();
            System.out.println();
        } else {
            Messages.normalMessageOutput("Неправильный ввод аргументов команды help, попробуйте еще раз");
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}




