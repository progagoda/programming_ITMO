package helpers;

import commands.Command;

public class CommandInvoker {
    CommandReceiver commandReceiver;

    public CommandInvoker(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    public String execute(Command command) throws InterruptedException {
        return command.execute(commandReceiver);
    }
}
