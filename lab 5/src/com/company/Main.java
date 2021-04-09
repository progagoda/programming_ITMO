package com.company;
import collection.GeneralColl;


import java.io.IOException;

import helpers.FileManager;
import helpers.ProgramStarter;

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
        FileManager fileManager;
        String file="StudyGroup";
        fileManager=new FileManager(file);
        GeneralColl collectionManager = new GeneralColl(new FileManager(file));
        //CollectionManager collectionManager = new CollectionManager(new CSVFileReader().getFileNameFromArgs(args));

        ProgramStarter programStarter = new ProgramStarter(collectionManager);
        programStarter.start();
            }
        }
