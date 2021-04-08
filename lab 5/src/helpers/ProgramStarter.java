package helpers;

import collection.GeneralColl;
import collection.Invoker;
import collection.Receiver;
import general.StudyGroup;
import  commands.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
        * Этот класс необходим для запуска основной работы программы,
        * здесь задаются команды для Invoker,
        * идет считывание csv файла и запись структуры в коллекцию
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
        addAllFlatsFromCSV();
        LineReader lineReader = new LineReader();
        lineReader.readLine(new Scanner(System.in), invoker);
    }

    /**
     * Считывает все данные из CVS файла и записывает их в коллекцию
     */
    public void addAllFlatsFromCSV() {
        JsonParser jsonParser = new JsonParser();
        CSVFileReader csvFileReader = new CSVFileReader();
        ArrayList<String> listOfLines;
        if ((listOfLines = csvFileReader.readAllLines(collectionManager.getFile())) != null) {
            for (int i = 0; i < listOfLines.size(); i++) {
                StudyGroup group;
                String[] args = csvParser.parseLineToArray(listOfLines.get(i));
                if (args != null) {
                    if ((group = csvParser.parseArrayToFlat(args)) != null) {
                        collectionManager.getCollection().add(group);
                    } else {
                        Messages.normalMessageOutput("Программа не смогла считать " + i + " строку, ошибка в формате самих полей, пропускам ее.");
                    }
                } else {
                    Messages.normalMessageOutput("Ошибка в строке " + i + ", неправильно составлена CSV таблица, что-то не так с кавычками, пропуск строки");
                }
            }
            Messages.normalMessageOutput("Запись данных из файла закончилась");
        }

    }

    /**
     * Регестрирует все команды для Invoker
     */
    public void registerAllCommands() {
        invoker.registerNewCommand("add", new Add(receiver));
       /* invoker.registerNewCommand("clear", new ClearCommand(receiver));
        invoker.registerNewCommand("count_less_than_number_of_rooms", new CountLessCommand(receiver));
        invoker.registerNewCommand("execute_script", new ExecuteScriptCommand(receiver));
        invoker.registerNewCommand("exit", new ExitCommand(receiver));
        invoker.registerNewCommand("head", new HeadCommand(receiver));
        invoker.registerNewCommand("help", new HelpCommand(receiver));
        invoker.registerNewCommand("info", new InfoCommand(receiver));
        invoker.registerNewCommand("min_by_coordinates", new MinByCoordinatesCommand(receiver));
        invoker.registerNewCommand("print_field_descending_number_of_rooms", new PrintFieldNumberOfRoomsCommand(receiver));
        invoker.registerNewCommand("remove_by_id", new RemoveByIdCommand(receiver));
        invoker.registerNewCommand("remove_first", new RemoveFirstCommand(receiver));
        invoker.registerNewCommand("remove_lower", new RemoveLowerCommand(receiver));
        invoker.registerNewCommand("save", new SaveCommand(receiver));
        invoker.registerNewCommand("show", new ShowCommand(receiver));
        invoker.registerNewCommand("update", new UpdateByIdCommand(receiver));
        **/

    }
}
