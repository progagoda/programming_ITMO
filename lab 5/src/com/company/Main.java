package com.company;

import commands.*;
import commands.StartCommand;
import general.GeneralCollection;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс Main
 */

public class Main {
    /**
     * Входное место программы
     * @param args аргумент
     * @throws IOException ошибка ввода
     */

    public static void main(String[] args) throws IOException {
        GeneralCollection generalCollection = new GeneralCollection();


        Help help = new Help();
        Info info = new Info();
        Show show = new Show();
        Clear clear = new Clear();
        Save save = new Save();
        Exit exit = new Exit();

        while (true) {
            System.out.println("Введите вашу команду, используйте \"help\" что бы получить список команд");
            System.out.print(">>>");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String pop = bufferedReader.readLine();
            if (!pop.isEmpty()) {
                StartCommand.doing(pop, generalCollection);
                System.out.println();
            }
        }

    }
}
