package com.company;

import commands.Help;
import commands.StartCommand;
import collection.GeneralColl;


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
        String[] test = new String[1];
        test[0] = "name";
        GeneralColl collectionManager = new GeneralColl(new CSVFileReader().getFileNameFromArgs(test));

        //CollectionManager collectionManager = new CollectionManager(new CSVFileReader().getFileNameFromArgs(args));

        ProgramStarter programStarter = new ProgramStarter(collectionManager);
        programStarter.start();
            }
        }

    }
}
