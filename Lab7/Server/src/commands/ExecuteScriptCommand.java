package commands;

import helpers.CommandReceiver;

import java.io.Serializable;
import java.util.ArrayList;

public class ExecuteScriptCommand implements Command, Serializable {
    private final String key = "execute_script";
    private final String helpText = "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    private final ArrayList<String> script;
    private static final long serialVersionUID = 6529685098267757690L;

    public ExecuteScriptCommand(ArrayList<String> list) {
        this.script = list;
    }

    @Override
    public String execute(CommandReceiver commandReceiver) {
        return commandReceiver.executeScript(script);
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