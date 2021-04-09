package collection;
import comparators.NameComparator;
import helpers.FileManager;
import helpers.Messages;
import general.StudyGroup;
import helpers.StudyGroupMaker;

import java.time.ZonedDateTime;
import java.util.*;
import java.io.*;

/**
 * Класс для колекции с объектами StudyGroup и его управлением
 */
public class GeneralColl {
    private  FileManager fileManager;
    private  PriorityQueue<StudyGroup> collection;
    private final Date date;
    private ZonedDateTime lastInitTime;

    /**
     * Конструкотор.
     *
     * @param fileName название Json файла, который будет считыватся для дальнейших действий
     */
    public GeneralColl(FileManager fileName) {
        this.fileManager = fileManager;
        collection = new PriorityQueue<>(new NameComparator());
        date = new Date();
    }

    /**
     * Выводит справку по доступным командам
     */
    public static void help() {
        System.out.println("help : вывести справку по доступным командам \n" +
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
                "exit : завершить программу (без сохранения в файл) \n");

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
     */
    public void printInfoAboutCollection() {
        Messages.normalMessageOutput("Тип коллекции - " + getCollection().getClass().getName() + "\n" +
                "Дата иницализации - " + getDate() + "\n" +
                "Кол-во элементов - " + getCollection().size());

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
    public boolean clearCollection() {
        if (getCollection().size() > 0) {
            IdManager.clearSet();
            getCollection().clear();
            return true;
        } else {
            return false;
        }
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
    public boolean getHeadOfCollection() {
        if (getCollection().size() > 0) {
            Objects.requireNonNull(getCollection().peek()).printInfoAboutElement();
            return true;
        } else {
            return false;
        }
    }
    /**
     * Реализация команды show
     * @return true/false если команда выполнилась верно
     */
    public boolean printAllElements() {
        if (getCollection().size() > 0) {
            getCollection().forEach((StudyGroup::printInfoAboutElement));
            return true;
        } else {
            return false;
        }
    }
    /**
     * Реализация команды remove_by_id
     *
     * @param id удаление по этому id
     * @return true/false если команда выполнилась верно
     */
    public boolean removeElementById(Long id) {
        if (getCollection().size() > 0) {
            if (!IdManager.checkUniq(id)) {
                getCollection().removeIf(flat -> flat.getId().equals(id));
                IdManager.removeUsedId(id);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Реализация команды add
     *
     * @param scanner Сканнер
     * @return true / false, если выполнилось добавление элемента
     */
    public boolean addElement(Scanner scanner) {
        StudyGroup group = new StudyGroupMaker().makeGroup(scanner);
        if (group != null) {
            group.setId(IdManager.findUniq(Math.abs(new Random().nextLong())));
            getCollection().add(group);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Реализация команды update
     *
     * @param id      id по которому идет обновление элемента
     * @param scanner Сканнер
     * @return true / false, если выполнилось обновление элемента коллекции
     */
    public boolean updateId(Long id, Scanner scanner) {
        StudyGroup group;
        if (!IdManager.checkUniq(id) && getCollection().size() > 0 && (group = new StudyGroupMaker().makeGroup(scanner)) != null) {
            PriorityQueue<StudyGroup> queue = new PriorityQueue<>(new NameComparator());
            while (!getCollection().peek().getId().equals(id)) {
                queue.add(getCollection().poll());
            }
            /*try {
                group.setDate(new SimpleTimeZone(Format("HH:mm:ss.SSS dd-MM-yyyy").parse(getCollection().poll().getCreationDate()));
            } catch (ParseException e) {
                Messages.normalMessageOutput("Какая-то проблема с датой ?");
                return false;
            }*/

            group.setId(id);
            getCollection().add(group);
            while (queue.size() > 0) {
                getCollection().add(queue.poll());
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Реализация команды remove_greater
     *
     * @param scanner Сканнер
     * @return true / false, если выполнилось удаление элементов коллекции
     */
    public boolean remove_greater(Scanner scanner) {
        StudyGroup group;
        if (getCollection().size() > 0) {
            if ((group = new StudyGroupMaker().makeGroup(scanner)) != null) {
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
                    return false;
                }
            } else {
                Messages.normalMessageOutput("Не удалось получить элемент для сравнения.");
                return false;
            }
        } else {
            Messages.normalMessageOutput("В коллекции нет элементов, нечего удалять");
            return false;
        }
        Messages.normalMessageOutput("Все элементы, выше данного - удалены!");
        return true;
    }
}

