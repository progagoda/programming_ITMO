package helpers;

import collection.IdManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import general.FormOfEducation;
import general.Semester;
import general.StudyGroup;
import message.MessageColor;
import message.Messages;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
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
                    Messages.normalMessageOutput("\u001B[37m" + "\u001B[31m"+"Не найдена локальная переменная"+ "\u001B[31m" + "\u001B[37m", MessageColor.ANSI_RED);

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
                BufferedInputStream reader = new BufferedInputStream(new FileInputStream((file)));
                Type collectionType = new TypeToken<PriorityQueue<StudyGroup>>() {
                }.getType();
                PriorityQueue<StudyGroup> collection = gson.fromJson(fileScanner, collectionType);
                        for (StudyGroup group : collection) {//детальная проверка файла на корректность
                            String line;
                            try {
                                Scanner scanner = new Scanner(System.in);
                                if (group.getName() == null) {
                                    while(true){
                                    System.out.println("Незаполнено поле Name  группы с id " + group.getId());
                                    System.out.println("Хотите заполнить данный объект?В ином случае он не загрузиться(true/false)");
                                    try {
                                        if (scanner.hasNextLine()) {
                                            line = scanner.nextLine().trim();
                                        } else {
                                            return null;
                                        }
                                        if (line.equals("true")) {
                                            System.out.print("Введите значение для поля Name: ");
                                            line = scanner.nextLine().trim();
                                            if (line.equals("end")) {
                                                System.out.println("Добавление элемента остановлено.");
                                                return null;
                                            }
                                            if (group.setName(line)) {
                                                System.out.println("Поле Name дописано");
                                                if (check(group)) break;
                                            }
                                        }
                                        if (line.equals("false")) {
//
                                            collection.remove(group);
                                            System.out.println("Тк вы не заполнили поле объект был пропущен");
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Ошибка ввода поля Name, попробуйте еще раз или напишите end");
                                    }}
                                }//проверка работает
                                if (group.getCoordinates().getX() == null) {
                                    while(true){
                                    System.out.println("Незаполнено поле Coordinates у группы с id " + group.getId());
                                    System.out.print("Хотите заполнить данный объект?В ином случае он не загрузиться(true/false):");
                                    try {
                                        if (scanner.hasNextLine()) {
                                            line = scanner.nextLine().trim();
                                        } else {
                                            return null;
                                        }
                                        if (line.equals("true")) {
                                            System.out.print("Введите значение для поля X: ");
                                            line = scanner.nextLine().trim();
                                                    if (group.write_coordinatesX(Long.valueOf(line)))
                                                    {
                                                        System.out.println("Поле X дописано");
                                                        if (group.getName() != null && group.getId() != null && group.getSemesterEnum() != null && group.getCreationDate() != null && group.getCoordinates().getY() != null
                                                                && group.getStudentsCount() != null && group.getExpelledStudents() != 0 && group.getGroupAdmin() != null) {
                                                            break;
                                                        }
                                                    }
                                                    if (line.equals("end")) {
                                                        System.out.println("Добавление элемента остановлено.");
                                                        break;
                                                    }
                                                }
                                        if (line.equals("false")) {
                                            collection.remove(group);
                                            System.out.println("Тк вы не заполнили поле объект был пропущен");
                                            break;
                                        }
                                    } catch (NullPointerException e) {
                                        System.out.println("Ошибка ввода поля X, попробуйте еще раз или напишите end");
                                    }}
                                }//проверка работает
                                if (group.getCoordinates().getY()==null){
                                        while (true){
                                        System.out.println("Незаполнено поле Y у группы с id " + group.getId());
                                        System.out.print("Хотите заполнить данный объект?В ином случае он не загрузиться(true/false):");
                                        try {
                                            if (scanner.hasNextLine()) {
                                                line = scanner.nextLine().trim();
                                            } else {
                                                return null;
                                            }
                                            if (line.equals("true")) {
                                                System.out.print("Введите значение для поля Y: ");
                                                line = scanner.nextLine().trim();
                                                if (group.write_coordinatesY(Float.valueOf(line))) ;
                                                {
                                                    System.out.println("Поле Y дописано");
                                                    if (group.getName() != null && group.getId() != null && group.getSemesterEnum() != null && group.getCreationDate() != null && group.getCoordinates().getX() != null
                                                            && group.getStudentsCount() != null && group.getExpelledStudents() != 0 && group.getGroupAdmin() != null) {
                                                        break;
                                                    }
                                                }
                                            }
                                            if (line.equals("end")) {
                                                System.out.println("Добавление элемента остановлено.");
                                                break;
                                            }
                                            if (line.equals("false")) {
                                                collection.remove(group);
                                                System.out.println("Тк вы не заполнили поле объект был пропущен");
                                                break;
                                            }
                                        }
                                         catch (NullPointerException e) {
                                            System.out.println("Ошибка ввода поля Y, попробуйте еще раз или напишите end");
                                }}}//проверка работает
                                if (group.getStudentsCount() == null) {
                                    while(true){
                                    System.out.println("Незаполнено поле StudentCount у группы с id " + group.getId());
                                    System.out.println("Хотите заполнить данный объект?В ином случае он не загрузиться(true/false)");
                                    try {
                                        if (scanner.hasNextLine()) {
                                            line = scanner.nextLine().trim();
                                        } else {
                                            return null;
                                        }
                                        if (line.equals("true")) {
                                            System.out.print("Введите значение для поля StudentsCount: ");
                                            line = scanner.nextLine().trim();
                                            if (line.equals("end")) {
                                                System.out.println("Добавление элемента остановлено.");
                                                return null;
                                            }
                                            if (group.setStudentsCount(Long.valueOf(line))) {
                                                System.out.println("Поле StudentsCount дописано");
                                                //if (check(group))
                                                  break;
                                            }
                                        }
                                        if (line.equals("false")) {
//
                                            collection.remove(group);
                                            System.out.println("Тк вы не заполнили поле объект был пропущен");
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Ошибка ввода поля StudentsCount, попробуйте еще раз или напишите end");
                                    }}
                                }//проверка работает
                                if (group.getExpelledStudents() == 0) {
                                    System.out.println("Незаполнено поле ExpelledStudents у группы с id " + group.getId());
                                    while (true) {
                                        System.out.println("Хотите заполнить данный объект?В ином случае он не загрузиться(true/false)");
                                        try {
                                            if (scanner.hasNextLine()) {
                                                line = scanner.nextLine().trim();
                                            } else {
                                                return null;
                                            }
                                            if (line.equals("true")) {
                                                System.out.print("Введите значение для поля expelledStudents: ");
                                                line = scanner.nextLine().trim();
                                                if (line.equals("end")) {
                                                    System.out.println("Добавление элемента остановлено.");
                                                    return null;
                                                }
                                                if (group.setExpelledStudents(Long.valueOf(line))) {
                                                    System.out.println("Поле expelledStudents дописано");
                                                    if (check(group)) break;
                                                }
                                            }
                                            if (line.equals("false")) {
//
                                                collection.remove(group);
                                                System.out.println("Тк вы не заполнили поле объект был пропущен");
                                                break;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Ошибка ввода поля expelledStudents, попробуйте еще раз или напишите end");
                                        }
                                    }
                                }// проверка работает
                                if (group.getFormOfEducation() == null) {//проверка работает
                                    System.out.println("Незаполнено поле Форма обучения у группы с id " + group.getId());
                                    while (true) {
                                        System.out.println("Хотите заполнить данный объект?В ином случае он не загрузиться(true/false):");
                                        try {
                                            if (scanner.hasNextLine()) {
                                                line = scanner.nextLine().trim();
                                            } else {
                                                return null;
                                            }
                                            if (line.equals("true")) {
                                                System.out.print("Введите значение для поля номер семместра: " + Arrays.toString(FormOfEducation.values()) + " : ");
                                                line = scanner.nextLine().trim();
                                                if (line.equals("end")) {
                                                    System.out.println("Добавление элемента остановлено.");
                                                    return null;
                                                }
                                                if (group.setFormOfEducation(FormOfEducation.valueOf(line))) {
                                                    System.out.println("Поле форма обучения дописано");
                                                    if (check(group)) break;

                                                }
                                            }
                                            if (line.equals("false")) {
                                                collection.remove(group);
                                                System.out.println("Тк вы не заполнили поле объект был пропущен");
                                                break;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Ошибка ввода поля форма обучения, попробуйте еще раз или напишите end");
                                        }
                                    }
                                }// проверка работает
                                if (group.getGroupAdmin().getName() == null) {
                                    System.out.println("Незаполнено поле groupAdmin у группы с id " + group.getId());
                                    while (true) {
                                        System.out.println("Хотите заполнить данный объект?В ином случае он не загрузиться(true/false):");
                                        try {
                                            if (scanner.hasNextLine()) {
                                                line = scanner.nextLine().trim();
                                            } else {
                                                return null;
                                            }
                                            if (line.equals("true")) {
                                                System.out.print("Введите значение для поля groupAdmin: ");
                                                line = scanner.nextLine().trim();
                                                if (line.equals("end")) {
                                                    System.out.println("Добавление элемента остановлено.");
                                                    return null;
                                                }
                                                if (group.getGroupAdmin().setName(line)) {
                                                    System.out.println("Поле groupAdmin дописано");
                                                    if (check(group)) break;
                                                }
                                            }
                                            if (line.equals("false")) {
//
                                                collection.remove(group);
                                                System.out.println("Тк вы не заполнили поле объект был пропущен");
                                                break;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Ошибка ввода поля groupAdmin, попробуйте еще раз или напишите end");
                                        }
                                    }
                                }// проверка работает
                                if (group.getCreationDate()== null) {//здесь Беда вылетает
                                    System.out.println("Не заполнено поле CreationDate у группы с id " + group.getId());
                                    while (true) {
                                        System.out.println("Хотите заполнить данный объект?В ином случае он не загрузиться(true/false):");
                                        try {
                                            if (scanner.hasNextLine()) {
                                                line = scanner.nextLine().trim();
                                            } else {
                                                return null;
                                            }
                                            if (line.equals("true")) {
                                                System.out.print("Это поле генерируется автоматически");
                                                if (group.setCreationDate(LocalDateTime.now())) {
                                                    System.out.println("Генерация CreationDate выполнена");
                                                    if (check(group)) break;
                                                }
                                            }
                                            if (line.equals("false")) {
//
                                                collection.remove(group);
                                                System.out.println("Тк вы не заполнили поле объект был пропущен");
                                                break;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Ошибка ввода поля CreationDate, попробуйте еще раз или напишите end");
                                        }
                                    }

                                }//вылетает хз как фиксить если дату удалить из json
                                if (group.getSemesterEnum() == null) {
                                    System.out.println("Незаполнено поле Номер семместра у группы с id " + group.getId());
                                    while (true) {
                                        System.out.print("Хотите заполнить данный объект?В ином случае он не загрузиться(true/false):");
                                        try {
                                            if (scanner.hasNextLine()) {
                                                line = scanner.nextLine().trim();
                                            } else {
                                                return null;
                                            }
                                            if (line.equals("true")) {
                                                System.out.print("Введите значение для поля номер семместра: " + Arrays.toString(Semester.values()) + " : ");
                                                line = scanner.nextLine().trim();
                                                if (line.equals("end")) {
                                                    System.out.println("Добавление элемента остановлено.");
                                                    return null;
                                                }
                                                if (group.setSemesterEnum(Semester.valueOf(line))) {
                                                    System.out.println("Поле номер семместра дописано");
                                                    if (check(group)) break;
                                                }
                                            }
                                            if (line.equals("false")) {
                                                collection.remove(group);
                                                System.out.println("Тк вы не заполнили поле объект был пропущен");
                                                break;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Ошибка ввода поля номер семместра, попробуйте еще раз или напишите end");
                                        }
                                    }
                                }//проверка работает
                                if (group.getId() == null) {
                                    System.out.println("Незаполнено поле Номер семместра у группы с id " + group.getId());
                                    while (true) {
                                        System.out.print("Хотите заполнить данный объект?В ином случае он не загрузиться(true/false):");
                                        try {
                                            if (scanner.hasNextLine()) {
                                                line = scanner.nextLine().trim();
                                            } else {
                                                return null;
                                            }
                                            if (line.equals("true")) {
                                                System.out.println("Данное поле генерируется автоматически");
                                                group.setId(IdManager.findUniq(Math.abs(new Random().nextLong())));
                                                System.out.println("Теперь id у данного объекта:" + group.getId());
                                                if (check(group)) break;
                                            }
                                                if (group.setSemesterEnum(Semester.valueOf(line))) {
                                                    System.out.println("Поле номер семместра дописано");
                                                    if (check(group)) break;
                                                }

                                            if (line.equals("false")) {
                                                collection.remove(group);
                                                System.out.println("Тк вы не заполнили поле объект был пропущен");
                                                break;
                                            }

                                        } catch (Exception e) {
                                            System.out.println("Ошибка ввода поля id, попробуйте еще раз или напишите end");
                                        }
                                    }
                                }//проверка работает
                            } catch (NullPointerException e){
                                System.out.println("Ошибка");

                            }
                        }
                //System.out.println("\u001B[37m" + "\u001B[33m" + "Коллекция успешно загружена!" + "\u001B[33m" + "\u001B[37m");
                if (collection == null) return new PriorityQueue<>();
                return collection;
            } catch (FileNotFoundException e) {
                System.err.println("Файл с таким именем не найден :(");

            } catch (IOException e) {
                System.err.println("Ошибка ввода-вывода");

            } catch (JsonSyntaxException e) {
                System.err.println("Формат файла не удовлетворяет условию");
            }
//           catch (RuntimeException e){
//               Messages.normalMessageOutput("\u001B[37m" + "\u001B[33m"+"Файл написан с ошибкой, перепроверьте файл и запустите программу снова"+ "\u001B[33m" + "\u001B[37m");
//           }
        }
            System.out.println("\u001B[37m" + "\u001B[31m" + "Системная переменная с загрузочным файлом не найдена!" + "\u001B[31m" + "\u001B[37m");
        return new PriorityQueue<>();

    }

    private boolean check(StudyGroup group) {//метод который проверяет есть ли другие null значения
        if (group.getName() != null && group.getId() != null && group.getSemesterEnum() != null && group.getCreationDate() != null && group.getCoordinates() != null
                && group.getStudentsCount() != null && group.getExpelledStudents() != 0 && group.getGroupAdmin() != null) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String string = "FileManager (класс для работы с загрузочным файлом)";
        return string;
    }
}