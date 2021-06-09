package collection;

import comparators.IdComparator;
import comparators.NameComparator;
import general.Semester;
import general.StudyGroup;
import helpers.FileManager;
import helpers.StudyGroupMaker;
import message.MessageColor;
import message.Messages;

import java.io.BufferedInputStream;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для колекции с объектами StudyGroup и его управлением
 */
public class GeneralColl {
    private FileManager fileManager;
    private final Date date;
    private ZonedDateTime lastInitTime;
    public static PriorityQueue<StudyGroup> collection;

    /**
     * Конструкотор.
     *
     */
    public GeneralColl(PriorityQueue collection) {
        this.fileManager = fileManager;
        this.collection=collection;
        date = new Date();
    }

    /**
     * Выводит справку по доступным командам
     */
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
                "exit : завершить программу (без сохранения в файл) \n",MessageColor.ANSI_CYAN);

    }

    /**
     * Возвращает коллекцию
     *
     * @return коллекция
     */
    public PriorityQueue<StudyGroup> getCollection() {
        return collection;
    }


    /**
     * Метод - вывод информации об коллекции, команда info
     *
     * @return
     */
    public String printInfoAboutCollection() {
        return Messages.unnormalMessageOutput("Тип коллекции - " + getCollection().getClass().getName() + "\n" +
                "Дата иницализации - " + getDate() + "\n" +
                "Кол-во элементов - " + getCollection().size(), MessageColor.ANSI_YELLOW);

    }

    /**
     * Геттер для файла
     *
     * @return файл
     */
    public FileManager getFile() {
        return fileManager;
    }

    /**
     * Читает коллекцию из файла
     */
    public void loadCollection() {
        collection = fileManager.readCollection();
        lastInitTime = ZonedDateTime.now();
    }

    /**
     * Геттер даты
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Очистить коллекцию
     *
     * @return true / false, если размер коллекции больше 0
     */
    public String clearCollection() {
        if (getCollection().size() > 0) {
            IdManager.clearSet();
            getCollection().clear();
            return Messages.unnormalMessageOutput("Все элементы удалены", MessageColor.ANSI_GREEN);
        } else {
            return Messages.unnormalMessageOutput("В колекции нет элементов, нечего стирать",MessageColor.ANSI_RED);
        }
    }

    /**
     * Реализация команды update
     *
     * @param id      id по которому идет обновление элемента
     * @param
     * @return true / false, если выполнилось обновление элемента коллекции
     */
    public String updateElement(Long id, StudyGroup group) {
        if (getCollection().size() > 0 && (group != null)) {
            PriorityQueue<StudyGroup> queue = new PriorityQueue<>(new NameComparator());
            while (!getCollection().peek().getId().equals(id)) {
                queue.add(getCollection().poll());
            }
            LocalDateTime date = LocalDateTime.now();
            group.setCreationDate(date);
            group.setId(id);
            getCollection().add(group);
            while (queue.size() > 0) {
                getCollection().add(queue.poll());
            }
        } else {
            return Messages.unnormalMessageOutput("Такого id не существует",MessageColor.ANSI_RED);
        }
        return Messages.unnormalMessageOutput("Элемент успешно обновлен",MessageColor.ANSI_YELLOW);
    }

    /**
     * Реализация команды add_if_min
     *
     * @return true / false, если выполнилось добавился элемент в колле
     */
    public String add_if_min(StudyGroup group) {
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
                            return Messages.unnormalMessageOutput("Элемент не был минимальным.Добавление остановлено",MessageColor.ANSI_RED);
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
           return  Messages.unnormalMessageOutput("В коллекции нет элементов, нечего удалять", MessageColor.ANSI_RED);

        }

    }

    /**
     * Реализация команды remove_greater
     *
     * @param
     * @return true / false, если выполнилось удаление элементов коллекции
     */
    public String removeGreatest(StudyGroup group) {
        if (getCollection().size() > 0) {
            if (group  != null) {
                try {
                    while (true) {
                        if (getCollection().size() > 0 && getCollection().peek().compareTo(group) > 0) {
                            IdManager.removeUsedId(getCollection().poll().getId());
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                     e.printStackTrace();
                     return  Messages.unnormalMessageOutput("Что-то пошло не так",MessageColor.ANSI_RED);

                }
            } else {
                return Messages.unnormalMessageOutput("Не удалось получить элемент для сравнения.", MessageColor.ANSI_RED);

            }
        } else {
            return  Messages.unnormalMessageOutput("В коллекции нет элементов, нечего удалять", MessageColor.ANSI_RED);

        }
         return Messages.unnormalMessageOutput("Все элементы, больше данного - удалены!", MessageColor.ANSI_YELLOW);

    }

    /**
     * Сортирует коллекцию
     *
     * @param comp компаратор для сортировки
     * @return новую отсортировоннуб коллекцию
     */
    public PriorityQueue<StudyGroup> sortCollectionByComp(Comparator<StudyGroup> comp) {
        List<StudyGroup> newList = new ArrayList<>(collection);
        newList.sort(comp);
        PriorityQueue<StudyGroup> newCollection = new PriorityQueue<>(comp);
        newCollection.addAll(newList);
        return newCollection;
    }

    /**
     * Выводит первый объект коллекции
     *
     * @return true / false, если размер коллекции больше 0
     */
    public String getHeadOfCollection() {
        if (getCollection().size() > 0) {
           return Objects.requireNonNull(getCollection().peek()).printInfoAboutElement();
        } else {
            return Messages.unnormalMessageOutput("В коллекции нет элементов, выводить нечего",MessageColor.ANSI_RED);
        }
    }
    /**
     * Реализация команды show
     *
     * @return true/false если команда выполнилась верно
     */
    public String printAllElements() {
        StringBuilder s = new StringBuilder("");
      if (!collection.isEmpty()){
          Stream<StudyGroup> stream = collection.stream();
         return collection.stream().map(StudyGroup::printInfoAboutElement).collect(Collectors.joining("\n"));}
      else return "Коллекция пуста";
    }


    /**
     * Реализация команды remove_by_id
     *
     * @param id удаление по этому id
     * @return true/false если команда выполнилась верно
     */
    public String removeElementById(Long id) {
        if (getCollection().size() > 0) {
            IdManager.getHashSetOfIds().add(getCollection().peek().getId());
            if (!IdManager.checkUniq(id)) {
                getCollection().removeIf(group-> group.getId().equals(id));
                IdManager.removeUsedId(id);
                return Messages.unnormalMessageOutput("Элемент с id "+id+" успешно  удален",MessageColor.ANSI_YELLOW);
            } else {
                return Messages.unnormalMessageOutput("Такого id нет ",MessageColor.ANSI_RED);
            }
        } else {
            return Messages.unnormalMessageOutput("Ваша коллекция пуста",MessageColor.ANSI_RED);
        }
    }
    /**
     * Реалицаия команды printElementbySemester
     * @return true / false, если размер коллекции больше 0
     */
    public String printElementbySemester() {
        ArrayList element= new ArrayList();
        if (getCollection().size() > 0) {
            ArrayList<Semester> uniq_semester = new ArrayList<Semester>();
            for (StudyGroup group : getCollection()) {
                uniq_semester.add(group.getSemesterEnum());
            }
            Set<Semester> set = new HashSet<>(uniq_semester);
            uniq_semester.clear();
            uniq_semester.addAll(set);
            for(int i=0; i<uniq_semester.size();i++){
                element.add(uniq_semester.get(i));
            }
        } else {
            return Messages.unnormalMessageOutput("Ваша коллекция пуста, выводить нечего",MessageColor.ANSI_RED);
        }
     return element.stream().map(x -> x.toString()).collect(Collectors.joining("\n")).toString();
    }


    /**
     * Реалицаия команды filter_by_group_admin
     * @return true / false, если размер коллекции больше 0
     */
    public String printElementbyGroupAdmin(String groupAdmin) {
         int i=0;
         ArrayList elements= new ArrayList();
        if (getCollection().size()>0 && groupAdmin!=null) {
            for (StudyGroup group : getCollection()) {
                if (group.getGroupAdmin().getName().equals(groupAdmin)) {
                    i=i+1;
                    elements.add(group.printInfoAboutElement());
                }
            }
        }
        else{
           return Messages.unnormalMessageOutput("Ваша коллеция либо пуста либо вы не ввели некорретное имя искомого админа",MessageColor.ANSI_RED);
        }
        return elements.stream().map(x -> x.toString()).collect(Collectors.joining("\n")).toString();
        }

    /**
     * Реализация метода min_by_id
     * @return true / false, если размер коллекции больше 0
     */
    public String findElementWithMinId() {
        if (getCollection().size() > 0) {
            PriorityQueue<StudyGroup> queue = sortCollectionByComp(new IdComparator());
            return Objects.requireNonNull(queue.peek()).printInfoAboutElement();
        } else {
            return Messages.unnormalMessageOutput("Ваша коллекция пуста", MessageColor.ANSI_RED);
        }
    }
    /**
     * Реализация команды add
     *
     * @param
     * @return true / false, если выполнилось добавление элемента
     */
    public String addElement(StudyGroup group) {
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
    /**
     * Записывает коллекцию в файл
     */
    public void saveToFile() {
        fileManager.writeCollection(collection);
    }
}

