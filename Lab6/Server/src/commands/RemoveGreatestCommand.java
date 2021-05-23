package commands;

import general.StudyGroup;
import helpers.CommandReceiver;

import java.io.Serializable;

public class RemoveGreatestCommand implements Command, Serializable {
    private final String key = "remove_greatest";
    private final String helpText = "заменить самый большой элемент";
    private final StudyGroup group;
    private static final long serialVersionUID = 6529685098267757692L;

    public RemoveGreatestCommand(StudyGroup group) {
        this.group = group;
    }
    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.removeGreatest(group);
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
