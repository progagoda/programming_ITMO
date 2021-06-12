package commands;

import general.StudyGroup;
import helpers.CommandReceiver;

import java.io.Serializable;

/**
 * Класс команды update
 */
public class UpdateCommand implements Command, Serializable {
    private final String key = "update";
    private final String helpText = "обновить значение элемента коллекции, id которого равен заданному";
    private final Long id;
    private final StudyGroup group;
    private  final String login;
    private static final long serialVersionUID = 6529685098267757690L;

    public UpdateCommand(Long id, StudyGroup group,String login) {
        this.id = id;
        this.group = group;
        this.login=login;
    }

    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.update(id,group);
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
