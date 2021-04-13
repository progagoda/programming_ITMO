package helpers;

import collection.Invoker;

import java.util.Scanner;

/**
 * Класс, которые реализует считывание из командной строки / файла
 */
public class LineReader {

    /**
     * Считываение из командной строки / файла
     *
     * @param scanner показывает откуда считывать информацию
     * @param invoker необходим для реальизации вызова всех команд
     */
    public void readLine(Scanner scanner, Invoker invoker) {
        System.out.print("Введите команду: ");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] args = line.trim().split(" ");
            if (args.length == 1 && args[0].equals("")) {
                System.out.print("Введите команду: ");
                continue;
            }
            if (args.length != 0) {
                invoker.executeCommand(scanner, args);
                System.out.print("Введите команду: ");

            }
        }
    }

}

