package commands;

import collection.GeneralColl;
import collection.Receiver;
import helpers.Messages;

import java.util.Collection;
import java.util.Scanner;

public class Add_if_min extends Command  {
    public Add_if_min(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("add_if_min : добавить новый элемент если он минимальный");
    }

    @Override
    public void execute(String[] args) {
        this.execute(args, new Scanner(System.in));
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        if(args.length == 1){
            if (receiver.add_if_min(scanner)){
                Messages.normalMessageOutput("Элемент успешно добавлен");
            } else {
                Messages.normalMessageOutput("Что-то пошло не так, либо вы написали end, так что не произошло добавления элемента");
            }
        } else {
            Messages.normalMessageOutput("Неправильно введенные аргументы, просьба написать так: studentCount,name,expelledStudents без пробелов. \n Все через запятую, так будет дальше использоваться");
        }
    }
}

