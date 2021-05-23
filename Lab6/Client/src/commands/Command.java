package commands;

import helpers.CommandReceiver;

public interface Command {
    String execute(CommandReceiver commandReceiver);

    String getKey();

    String getHelpText();
}

