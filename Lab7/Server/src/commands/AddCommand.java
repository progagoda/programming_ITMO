package commands;

import general.Semester;
import general.StudyGroup;
import helpers.CommandReceiver;

import java.io.Serializable;
import java.util.Scanner;

public class AddCommand implements Command, Serializable {
    private final String key = "add";
    private final String helpText = "добавить новый элемент с заданным ключом";
    private static final long serialVersionUID = 6529685098267757690L;
    private final StudyGroup group;

    public AddCommand(StudyGroup group) {
        this.group = group;
    }

    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.add(group);
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


