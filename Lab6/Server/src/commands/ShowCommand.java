package commands;

import helpers.CommandReceiver;

import java.io.Serializable;

/**
 * Класс, реализующий программу show, которая выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class ShowCommand implements Serializable,Command {
    private final String key = "show";
    private final String helpText = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";

    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.show();
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getHelpText() {
        return helpText;
    }
}
