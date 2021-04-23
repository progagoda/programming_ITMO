package helpers;

import collection.GeneralColl;
import collection.Invoker;
import collection.Receiver;
import commands.*;

import java.util.Scanner;

/**
 * Этот класс необходим для запуска основной работы программы,
 * здесь задаются команды для Invoker,
 * идет считывание json файла и запись структуры в коллекцию
 * запускает LineReader для чтения данных из командной строки
 */
public class ProgramStarter {

    private final GeneralColl collectionManager;
    private final Invoker invoker;
    private final Receiver receiver;

    public ProgramStarter(GeneralColl collectionManager) {
        this.collectionManager = collectionManager;
        this.invoker = new Invoker();
        this.receiver = new Receiver(this.collectionManager, invoker);
    }

    /**
     * Идет запуск основых функций программы
     */
    public void start() {
        registerAllCommands();
        addAllFGroupsFromJson();
        LineReader lineReader = new LineReader();
        lineReader.readLine(new Scanner(System.in), invoker);
    }

    /**
     * Считывает все данные из Json файла и записывает их в коллекцию
     */
    public void addAllFGroupsFromJson() {
        String myfile = "StudyGroup.json";
        FileManager jsonParser = new FileManager(myfile);
        jsonParser.readCollection();

    }

    /**
     * Регестрирует все команды для Invoker
     */
    public void registerAllCommands() {
        invoker.registerNewCommand("show", new Show(receiver));
        invoker.registerNewCommand("add", new Add(receiver));
        invoker.registerNewCommand("help", new Help(receiver));
        invoker.registerNewCommand("exit", new Exit(receiver));
        invoker.registerNewCommand("clear", new Clear(receiver));
        invoker.registerNewCommand("head", new Head(receiver));
        invoker.registerNewCommand("info", new Info(receiver));
        invoker.registerNewCommand("save", new Save(receiver));
        invoker.registerNewCommand("update", new UpdateId(receiver));
        invoker.registerNewCommand("remove_by_id", new Remove_by_id(receiver));
        invoker.registerNewCommand("execute_script", new ExecuteScript(receiver));
        invoker.registerNewCommand("min_by_id", new Min_by_id(receiver));
        invoker.registerNewCommand("remove_greater", new Remove_greater(receiver));
        invoker.registerNewCommand("filter_by_group_admin", new Filter_by_group_admin(receiver));
        invoker.registerNewCommand("add_if_min", new Add_if_min(receiver));
       /*
        invoker.registerNewCommand("count_less_than_number_of_rooms", new CountLessCommand(receiver));
        invoker.registerNewCommand("print_field_descending_number_of_rooms", new PrintFieldNumberOfRoomsCommand(receiver));
        invoker.registerNewCommand("remove_first", new RemoveFirstCommand(receiver));
        **/

    }
}
