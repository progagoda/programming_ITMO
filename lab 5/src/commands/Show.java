package commands;
import collection.Receiver;
import helpers.Messages;

import java.util.Scanner;

/**
 * Класс, реализующий программу show, которая выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class Show extends Command {
    public Show(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 1){
            if(!receiver.printAllElements()){
                Messages.normalMessageOutput("В коллекции нет элементов, вывод недоступен");
            }
        } else {
            Messages.normalMessageOutput("Непавильны ввод агрументов, попробуйте еще раз");
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}
