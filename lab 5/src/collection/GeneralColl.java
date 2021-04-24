package collection;

import comparators.IdComparator;
import comparators.NameComparator;
import general.Semester;
import helpers.FileManager;
import helpers.Messages;
import general.StudyGroup;
import helpers.StudyGroupMaker;

import java.time.ZonedDateTime;
import java.util.*;

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
     * @param fileManager название Json файла, который будет считыватся для дальнейших действий
     */
    public GeneralColl(FileManager fileManager) {
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
     * Реализация команды update
     *
     * @param id      id по которому идет обновление элемента
     * @param scanner Сканнер
     * @return true / false, если выполнилось обновление элемента коллекции
     */
    public boolean updateElement(Long id, Scanner scanner) {
        StudyGroup group;
        if (!IdManager.checkUniq(id) && getCollection().size() > 0 && (group = new StudyGroupMaker().makeGroup(scanner)) != null) {
            PriorityQueue<StudyGroup> queue = new PriorityQueue<>(new NameComparator());
            while (!getCollection().peek().getId().equals(id)) {
                queue.add(getCollection().poll());
            }
            ZonedDateTime date = ZonedDateTime.now();
            group.setCreationDate(date);

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
     * Реализация команды add_if_min
     *
     * @param scanner Сканнер
     * @return true / false, если выполнилось добавился элемент в колле
     */
    public boolean add_if_min(Scanner scanner) {
        StudyGroup group;
        if (getCollection().size() > 0) {
            group = new StudyGroupMaker().makeGroup(scanner);
            if (group != null) {
                try {
                    while (true) {
                        if (getCollection().size() > 0 && getCollection().peek().compareTo(group)>0) {
                            group.setId(IdManager.findUniq(Math.abs(new Random().nextLong())));
                            group.setCreationDate(ZonedDateTime.now());
                            getCollection().add(group);
                            Messages.normalMessageOutput("Элемент был добавлен в коллекцию");
                            return  true;
                        } else {
                            System.out.println("Элемент не был минимальным.Добавление остановлено");
                            return false;
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

    }

    /**
     * Реализация команды remove_greater
     *
     * @param scanner Сканнер
     * @return true / false, если выполнилось удаление элементов коллекции
     */
    public boolean removeGreatest(Scanner scanner) {
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
        Messages.normalMessageOutput("Все элементы, больше данного - удалены!");
        return true;
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
     *
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
     * Реалицаия команды printElementbySemester
     * @return true / false, если размер коллекции больше 0
     */
    public boolean printElementbySemester() {
        if (getCollection().size() > 0) {
            ArrayList<Semester> uniq_semester = new ArrayList<Semester>();
            for (StudyGroup group : getCollection()) {
                uniq_semester.add(group.getSemesterEnum());
            }
            Set<Semester> set = new HashSet<>(uniq_semester);
            uniq_semester.clear();
            uniq_semester.addAll(set);
            for(int i=0; i<uniq_semester.size();i++){
                System.out.println(uniq_semester.get(i));
            }
        } else {
            Messages.normalMessageOutput("Ваша коллекция пуста, выводить нечего");
            return  false;
        }
        return  true;
    }


    /**
     * Реалицаия команды filter_by_group_admin
     * @return true / false, если размер коллекции больше 0
     */
    public boolean printElementbyGroupAdmin(String groupAdmin) {
         int i=0;
        if (getCollection().size()>0 && groupAdmin!=null) {
            for (StudyGroup group : getCollection()) {
                if (group.getGroupAdmin().getName().equals(groupAdmin)) {
                    i=i+1;
                    group.printInfoAboutElement();
                }
                else {
                    i=i+1;
                    System.out.println("Объект №"+i+" не подходит");
                }
            }
        }
        else{
            Messages.normalMessageOutput("Ваша коллеция либо пуста либо вы не ввели некорретное имя искомого админа");
        }
        return  true;
        }

    /**
     * Реализация метода min_by_id
     * @return true / false, если размер коллекции больше 0
     */
    public boolean findElementWithMinId() {
        if (getCollection().size() > 0) {
            PriorityQueue<StudyGroup> queue = sortCollectionByComp(new IdComparator());
            Objects.requireNonNull(queue.peek()).printInfoAboutElement();
            return true;
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
            ZonedDateTime date = ZonedDateTime.now();
            group.setCreationDate(date);
            group.setId(IdManager.findUniq(Math.abs(new Random().nextLong())));
            getCollection().add(group);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Записывает коллекцию в файл
     */
    public void saveToFile() {
        fileManager.writeCollection(collection);
    }
}

