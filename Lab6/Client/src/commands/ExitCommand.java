package commands;

import helpers.CommandReceiver;

import java.io.Serializable;

public class ExitCommand implements Command, Serializable {
    private final String key = "exit";
    private final String helpText = "завершить программу (без сохранения в файл)";

    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.exit();
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

