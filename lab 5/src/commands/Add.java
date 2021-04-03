package commands;

import collection.GeneralColl;
import collection.Receiver;
import helpers.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

/**
 * Класс, реализующий программу insert key, которая добавляет новый элемент с заданным ключом
 */
public class Add extends Command {
    public Add(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("add {element} : добавить новый элемент в коллекцию");
    }

    @Override
    public void execute(String[] args) {
        this.execute(args, new BufferedReader(in));
    }

    @Override
    public void execute(String[] args,BufferedReader scanner) {
        if(args.length == 1){
            if (receiver.addElement(scanner)){
                Messages.normalMessageOutput("Элемент успешно добавлен");
            } else {
                Messages.normalMessageOutput("Что-то пошло не так, либо вы написали end, так что не произошло добавления элемента");
            }
        } else {
            Messages.normalMessageOutput("Неправильно введенные аргументы, просьба написать так: studentCount,name,expelledStudents без пробелов. \n Все через запятую, так будет дальше использоваться");
        }
    }
}
