package commands;

import collection.GeneralColl;

import java.io.IOException;
import java.util.Collection;
/**
 * Класс, реализующий программу insert name, которая добавляет новый элемент с заданным заголовком
 */
public class Add implements CommandDo {
    Collection collection;
    /**
     * Конструктор по умолчанию, который добавляет объект класса команды в коллекцию команд
     */
    public Add(){StartCommand.addCommand("add",this);}
    /**
     * Сначала идет проверка на наличие элмента в коллекции {@link GeneralColl#getGeneralColl()} c таким ключом, если есть то предлагается выбор заменить элемент коллекции или нет. Если элемента с таким ключом нету то вызывается метод {@link GeneralColl#addStudyGroup(int)}
     * @param key строковое значение, аргумент команды (ключ)
     * @param genCollection класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(String key, GeneralColl genCollection) {// сделать проверку, нет ли уже такого ключа в коллекции
        try {
            Integer ret = Integer.parseInt(key);
            boolean s = true;
            if (genCollection.getGeneralColl()!= null) {
                while (s) {
                    System.out.println("Элемeнт с данным ключом уже есть в коллекции, хотите заменить эелемент коллекции? yes/no ");
                    String tip = genCollection.scanLine();
                    if (tip.equals("yes")) {
                        s = false;
                        genCollection.addStudyGroup(ret);
                        System.out.println("Объект с ключем: " + ret + " добавлен в коллецию");
                    } else if (tip.equals("no")) {
                        s = false;
                        break;
                    } else {
                        System.out.println("Вы ввели некорректное значение");
                    }
                }
            } else {
                genCollection.addStudyGroup(ret);
                System.out.println("Объект с ключем: " + ret + " добавлен в коллецию");
            }
        } catch (NumberFormatException| IOException e) {
            System.out.println("Ключ должен быть типа целым числом");
        }
    }


}