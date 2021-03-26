package commands;

import general.GeneralColl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Класс, реализующий команду execute_script file_name, которая считывает и исполняет скрипт из указанного файла
 */
public class Execute_script implements CommandDo {
    /**
     * Конструктор по умолчанию, который добавляет объект класса команды в коллекцию команд
     */
    public Execute_script(){
        StartCommand.addCommand("execute_script",this);
    }
    /**
     * Проверяется наличие команды вызова этого файла в {@link StartCommand#runScripts}, если команда обнаружена, то команда пропускается в целях защиты от рекурсии, если же она не обнаружена, то вызвается метод {@link StartCommand#doing(String, GeneralColl)} (String, GeneralCollection)}. По завершению файл от туда удаляется
     * @param name строковое значение, имя файла
     * @param generalCollection класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(String name, GeneralColl generalCollection) {
        try (BufferedReader bufferedReader = new BufferedReader((new FileReader(name)))){
            StartCommand.runScripts.add("execute_script " + name);
            String inpurs = bufferedReader.readLine();
            while (inpurs != null) {
                if(inpurs.contains("execute_script") && StartCommand.runScripts.contains(inpurs)) {
                    System.out.println(inpurs.split(" ")[1] + " Уже запущен. Команда пропущена");
                } else {
                    StartCommand.doing(inpurs, generalCollection);
                }
                inpurs = bufferedReader.readLine();
            }
        } catch (IOException ex){
            System.out.println("Файл не найден или доступ к нему запрещен.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        StartCommand.runScripts.remove("execute_script" + name);
    }
}
