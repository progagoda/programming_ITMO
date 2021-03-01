import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import gson.com.google.gson.*;
import gson.com.google.gson.reflect.*;
public class CollectionManager {
    private PriorityQueue<StudyGroup> students;
    private File jsonCollection;
    private Date initDate;
    private Gson serializer;
    private boolean wasStart;
    protected static HashMap<String, String> help;

    {
        wasStart = false;
        serializer = new Gson();
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

    public CollectionManager(String collectionPath) throws IOException {
        try {
            if (collectionPath == null) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.out.println("Путь до файла json нужно передать через переменную окружения Collman_Path.");
            System.exit(1);

        }
        File file = new File(collectionPath);
        try {
            if (file.exists()) this.jsonCollection = new File(collectionPath);
            else throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.out.println("Файл по указанному пути не существует.");
            System.exit(1);
        }
        this.load();
        this.initDate = new Date();
        wasStart = true;

    }

    /**
     * Удаляет последний элемент коллекции.
     */
    public void remove_last() {
        try {
            students.removeLast();
            System.out.println("Последний элемент коллекции удалён.");
            save();
        }

    }
}