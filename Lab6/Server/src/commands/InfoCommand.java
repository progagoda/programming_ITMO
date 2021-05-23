package commands;

import helpers.CommandReceiver;

import java.io.Serializable;

public class InfoCommand implements Serializable,Command {
    private final String key = "info";
    private final String helpText = "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";

    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.info();
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