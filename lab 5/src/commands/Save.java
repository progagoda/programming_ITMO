package commands;
import collection.GeneralColl;
/**
        * Класс, реализующий программу save, которая сохраняет коллекцию в файл
        */
public class Save implements CommandDo {
    /**
     * Конструктор по умолчанию, который добавляет объект класса команды в коллекцию команд
     */
    public Save() {
        StartCommand.addCommand("save", this);
    }

    /**
     * Обращение к методу {@link GeneralColl#save()}
     * @param name строковое значение, аргумент команды(null)
     * @param generalCollection класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(String name, GeneralColl generalCollection) {
        generalCollection.save();
    }
}

