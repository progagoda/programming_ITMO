package commands;

import general.GeneralCollection;

import java.io.IOException;
import java.util.Collection;
/**
 * Класс, реализующий программу insert key, которая добавляет новый элемент с заданным ключом
 */
public class Add implements CommandDo {
    Collection collection;
    /**
     * Конструктор по умолчанию, который добавляет объект класса команды в коллекцию команд
     */
    public Add(){StartCommand.addCommand("add",this);}
    /**
     * Сначала идет проверка на наличие элмента в коллекции {@link GeneralCollection#getGenCollection()} c таким ключом, если есть то предлагается выбор заменить элемент коллекции или нет. Если элемента с таким ключом нету то вызывается метод {@link GeneralCollection#addStudyGroup(int)}
     * @param name строковое значение, аргумент команды (ключ)
     * @param generalCollection класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(String name, GeneralCollection generalCollection) {// сделать проверку, нет ли уже такого ключа в коллекции
        try {
            Integer ret = Integer.parseInt(name);
            boolean s = true;
            if (generalCollection.getGenCollection().get(ret) != null) {
                while (s) {
                    System.out.println("Элемнет с данным ключом уже есть в коллекции, хотите заменить эелемент коллекции? yes/no ");
                    String tip = generalCollection.scanLine();
                    if (tip.equals("yes")) {
                        s = false;
                        generalCollection.addStudyGroup(ret);
                        System.out.println("Объект с ключем: " + ret + " добавлен в коллецию");
                    } else if (tip.equals("no")) {
                        s = false;
                        break;
                    } else {
                        System.out.println("Вы ввели некорректное значение");
                    }
                }
            } else {
                generalCollection.addStudyGroup(ret);
                System.out.println("Объект с ключем: " + ret + " добавлен в коллецию");
            }
        } catch (NumberFormatException| IOException e) {
            System.out.println("Ключ должен быть типа целым числом");
        }
    }
}
