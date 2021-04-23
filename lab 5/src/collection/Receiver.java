package collection;

import helpers.LineReader;
import helpers.Messages;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Receiver {
    private final GeneralColl collectionManager;
    private final Invoker invoker;

    /**
     * Класс ресивер (получатель), в котором описана логика некоторых команд
     */
    public Receiver(GeneralColl collectionManager, Invoker invoker) {
        this.collectionManager = collectionManager;
        this.invoker = invoker;
    }

    /**
     * Реализация команды save
     */
    public boolean saveCollection() {
        if (collectionManager.getCollection().size() > 0) {
            collectionManager.saveToFile();
            return true;
        } else {
            return false;
        }
    }

    /**
     * выводит информацию о всех командах
     */
    public void getInfoAboutAllCommands() {
        invoker.getHashMap().forEach((name, command) -> command.printInfoAboutCommand());
    }

    /**
     * Логика для info
     */
    public void getInfoAboutCollection() {
        collectionManager.printInfoAboutCollection();
    }

    /**
     * Логика для exit
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Логика для clear
     */
    public boolean clear() {
        return collectionManager.clearCollection();
    }

    /**
     * Логика для head
     */
    public boolean getHead() {
        return collectionManager.getHeadOfCollection();
    }

    /**
     * Логика для show
     */
    public boolean printAllElements() {
        return collectionManager.printAllElements();
    }

    /**
     * Логика для execute_script
     */
    public boolean executeScript(String args) {
        LineReader lineReader = new LineReader();
        File file = null;
        try {
            file = new File(args);
            if (!file.exists() || !file.canRead() || !file.canWrite()) {
                throw new IllegalAccessError();
            }
            lineReader.readLine(new Scanner(file), invoker);
        } catch (IllegalAccessError | FileNotFoundException e) {
            Messages.normalMessageOutput("Невозможно работать с данным файлом, попробуйте еще раз");
            return false;
        } catch (StackOverflowError | OutOfMemoryError e) {
            Messages.normalMessageOutput("ЭЭЭЭЭ, куда, рекурся зло, вышел и зашел обратно!");
            return false;
        }
        Messages.normalMessageOutput("Закончилось выполнение скрипта из файла");
        return true;
    }
//    /**
//     * Логика для print_field_descending_number_of_rooms
//     */
//    public boolean printFieldDescendingSemester() {
//        return collectionManager.printElementbySemester();
//    }
    /**
     * Логика для print_field_descending_number_of_rooms
     */
    public boolean printFieldDescendingGroupAdmin(String groupAdmin) {
        return collectionManager.printElementbyGroupAdmin(groupAdmin);
    }
    /**
     * Логика для update
     */
    public boolean updateElementById(Long id, Scanner scanner) {
        return collectionManager.updateElement(id, scanner);
    }
    /**
     * Логика для min_by_coordinates
     */
    public boolean printElementWithMinId() {
        return collectionManager.findElementWithMinId();
    }

    /**
     * Логика для remove_lower
     */
    public boolean removeLowerElements(Scanner scanner) {
        return collectionManager.removeGreatest(scanner);
    }

    /**
     * Логика для remove_by_id
     */
    public boolean removeById(Long id) {
        return collectionManager.removeElementById(id);
    }

    /**
     * Логика для add
     */
    public boolean addElement(Scanner scanner) {
        return collectionManager.addElement(scanner);
    }
}
