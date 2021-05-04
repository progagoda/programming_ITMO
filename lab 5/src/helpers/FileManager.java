package helpers;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import general.StudyGroup;
import java.io.*;
import java.lang.reflect.Type;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Класс загрузки/чтения коллекции из файла
 */
public class FileManager {
    private Gson gson = new Gson();
    private String envVariable;
    File file;
    public FileManager(String fileName)  {
        this.envVariable = fileName;
        try {
            this.file = new File(envVariable);
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
                try (PrintWriter out = new PrintWriter(new PrintWriter(new File("file2")))) {
                    out.write(gson.toJson(collection));
                    out.close();
                    System.out.println("Не переживайте. Мы записали вашу коллекцию в новый файл: " + "/home/s285384/PROGA/lab5/file2");
                } catch (Exception e) {
                    Messages.normalMessageOutput("\u001B[37m" + "\u001B[31m"+"Не найдена локальная переменная"+ "\u001B[31m" + "\u001B[37m");

                }

            } else {
                try (PrintWriter pw = new PrintWriter(new PrintWriter(envVariable))) {
                    File file = new File(envVariable);

                    pw.write(gson.toJson(collection));
                    pw.close();
                    System.out.println("Коллекция успешно сохранена в файл!");

                } catch (Exception e) {
                    System.out.println("Коллекция не смогла сохраниться");

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
        if (file!= null) {
            if (file.exists() & !file.canRead()) {
                System.out.println("\u001B[37m" + "\u001B[31m" + "Недостаточно прав для чтения данных из файла. Добавьте права на чтение и запустите программу вновь" + "\u001B[31m" + "\u001B[37m");
                System.exit(0);
            }
            try (FileReader fileScanner= new FileReader(file)) {
                BufferedInputStream reader= new BufferedInputStream(new FileInputStream((file)));
                Type collectionType = new TypeToken<PriorityQueue<StudyGroup>>(){}.getType();
                PriorityQueue<StudyGroup> collection = gson.fromJson(fileScanner, collectionType);
                reader.readAllBytes();
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
           catch (RuntimeException e){
               Messages.normalMessageOutput("\u001B[37m" + "\u001B[33m"+"Файл написан с ошибкой, перепроверьте файл и запустите программу снова"+ "\u001B[33m" + "\u001B[37m");
               Scanner file = new Scanner(file);
           }
        } else
            System.out.println("\u001B[37m" + "\u001B[31m" + "Системная переменная с загрузочным файлом не найдена!" + "\u001B[31m" + "\u001B[37m");
        return new PriorityQueue<>();

    }

    @Override
    public String toString() {
        String string = "FileManager (класс для работы с загрузочным файлом)";
        return string;
    }
}