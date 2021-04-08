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

    /**
     * Испольнить команду только с аргументами
     * @param args аргументы, считываемые из командной строки
     */
    public abstract void execute(String[] args);

    /**
     * Испольнить команду с аргументами args и сканнером, для считывания данных из фала
     * @param args - аргументы для команды
     * @param scanner - сканнер
     */
    public abstract void execute(String[] args, Scanner scanner);
}
