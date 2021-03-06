import collection.GeneralColl;


import java.io.File;
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
    public static void main(String[] args){
        FileManager fileManager;
        final String myenv="M:\\лабы прога\\programming_ITMO\\lab 5\\StudyGroup.json";
        fileManager=new FileManager(myenv);
        GeneralColl collectionManager = new GeneralColl(fileManager);
        collectionManager.loadCollection();
        ProgramStarter programStarter = new ProgramStarter(collectionManager);
        programStarter.start();

            }
        }
