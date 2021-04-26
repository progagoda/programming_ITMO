package commands;
import collection.Receiver;
import java.util.Scanner;

/**
 * Абстрактный класс для команд
 */
public abstract class Command {
    /**
     * Поле, содержащее ресивер
     */
    public Receiver receiver;

    /**
     * Конструктор.
     * @param receiver - ресивер
     */
    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Печатает, что делает команда
     */
    public abstract void printInfoAboutCommand();


    public abstract void execute(String[] args);

    
    public abstract void execute(String[] args, Scanner scanner);
}
