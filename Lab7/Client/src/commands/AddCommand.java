package commands;

import general.StudyGroup;
import helpers.CommandReceiver;

import java.io.Serializable;
import java.util.Scanner;

public class AddCommand implements Command, Serializable {
    private final String key = "add";
    private final String helpText = "добавить элемент";
    private final StudyGroup group;
    private String login;
    private static final long serialVersionUID = 6529685098267757690L;

    public AddCommand(StudyGroup group,String  login) {
        this.group = group;
        this.login= login;
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
