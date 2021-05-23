package commands;

import helpers.CommandReceiver;

import java.io.Serializable;

public class PrintSemesterCommand implements Command, Serializable {
    private final String key = "print_unique_semester";
    private final String helpText = "выводит уникальные семместры";

    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.printSemester();
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
