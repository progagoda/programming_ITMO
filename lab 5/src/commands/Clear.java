package commands;
import general.GeneralCollection;
/**
 * Класс, реализующий программу clear, очищающую коллекцию
 */
public class Clear  implements  CommandDo{
    /**
     * Конструктор по умолчанию, который добавляет объект класса команды в коллекцию команд
     */
    public Clear() {
        StartCommand.addCommand("clear", this);
    }
    /**
     * Обращение к методу {@link GeneralCollection#clear()}
     * @param name строковое значение, аргумент команды (null)
     * @param generalCollection класс с коллекцией над которой производятся действия
     */
    @Override
    public void execute(String name, GeneralCollection generalCollection) {
        generalCollection.clear();
    }
}
