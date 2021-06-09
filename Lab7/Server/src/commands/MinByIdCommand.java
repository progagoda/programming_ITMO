package commands;

import helpers.CommandReceiver;

import java.io.Serializable;

public class MinByIdCommand implements Command, Serializable {
    private final String key = "min_by_id";
    private final String helpText = "вывести элемент с минимальным id";

    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.minById();
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
