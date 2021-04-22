package commands;


import collection.Receiver;
import helpers.Messages;
import java.util.Scanner;

public class Add_if_min extends Command {
    public Add_if_min(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("add_if_min {element} : добавить в коллекции элемент если он наименьший в колекции");
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
            receiver.add_if_min(scanner);
        }
    }
}
