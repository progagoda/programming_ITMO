package helpers;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import general.StudyGroup;
import java.io.*;
import java.lang.reflect.Type;
import java.util.PriorityQueue;

/**
 * Класс загрузки/чтения коллекции из файла
 */
public class FileManager {
    private Gson gson = new Gson();
    private String envVariable;
    File file;

    public FileManager(String fileName) {
        this.envVariable = fileName;
        try {
            this.file = new File(System.getenv("M:\\лабы прога\\programming_ITMO\\lab 5\\StudyGroup.json"));
        } catch (NullPointerException e) {
            System.out.println("\u001B[37m" + "\u001B[31m" + "Вам необходимо задать переменную окружения!!!" + "\u001B[31m" + "\u001B[37m");
        }
    }

    /**
     * Запись коллекции в файл
     *
     * @param collection -коллекция, которую нужно записать
     */
    public void writeCollection(PriorityQueue collection) {
        if (file != null) {
            if (!file.canWrite()) {
                System.out.println("\u001B[37m" + "\u001B[31m" + "Недостаточно прав для записи в файл. Добавьте права на запись " + "\u001B[31m" + "\u001B[37m");
                try (PrintWriter out = new PrintWriter(new PrintWriter(new File("M:\\лабы прога\\programming_ITMO\\lab 5\\StudyGroup1.json")))) {
                    out.write(gson.toJson(collection));
                    out.close();
                    System.out.println("Не переживайте. Мы записали вашу коллекцию в новый файл: " + "/home/s285384/prog5/lib/file2");
                } catch (Exception e) {

                }

            } else {
                try (PrintWriter pw = new PrintWriter(new PrintWriter(System.getenv().get(envVariable)))) {
                    File file = new File(System.getenv().get(envVariable));

                    pw.write(gson.toJson(collection));
                    System.out.println("Коллекция успешно сохранена в файл!");

                } catch (Exception e) {
                    System.out.println();

                }
            }
        } else System.out.println("Системная переменная с загрузочным файлом не найдена!");
    }

    /**
     * Чтение коллекции из файла
     *
     * @return коллекция, которая была считана из файла
     */
    public PriorityQueue<StudyGroup> readCollection() {
        if (System.getenv("M:\\лабы прога\\programming_ITMO\\lab 5\\StudyGroup.json") != null) {
            if (file.exists() & !file.canRead()) {
                System.out.println("\u001B[37m" + "\u001B[31m" + "Недостаточно прав для чтения данных из файла. Добавьте права на чтение и запустите программу вновь" + "\u001B[31m" + "\u001B[37m");
                System.exit(0);
            }
            try (FileReader fileScanner = new FileReader(file)) {
                Type collectionType = new TypeToken<PriorityQueue<StudyGroup>>() {
                }.getType();
                PriorityQueue<StudyGroup> collection = gson.fromJson(fileScanner, collectionType);
                System.out.println("\u001B[37m" + "\u001B[33m" + "Коллекция успешно загружена!" + "\u001B[33m" + "\u001B[37m");
                if (collection == null) return new PriorityQueue<>();
                return collection;
            } catch (FileNotFoundException e) {
                System.err.println("Файл с таким именем не найден :(");

            } catch (IOException e) {
                System.err.println("Ошибка ввода-вывода");

            } catch (JsonSyntaxException e) {
                System.err.println("Формат файла не удовлетворяет условию");
            }
        } else
            System.out.println("\u001B[37m" + "\u001B[31m" + "Системная переменная с загрузочным файлом не найдена!" + "\u001B[31m" + "\u001B[37m");
        return new PriorityQueue<>();

    }

//    public static void Writer() throws Exception{
//        File file= new File("StudyGroup.json");
//        PrintWriter writer= new PrintWriter(file,"UTF-8");
//        writer.write(gson.toJson(collection);
//        writer.println(stroka);
//        writer.close();
//
//    }

    @Override
    public String toString() {
        String string = "FileManager (класс для работы с загрузочным файлом)";
        return string;
    }
}