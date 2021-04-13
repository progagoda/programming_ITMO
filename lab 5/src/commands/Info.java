package commands;
import collection.Receiver;
import helpers.Messages;
import java.util.Scanner;

public class Info extends Command{
    public Info(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void printInfoAboutCommand() {
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }

    @Override
    public void execute(String[] args) {
        if(args.length == 1){
            receiver.getInfoAboutCollection();
        } else {
            Messages.normalMessageOutput("Неправильный ввод аргументов для данной команды");
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        this.execute(args);
    }
}