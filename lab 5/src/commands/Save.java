package commands;
import helpers.Messages;
import collection.Receiver;
import java.util.Scanner;
/**
 * Класс, реализующий программу save, которая сохраняет коллекцию в файл
 */
public class Save extends Command {
    public Save(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("save : сохранить коллекцию в файл");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 1){
            if(receiver.saveCollection()){
                Messages.normalMessageOutput("Сохранение произошло, возрадуйся смертный");
            } else {
                Messages.normalMessageOutput("В коллекции нет элементов, какой тут что-то сохранять, сначала добавь что-то");
            }
        } else {
            Messages.normalMessageOutput("Неправильный ввод агрументов, попробуйте еще раз");
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}