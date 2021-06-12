package commands;

import helpers.CommandReceiver;

public interface Command {
    String execute(CommandReceiver commandReceiver) throws InterruptedException;

    String getKey();

    String getHelpText();
}

