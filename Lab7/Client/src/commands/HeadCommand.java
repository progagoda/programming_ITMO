package commands;

import helpers.CommandReceiver;

import java.io.Serializable;

public class HeadCommand implements Command, Serializable {
    private final String key = "head";
    private final String helpText = "вывести первый элемент в очереди";

    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.printHead();
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

