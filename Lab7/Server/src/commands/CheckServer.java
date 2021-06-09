package commands;

import helpers.CommandReceiver;

import java.io.Serializable;

public class CheckServer implements Command, Serializable {
    private final String key = "";
    private final String helpText = "проверка сервера";

    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.checkServer();
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
