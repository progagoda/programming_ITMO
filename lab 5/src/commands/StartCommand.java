package commands;
import collection.GeneralColl;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс,управялющий командами
 */
public class StartCommand {
    /** Карта команд. Ключ - строковое название команды. Значение - классы, реализующие интерфейс CommandDo*/
    private static final Map<String,CommandDo> commands= new HashMap<>();
    /** Массив для хранения команд, которые обрабатываются командо execute_script*/
    public static PriorityQueue<String> runScripts = new PriorityQueue<>();

    /**
     *
     * @param name имя команды (ключ)
     * @param commandDo объект команды
     */
    public static void addCommand(String name, CommandDo commandDo) {
        commands.put(name, commandDo);
    }
    /**
     * Введенная строка пользователем разбивается на две строки по пробелу, если второй строка пустая то вызывается метод {@link CommandDo#execute(String, GeneralColl)} с первым аргументом null, если вторая строка не пустая то так же запускается метод {@link CommandDo#execute(String, GeneralColl)}  и в первый аргумент передается вторая строка
     * @param words строка введенная пользователем
     * @param generalCollection класс с коллекцией, над которой производятся действия
     */
    public static void doing(String words, GeneralColl generalCollection) {
        String[] partsWords = words.split(" ");
        if (words.isEmpty()) {
            return;
        }
        if (partsWords.length == 1) {    // если введено одно слово
            CommandDo commandDo = commands.get(partsWords[0]);
            if(commandDo != null) {    // проверка на правильность программы
                commandDo.execute(null, generalCollection);
            } else {
                System.out.println("Команды не существует");
            }
        } else if (partsWords.length == 2) {  // если введено 2 слова
            CommandDo commandDo = commands.get(partsWords[0]);
            String kek = partsWords[1];
            if (commandDo != null) {
                commandDo.execute(kek, generalCollection);
            } else {
                System.out.println("Команды не существует");
            }
        } else {
            System.out.println("Неверный ввод команды");
        }

    }

}

