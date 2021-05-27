package helpers;

import collection.GeneralColl;
import commands.*;
import general.StudyGroup;
import message.MessageColor;
import message.Messages;

import java.util.ArrayList;
import java.util.HashSet;

public class CommandReceiver {
    private final HashSet<Command> commandSet = new HashSet<>();
    private String path;
    private  GeneralColl collectionManager;
    public CommandReceiver() {

        FileManager fileManager;
         String path = "E:\\файлы итмо лабы\\программирование\\programming_ITMO\\Lab6\\Server\\StudyGroup.json";
        if (System.getenv("GROUP_FILE") != null){ path = System.getenv("GROUP_FILE");}
        fileManager= new FileManager(path);
        collectionManager= new GeneralColl(fileManager);
        collectionManager.loadCollection();
        Messages.normalMessageOutput("Коллекция успешно загружена", MessageColor.ANSI_PURPLE);
        addCommands(
                new HelpCommand(),
                new InfoCommand(),
                new ShowCommand(),
                new AddCommand(null),
                new AddMinCommand(null),
                new RemoveIdCommand(null),
                new ClearCommand(),
                new AddMinCommand(null),
                new ExecuteScriptCommand(null),
                new HeadCommand(),
                new PrintSemesterCommand(),
                new RemoveGreatestCommand(null),
                new MinByIdCommand(),
                new FilterByGroupAdminCommand(null)

//                new ExecuteScriptCommand(null),
//                new ExitCommand(),
//                new RemoveLowerCommand(null),
//                new ReplaceIfGreaterCommand(0, null),
//                new RemoveLowerKeyCommand(0),
//                new RemoveAllByOwnerCommand(null),
//                new PrintAscendingCommand(),
//                new PrintDescendingCommand()
        );

    }

    public String help() {
        return collectionManager.help();
    }
    public String minById() {return collectionManager.findElementWithMinId();}
    public String info() {
       return collectionManager.printInfoAboutCollection();
    }
    public String show() {
        return collectionManager.printAllElements();
    }
    public String clear(){
        return collectionManager.clearCollection();
    }
    public String printHead(){
        return  collectionManager.getHeadOfCollection();
    }
    public String removeGreatest(StudyGroup group){return  collectionManager.removeGreatest(group);}
    public  String removeKey(Long id){return  collectionManager.removeElementById(id);}
    public String filterAdmin(String admin){return collectionManager.printElementbyGroupAdmin(admin);}
    public  String update(Long id,StudyGroup group){return collectionManager.updateElement(id,group);}
    public  String printSemester(){return collectionManager.printElementbySemester();}
    public  String checkServer(){return Messages.unnormalMessageOutput("Cервер готов к передаче информации",MessageColor.ANSI_BLUE);}
    /**
     * Логика для add
     */
    public String add(StudyGroup group) {
        return collectionManager.addElement(group);
    }
    public String addMin(StudyGroup group) {
        return  collectionManager.add_if_min(group);
    }
    private void addCommands(Command ... commands) {
        for (Command command : commands) {
            commandSet.add(command);
        }
    }
    public String executeScript(ArrayList<String> list) {
        return null;
    }
    public String save() {
        if (collectionManager.getCollection().size() > 0) {
            collectionManager.saveToFile();
            return "Коллекция сохранена";
        } else {
            return "Что-то пошло не так";
        }
    }

}
