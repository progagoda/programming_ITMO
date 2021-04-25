package collection;

import helpers.LineReader;
import helpers.Messages;
import exceptions.Sript_recurse_exception;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
     * Логика для add_if_min
     */
    public boolean add_if_min(Scanner scanner) {
        return  collectionManager.add_if_min(scanner);
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
     * Логика для print_unique_semester
     */
    public boolean print_unique_semester() {
        return collectionManager.printElementbySemester();
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
    private List<String> scriptFileNames = new ArrayList<String>();
    public boolean executeScript(String args) {
        LineReader lineReader = new LineReader();
        String[] userCommand = {"", ""};
        scriptFileNames.add(args);// довалили это имя скрипта в List
        File file = null;
        try {
            file = new File(args);
            if (!file.exists() || !file.canRead() || !file.canWrite()) {
                throw new IllegalAccessError();
            }
            try {
                Scanner scanner2 = new Scanner(file);//считываем скрипт
                do{
                userCommand = (scanner2.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                    if (userCommand[0].equals("execute_script")) {//если наша команда равна execute_script
                        for (String name : scriptFileNames) {
                            if (name.equals(userCommand[1])) throw new Sript_recurse_exception();
                        }
                    }
                }
                while (scanner2.hasNext());
            }
            catch (Sript_recurse_exception e){
                Messages.normalMessageOutput("\u001B[37m" + "\u001B[33m" + " Здесь рекурсия запрещена \n удалите из вашего скрипта строчку execute_script имя_того_же_скрипта" + "\u001B[33m" + "\u001B[37m");
                return false;
            }
            lineReader.readLine(new Scanner(file), invoker);//выполнятор всего скрипта
        } catch (IllegalAccessError | FileNotFoundException e) {
            Messages.normalMessageOutput("Невозможно работать с данным файлом, попробуйте еще раз");
            return false;
        } catch (StackOverflowError | OutOfMemoryError e) {
            Messages.normalMessageOutput("ЭЭЭЭЭ, куда, рекурся зло, вышел и зашел обратно!");
            return false;
        }
        Messages.normalMessageOutput("Выполнение скрипта закончено");
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
