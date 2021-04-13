package commands;

import helpers.Messages;
import  collection.Receiver;
import java.util.Scanner;

public class Head extends Command{
    public Head(Receiver receiver) {
       super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("head : вывести первый элемент коллекции");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            if(!receiver.getHead()){
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
