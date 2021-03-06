package commands;

import general.StudyGroup;
import helpers.CommandReceiver;

import java.io.Serializable;

public class AddMinCommand implements Command, Serializable {
    private final String key = "add";
    private final String helpText = "добавить элемент";
    private final StudyGroup group;
    private static final long serialVersionUID = 6529685098267757691L;

    public AddMinCommand(StudyGroup group) {
        this.group = group;
    }

    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.addmin(group);
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
