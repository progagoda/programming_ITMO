package commands;

import general.Person;
import helpers.CommandReceiver;

import java.io.Serializable;

public class FilterByGroupAdminCommand implements Command, Serializable {
    private final String key = "filter_admin";
    private final String helpText = "вывести элементы по имени старосты";
    private final String adminName;
    private static final long serialVersionUID = 6529685098267757694L;
    public FilterByGroupAdminCommand(String admin) {
        this.adminName=admin;
    }
    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.filterAdmin(adminName);
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
