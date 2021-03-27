package commands;
import general.GeneralColl;
/**
 * Класс, реализующий программу show, которая выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class Show implements CommandDo {
    /**
     * Конструктор по умолчанию, который добавляет объект класса команды в коллекцию команд
     */
    public Show() {
        StartCommand.addCommand("show", this);
    }

    /**
     * Обращение к методу {@link GeneralColl
#show()}
     * @param name строковое значение, аргумент команды(null)
     * @param generalColl
 класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(String name, GeneralColl generalColl){
                GeneralColl.show();
    }
}

