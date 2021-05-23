package helpers;

import general.*;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс для создания Новой StudyGroup из командной строки / файла
 */
public class StudyGroupMaker {
    /**
     * Создание новой Group
     *
     * @param scanner указывает откуда считывать
     * @return возвращает новую созданную Group
     */
    public StudyGroup makeGroup(Scanner scanner) {
        StudyGroup group = new StudyGroup();
        String line;
        while (true) {
            System.out.print("Введите значение для поля name: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Добавление элемента остановлено.");
                    return null;
                }
                if (group.setName(line)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля name, попробуйте еще раз или напишите end");
            }
        }
        while (true) {
            System.out.print("Введите значение для поля studentCount: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Добавление элемента остановлено.");
                    return null;
                }
                if (group.setStudentsCount(Long.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля studentCount, попробуйте еще раз или напишите end");
            }
        }

        while (true) {
            System.out.print("Введите значение для поля expelledStudents: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Добавление элемента остановлено.");
                    return null;
                }
                if (group.setExpelledStudents(Long.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля expelledStudents, попробуйте еще раз или напишите end");
            }
        }

        Person person = new Person();
        System.out.println("Теперь необходимо создать объект старосты группы, для этого:");
        while (true) {
            System.out.print("Введите значение для поля name: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Добавление элемента остановлено.");
                    return null;
                }
                if (person.setName(line)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля name, попробуйте еще раз или напишите end");
            }
        }

        while (true) {
            System.out.print("Введите значение для поля passportID: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Добавление элемента остановлено.");
                    return null;
                }
                if (person.setPassportID(line)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля passportID, попробуйте еще раз или напишите end");
            }
        }

        while (true) {
            System.out.print("Введите значение для поля eyeColor, есть такие значения + " + Arrays.toString(Color.values()) + " : ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Добавление элемента остановлено.");
                    return null;
                }
                if (person.setEyeColor(Color.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля eyeColor, попробуйте еще раз или напишите end");
            }
        }
        while (true) {
            System.out.print("Введите значение для поля hairColor, есть такие значения + " + Arrays.toString(Color.values()) + " : ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Добавление элемента остановлено.");
                    return null;
                }
                if (person.setHairColor(Color.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля hairColor, попробуйте еще раз или напишите end");
            }
        }
        while (true) {
            System.out.print("Введите значение для поля nationality, есть такие значения + " + Arrays.toString(Country.values()) + " : ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Добавление элемента остановлено.");
                    return null;
                }
                if (person.setNationality(Country.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля nationality, попробуйте еще раз или напишите end");
            }
        }
        group.setGroupAdmin(person);

        Coordinates coordinates = new Coordinates();
        System.out.println("Теперь необходимо создать Координаты, для группы:");
        while (true) {
            System.out.print("Введите значение для поля x: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Добавление элемента остановлено.");
                    return null;
                }
                if (coordinates.setX(Long.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля x, попробуйте еще раз или напишите end");
                continue;
            }
        }

        while (true) {
            System.out.print("Введите значение для поля y: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Добавление элемента остановлено.");
                    return null;
                }
                if (coordinates.setY(Float.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля y, попробуйте еще раз или напишите end");
            }
        }

        group.setCoordinates(coordinates);

        while (true) {
            System.out.print("Введите значение для поля formOfEducation, есть такие значения + " + Arrays.toString(FormOfEducation.values()) + " : ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Добавление элемента остановлено.");
                    return null;
                }
                if (group.setFormOfEducation(FormOfEducation.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля formOfEducation, попробуйте еще раз или напишите end");
            }
        }

        while (true) {
            System.out.print("Введите значение для поля semesterEnum, есть такие значения + " + Arrays.toString(Semester.values()) + " : ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Добавление элемента остановлено.");
                    return null;
                }
                if (group.setSemesterEnum(Semester.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля semesterEnum, попробуйте еще раз или напишите end");
            }
        }

        return group;
    }
}

