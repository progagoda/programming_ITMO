package commands;

import collection.Receiver;
import helpers.Messages;

import java.util.Scanner;

public class Min_by_id  extends Command{
    public Min_by_id(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("min_by_id : вывести любой объект из коллекции, значение поля id которого является минимальным");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 1){
            if(!receiver.printElementWithMinId()){
                Messages.normalMessageOutput("В коллекции нет эллементов, нечего выводить!");
            }
        } else {
            Messages.normalMessageOutput("Неправильные аргументы комманды, попробуйте еще раз");
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}

