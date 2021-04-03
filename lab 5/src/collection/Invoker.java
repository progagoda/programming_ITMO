package collection;

import commands.Command;
import helpers.Messages;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Исполнитель всех команд
 */
public class Invoker{
    /**
     * Поле, содержащее ключ - имя команды и значение - объект команды
     */
    private final HashMap<String, Command> hashMap = new HashMap<>();

    /**
     * Выполнить команду по ключу - названию команды
     *  @param scanner - сканер, которые считывает данные из командной строки / файла
     * @param args - аргументы, полученные при помощи сканнера
     */
    public void executeCommand(BufferedInputStream scanner, String[] args) {
        if (args.length > 0) {
            Command command = hashMap.get(args[0]);
            if (command == null) {
                Messages.normalMessageOutput("Неправильный ввод команды, попробуйте написать help и посмотреть доступные команды");
            } else {
                if(scanner.equals(new Scanner(System.in))){
                    command.execute(args);
                } else {
                    command.execute(args, scanner);
                }
            }
        }

    }

    /**
     * Добавить новую команду к списку имеющихся
     *
     * @param commandName имя команды
     * @param command объект данной команды
     */
    public void registerNewCommand(String commandName, Command command) {
        hashMap.put(commandName, command);
    }

    /**
     * Геттер коллекции команд
     * @return возвращает коллекцию команд
     */
    public HashMap<String, Command> getHashMap() {
        return hashMap;
    }

}


