package commands;
import collection.GeneralColl;

/**
 * интерфейс для выполнения команд
 */
public interface CommandDo {
    /**
     * @param name строковое значение, аргумент команды
     * @param generalCollection класс с коллекцией, над которой производятся действия
     */
    void execute(String name,GeneralColl generalCollection);

}
