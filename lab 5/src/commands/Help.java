package commands;

import general.GeneralColl;

public class Help implements  CommandDo{
    /**
     * Конструктор по умолчанию, который добавляет объект класса команды в коллекцию команд
     */
    public Help() {
        StartCommand.addCommand("help", this);
    }

    @Override
    public void execute(String name, GeneralColl generalCollection) {

    }

    /**
     * Обращение к методу {@link GeneralCollection#help()}
     * @param name строковое значение, аргумент команды(null)
     * @param generalCollection класс с коллекцией, над которой производятся действия
     */


    }




