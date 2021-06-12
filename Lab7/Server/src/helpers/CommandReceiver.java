package helpers;

import collection.IdManager;
import commands.*;
import comparators.IdComparator;

import general.Semester;
import general.StudyGroup;
import message.MessageColor;
import message.Messages;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandReceiver {
    private final HashSet<Command> commandSet = new HashSet<>();
    private PriorityQueue<StudyGroup> collection;
    private DatabaseManager databaseManager;
    private final LocalDate initDate;

    public CommandReceiver() {
        collection = new PriorityQueue<>();
        initDate = LocalDate.now();
//        FileManager fileManager;
//         String path = "E:\\файлы итмо лабы\\программирование\\programming_ITMO\\Lab6\\Server\\StudyGroup.json";
//        if (System.getenv("GROUP_FILE") != null){ path = System.getenv("GROUP_FILE");}
//        fileManager= new FileManager(path);
//        collectionManager= new GeneralColl(fileManager);
//        collectionManager.loadCollection();
        databaseManager = new DatabaseManager();
        try {
            databaseManager.connect(collection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addCommands(
                new CheckServer(),
                new HelpCommand(),
                new InfoCommand(),
                new ShowCommand(),
                new AddCommand(null),
                new AddMinCommand(null),
                new RemoveIdCommand(null, null),
                new ClearCommand(null),
                new AddMinCommand(null),
                new ExecuteScriptCommand(null),
                new HeadCommand(),
                new PrintSemesterCommand(),
                new RemoveGreatestCommand(null, null),
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
        Messages.normalMessageOutput("Коллекция успешно загружена", MessageColor.ANSI_PURPLE);
    }

    public void exit() {
        System.exit(-1);
    }

    public String help() {
        return Messages.unnormalMessageOutput("help : вывести справку по доступным командам \n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) \n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении \n" +
                "add {element : добавить новый элемент с заданным ключём \n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному \n" +
                "remove_by_id id : удалить элемент из коллекции по его id \n" +
                "clear : очистить коллекцию \n" +
                "save : сохранить коллекцию в файл \n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. \n" +
                "min_by_id : вывести любой объект из коллекции, значение поля id которого является минимальным \n" +
                "head: вывести первый элемент коллекции \n" +
                "add_if_min {element : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции \n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный \n" +
                "filter_by_group_admin groupAdmin : вывести элементы, значение поля groupAdmin которых равно заданному \n" +
                "print_unique_semester_enum : вывести уникальные значения поля semesterEnum всех элементов в коллекции \n" +
                "exit : завершить программу (без сохранения в файл) \n", MessageColor.ANSI_CYAN);

    }

    public String minById() {
        if (getCollection().size() > 0) {
            PriorityQueue<StudyGroup> queue = sortCollectionByComp(new IdComparator());
            return Objects.requireNonNull(queue.peek()).printInfoAboutElement();
        } else {
            return Messages.unnormalMessageOutput("Ваша коллекция пуста", MessageColor.ANSI_RED);
        }
    }

    public PriorityQueue<StudyGroup> sortCollectionByComp(Comparator<StudyGroup> comp) {
        List<StudyGroup> newList = new ArrayList<>(collection);
        newList.sort(comp);
        PriorityQueue<StudyGroup> newCollection = new PriorityQueue<>(comp);
        newCollection.addAll(newList);
        return newCollection;
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public String info() {
        return Messages.unnormalMessageOutput("Тип коллекции - " + getCollection().getClass().getName() + "\n" +
                "Дата инициализации - " + getInitDate() + "\n" +
                "Кол-во элементов - " + getCollection().size(), MessageColor.ANSI_YELLOW);

    }

    public String show() throws InterruptedException {
        StringBuilder s = new StringBuilder("");
        if (!collection.isEmpty()) {
            Stream<StudyGroup> stream = collection.stream();
            return collection.stream().map(StudyGroup::printInfoAboutElement).collect(Collectors.joining("\n"));
        } else return "Коллекция пуста";
    }

    public synchronized String clear(String login) {
        if (getCollection().size() > 0) {
            try {
                Iterator<StudyGroup> iterator = getCollection().iterator();
                while (iterator.hasNext()) {
                    StudyGroup group = iterator.next();
                    if (group.getCreator().equals(login)) {
                        IdManager.getHashSetOfIds().add(getCollection().peek().getId());
                        IdManager.removeUsedId(group.getId());
                        iterator.remove();
                    }
                }
                return Messages.unnormalMessageOutput("Все ваши элементы удалены", MessageColor.ANSI_GREEN);
            } catch (Exception e) {
                 e.printStackTrace();
                 return "Ошибка";

            }
        } else {
            return Messages.unnormalMessageOutput("В колекции нет элементов, нечего стирать", MessageColor.ANSI_RED);
        }
    }

    public void removeid(Long id, StudyGroup group) {
        if (group.getId().equals(id))
            getCollection().remove(group);
    }

    public String printHead() {
        if (getCollection().size() > 0) {
            return Objects.requireNonNull(getCollection().peek()).printInfoAboutElement();
        } else {
            return Messages.unnormalMessageOutput("В коллекции нет элементов, выводить нечего", MessageColor.ANSI_RED);
        }
    }

    public synchronized String removeGreatest(StudyGroup group, String login) {
        if (getCollection().size() > 0) {
            if (group != null) {
                try {
                    Iterator<StudyGroup> iterator = getCollection().iterator();
                    while (iterator.hasNext()) {
                        StudyGroup group1 = iterator.next();
                        if (group1.getCreator().equals(login)) {
                            if (getCollection().size() > 0 && getCollection().peek().compareTo(group) > 0) {
                                IdManager.removeUsedId(getCollection().poll().getId());
                                collection.remove(group1);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return Messages.unnormalMessageOutput("Что-то пошло не так", MessageColor.ANSI_RED);

                }
            } else {
                return Messages.unnormalMessageOutput("Не удалось получить элемент для сравнения.", MessageColor.ANSI_RED);

            }
        } else {
            return Messages.unnormalMessageOutput("В коллекции нет элементов, нечего удалять", MessageColor.ANSI_RED);

        }
        return Messages.unnormalMessageOutput("Все элементы, больше данного - удалены!", MessageColor.ANSI_YELLOW);

    }

    public synchronized String removeKey(Long id, String login) {
        if (getCollection().size() > 0) {
            for (StudyGroup group : getCollection()) {
                if (group.getId().equals(id)) {
                    if (group.getCreator().equals(login)) {
                        if (!IdManager.checkUniq(id)) {
                            IdManager.getHashSetOfIds().add(getCollection().peek().getId());
                            IdManager.removeUsedId(id);
                            getCollection().remove(group);
                            return Messages.unnormalMessageOutput("Элемент с id " + id + " успешно  удален", MessageColor.ANSI_YELLOW);
                        } else {
                            return Messages.unnormalMessageOutput("Такого id нет в коллекции", MessageColor.ANSI_RED);
                        }
                    } else {
                        return Messages.unnormalMessageOutput("Вы не являетесь владельцем этого объекта", MessageColor.ANSI_RED);
                    }

                } else {
                }
            }
        } else {
            return Messages.unnormalMessageOutput("Ваша коллекция пуста", MessageColor.ANSI_RED);
        }
        return Messages.unnormalMessageOutput("Команда выполнена", MessageColor.ANSI_BLUE);
    }

    public String filterAdmin(String groupAdmin) {
        int i = 0;
        ArrayList elements = new ArrayList();
        if (getCollection().size() > 0 && groupAdmin != null) {
            for (StudyGroup group : getCollection()) {
                if (group.getGroupAdmin().getName().equals(groupAdmin)) {
                    i = i + 1;
                    elements.add(group.printInfoAboutElement());
                }
            }
        } else {
            return Messages.unnormalMessageOutput("Ваша коллеция либо пуста либо вы не ввели некорретное имя искомого админа", MessageColor.ANSI_RED);
        }
        return elements.stream().map(x -> x.toString()).collect(Collectors.joining("\n")).toString();
    }

    public String update(Long id, StudyGroup group, String login) {
        if (getCollection().size() > 0 && (group != null) && !IdManager.checkUniq(id)) {
            for (StudyGroup group1 : getCollection()) {
                if (group1.getId().equals(id)) {
                    if (group1.getCreator().equals(login)) {
                        IdManager.getHashSetOfIds().add(getCollection().peek().getId());
                        getCollection().remove(group1);
                        LocalDateTime date = LocalDateTime.now();
                        group.setCreationDate(date);
                        group.setId(id);
                        getCollection().add(group);
                        return Messages.unnormalMessageOutput("Элемент с id " + id + " успешно  обновлен", MessageColor.ANSI_YELLOW);
                    } else {
                        return Messages.unnormalMessageOutput("Вы не являетесь владельцем  объекта " + group1.getId(), MessageColor.ANSI_RED);
                    }
                }
            }
        } else {
            return Messages.unnormalMessageOutput("Вы передали пустую группу, либо такого id нет в коллекции", MessageColor.ANSI_RED);
        }
        return Messages.unnormalMessageOutput("Обновление выполнено", MessageColor.ANSI_YELLOW);
    }

    public String printSemester() {
        ArrayList element = new ArrayList();
        if (getCollection().size() > 0) {
            ArrayList<Semester> uniq_semester = new ArrayList();
            for (StudyGroup group : getCollection()) {
                uniq_semester.add(group.getSemesterEnum());
            }
            Set<Semester> set = new HashSet<>(uniq_semester);
            uniq_semester.clear();
            uniq_semester.addAll(set);
            for (int i = 0; i < uniq_semester.size(); i++) {
                element.add(uniq_semester.get(i));
            }
        } else {
            return Messages.unnormalMessageOutput("Ваша коллекция пуста, выводить нечего", MessageColor.ANSI_RED);
        }
        return element.stream().map(x -> x.toString()).collect(Collectors.joining("\n")).toString();
    }

    public String checkServer() {
        return Messages.unnormalMessageOutput("Cервер готов к передаче информации", MessageColor.ANSI_BLUE);
    }

    /**
     * Логика для add
     */
    public String add(StudyGroup group) {
        if (group != null) {
            LocalDateTime date = LocalDateTime.now();
            group.setCreationDate(date);
            group.setId(IdManager.findUniq(Math.abs(new Random().nextLong())));
            getCollection().add(group);
            return "Элемент добавлен";
        } else {
            return "Элемент не добавлен";
        }
    }

    public String addMin(StudyGroup group) {
        if (getCollection().size() > 0) {
            if (group != null) {
                try {
                    while (true) {
                        if (getCollection().size() > 0 && getCollection().peek().compareTo(group) > 0) {
                            group.setId(IdManager.findUniq(Math.abs(new Random().nextLong())));
                            group.setCreationDate(LocalDateTime.now());
                            getCollection().add(group);
                            return Messages.unnormalMessageOutput("Элемент был добавлен в коллекцию", MessageColor.ANSI_YELLOW);
                        } else {
                            return Messages.unnormalMessageOutput("Элемент не был минимальным.Добавление остановлено", MessageColor.ANSI_RED);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return Messages.unnormalMessageOutput("Что-то пошло не так", MessageColor.ANSI_RED);
                }
            } else {
                return Messages.unnormalMessageOutput("Не удалось получить элемент для сравнения.", MessageColor.ANSI_RED);

            }
        } else {
            return Messages.unnormalMessageOutput("В коллекции нет элементов, нечего удалять", MessageColor.ANSI_RED);

        }
    }

    private void addCommands(Command... commands) {
        for (Command command : commands) {
            commandSet.add(command);
        }
    }

    public PriorityQueue<StudyGroup> getCollection() {
        return collection;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public synchronized String executeScript(ArrayList<String> list) {
        return null;
    }

    public String save() {
        databaseManager.saveCollection(collection);
        return Messages.unnormalMessageOutput("Коллекция сохранена", MessageColor.ANSI_BLUE);
    }
}