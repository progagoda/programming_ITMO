package general;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.*;
/**
 * Класс для дерева с объектами StudyGroup и его управлением
 */
public class GeneralCollection {
    /**Поле genCollection, ключи - Integer, значения - HumanBeing*/
    private TreeMap<Integer, StudyGroup> genCollection = new TreeMap<>();
    /**
     * Чтение данных из файла HumanBeing.json генерация id происходит автоматически в диапазоне от 0 до 10000
     * @throws IOException ошибка пользовательского ввода
     */
    public GeneralCollection() throws IOException {
        Gson gson = new Gson();
        try (BufferedReader reader = new BufferedReader(new FileReader("StudyGroup.json"))) {
            Type foundMap = new TypeToken<TreeMap<Integer, StudyGroup>>(){}.getType();
            genCollection = gson.fromJson(reader, foundMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Выводит справку по доступным командам
     */
    public static void help() {
        System.out.println("help : вывести справку по доступным командам \n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) \n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении \n" +
                "add {element : добавить новый элемент с заданным ключём \n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному \n" +
                "remove_by_id id : удалить элемент из коллекции по его id \n" +
                "clear : очистить коллекцию \n" +
                "save : сохранить коллекцию в файл \n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. \n" +
                "min_by_id : вывести любой объект из коллекции, значение поля id которого является минимальным \n" +
                "head: вывести первый элемент коллекции \n" +
                "add_if_min {element : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции \n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный \n" +
                "filter_by_group_admin groupAdmin : вывести элементы, значение поля groupAdmin которых равно заданному \n" +
                "print_unique_semester_enum : вывести уникальные значения поля semesterEnum всех элементов в коллекции \n" +
                "exit : завершить программу (без сохранения в файл) \n");

    }
    /**
     * Выводит информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     */
    public  void info(){System.out.println("Тип: StudyGroup\n"
            + "Дата инициализации: " + genCollection.get(0).getCreationDate().now() + '\n'
            + "Количество элементов: " + genCollection.size());}
    /**
     * Выводит все элементы коллекции в строковом представлении
     */
    public  void show(){
        if (!genCollection.isEmpty()) {
            for (Integer key : genCollection.keySet()) {
                System.out.println("Key: " + key + " Value: " + genCollection.get(key).toString());
            }
        } else {
            System.out.println("Коллекция пуста.");
        }
    }
    /**
     * Добавляет новый элемент в коллекцию
     * @param key Ключ
     */
    public  void add(int key){
        StudyGroup humanBeing1 = new StudyGroup(scanName(), scanCoordinates(), scanRealHero(), scanHasToothpick(), scanImpactSpeed(), scanWeaponType(), scanMood(), scanCar());
        getGenCollection().remove(key);
        getGenCollection().put(key, humanBeing1);
    }
    public  void updateId(int element){// метода для создания нового обьекта и помещение его в коллекцию

    }
    /**
     * Удаляет элемент коллекции, id которого равен введенному
     * @param id ключ
     */
    public  void remove_by_id(int id){
        genCollection.remove(id);
    }
    /**
     * Геттер genCollection
     * @return возвращает коллекцию
     */
    public TreeMap<Integer, StudyGroup> getGenCollection() {
        return genCollection;
    }
    /**
     * Удаляет все элементы из коллекции
     */
    public  void clear(){if (genCollection.size() == 0) {
        System.out.println("Коллекция уже пуста");
    } else {
        genCollection.clear();
    }}
    public  void save(){}
    public  void execute_script( String file_name){}
    /**
     * Завершает программу (без сохранения в файл)
     */
    public void exit() {
        System.exit(0);
    }
    public  void head(){}
    public  void add_if_min(String element){}
    public  void remove_greater(String element){}
    public  void min_by_id(){}
    public  void filter_by_group_admin(String groupAdmin){}
    public  void print_unique_semester_enum(){}


}