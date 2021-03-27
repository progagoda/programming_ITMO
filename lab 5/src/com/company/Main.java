package com.company;

import commands.Help;
import commands.StartCommand;
import general.GeneralColl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import general.*;
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
        GeneralColl generalCollection = new GeneralColl();
       // Info info= new Info();
        Help help= new Help();
       // Add add= new Add();
       // Save save = new Save();
        //Clear clear = new Clear();
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
