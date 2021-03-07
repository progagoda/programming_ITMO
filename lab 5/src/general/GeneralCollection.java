package general;

import java.io.*;
import java.util.*;

public class GeneralCollection {
    private PriorityQueue<StudyGroup> students;
    private File jsonCollection;
    private Date initDate;

    private boolean wasStart;
    protected static HashMap<String, String> help;

    {
        wasStart = false;

        students = new PriorityQueue<>();
        help = new HashMap<>();
        help.put("info", "Информация о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        help.put("show", "Вывод всех элементов коллекции");
        help.put("add {element}", "Добавить новый элемент в коллекцию");
        help.put("update id {element}", "Обновить значение элемента коллекции, id которого равен заданному");
        help.put("remove_by_id id", "Удалить элемент из коллекции по его id");
        help.put("clear", "Очистить коллекцию");
        help.put("save", "Сохранить коллекцию в файл");
        help.put("execute_script file_name", "Считать и исполнить скрипт из указанного файла");
        help.put("exit", "Завершить программу (без сохранения в файл)");
        help.put("head", "Вывести первый элемент коллекции");
        help.put("add_if_min {element}", "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        help.put("remove_greater {element}", "Удалить из коллекции все элементы, превышающие заданный");
        help.put("min_by_id", "Вывести любой объект из коллекции, значение поля id которого является минимальным");
        help.put("filter_by_group_admin groupAdmin", "Вывести элементы, значение поля groupAdmin которых равно заданному");
        help.put("print_unique_semester_enum", "Вывести уникальные значения поля semesterEnum всех элементов в коллекции");
    }
    public  void help(){}
    public  void info(){}
    public  void show(){}
    public  void add(int element){}
    public  void updateId(int element){}
    public  void remove_by_id(int id){}
    public  void clear(){}
    public  void save(){}
    public  void execute_script( String file_name){}
    public  void exit(){}
    public  void head(){}
    public  void add_if_min(String element){}
    public  void remove_greater(String element){}
    public  void min_by_id(){}
    public  void filter_by_group_admin(String groupAdmin){}
    public  void print_unique_semester_enum(){}


}