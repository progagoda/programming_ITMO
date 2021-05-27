package commands;

import helpers.CommandReceiver;

import java.io.Serializable;

/**
 * Класс команды remove_key
 */
public class RemoveIdCommand implements Command, Serializable {
    private final String key = "remove_key";
    private final String helpText = "удалить элемент из коллекции по его ключу";
    private final Long id;
    private static final long serialVersionUID = 6529685098267757693L;
    public RemoveIdCommand(Long id) {
        this.id = id;
    }
    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.removeKey(id);
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