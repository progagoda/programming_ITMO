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
     //   FileManager.Writer();
        FileManager fileManager;
        final String myenv=System.getenv("StudyGroup.json");
        fileManager=new FileManager(myenv);

        GeneralColl collectionManager = new GeneralColl(fileManager);
        ProgramStarter programStarter = new ProgramStarter(collectionManager);
        fileManager.readCollection();
        programStarter.start();

            }
        }
