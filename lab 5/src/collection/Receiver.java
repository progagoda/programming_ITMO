package collection;

import java.util.Scanner;

public class Receiver {
    private final GeneralColl collectionManager;
    private final Invoker invoker;

    /**
     * Конструктор
     * @param collectionManager добалвяет менеджер коллекции
     * @param invoker добавляет исполнителя
     */
    public Receiver(GeneralColl collectionManager, Invoker invoker) {
        this.collectionManager = collectionManager;
        this.invoker = invoker;
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
     * Логика для add
     */
    public boolean addElement(Scanner scanner){
        return collectionManager.addElement(scanner);
    }}
