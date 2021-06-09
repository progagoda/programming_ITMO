package commands;
import helpers.CommandReceiver;

import java.io.Serializable;

/**
 * Класс команды clear
 */
public class ClearCommand implements Command, Serializable {
    private final String key = "clear";
    private final String helpText = "очистить коллекцию";

    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.clear();
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
