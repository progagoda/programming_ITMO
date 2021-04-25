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
    public static void main(String[] args) throws Exception {
        FileManager fileManager;
        final String myenv=System.getenv("E://GROUP_FILE");
        fileManager=new FileManager(myenv);
        GeneralColl collectionManager = new GeneralColl(fileManager);
       // collectionManager.loadCollection();
        ProgramStarter programStarter = new ProgramStarter(collectionManager);
        //fileManager.readCollection();
        programStarter.start();

            }
        }
