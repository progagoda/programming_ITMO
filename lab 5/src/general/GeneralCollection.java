package general;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.*;
/**
 * Класс для дерева с объектами StudyGroup и его управлением
 */
public class GeneralCollection {
    /**Поле genCollection, ключи - Integer, значения - HumanBeing*/
    private TreeMap<Integer, StudyGroup> genCollection = new TreeMap<>();
    /**
     * Чтение данных из файла HumanBeing.json генерация id происходит автоматически в диапазоне от 0 до 10000
     * @throws IOException ошибка пользовательского ввода
     */
    public GeneralCollection() throws IOException {
        Gson gson = new Gson();
        try (BufferedReader reader = new BufferedReader(new FileReader("StudyGroup.json"))) {
            Type foundMap = new TypeToken<TreeMap<Integer, StudyGroup>>(){}.getType();
            genCollection = gson.fromJson(reader, foundMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
     * Выводит информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     */
    public  void info(){System.out.println("Тип: StudyGroup\n"
            + "Дата инициализации: " + genCollection.get(0).getCreationDate().now() + '\n'
            + "Количество элементов: " + genCollection.size());}
    /**
     * Выводит все элементы коллекции в строковом представлении
     */
    public  void show(){
        if (!genCollection.isEmpty()) {
            for (Integer key : genCollection.keySet()) {
                System.out.println("Key: " + key + " Value: " + genCollection.get(key).toString());
            }
        } else {
            System.out.println("Коллекция пуста.");
        }
    }
    /**
     * Добавляет новый элемент в коллекцию
     * @param key Ключ
     * @throws IOException
     */
    public  void add(int key) throws IOException {
        StudyGroup humanBeing1 = new StudyGroup(scanName(), scanCoordinates(), scanStudentsCount(), scanExpelledStudents(), scanFormOfEducation(), scanSemesterEnum(), scanGroupAdmin());
        getGenCollection().remove(key);
        getGenCollection().put(key, humanBeing1);
    }
    /**
     * Считывает поле name
     * @return name
     */
    public String scanName() {
        System.out.println("Введите имя:");
        Scanner in = new Scanner(System.in);
        String go = in.nextLine();
        while (go.isEmpty()) {
            System.out.println("Имя не может быть пустым! Пожалуйста, введите имя:");
            go = in.nextLine();
        }
        return go;
    }
    /**
     * Считывает строку, введенную пользователем
     * @return строка, введенная пользователем
     */
    public String scanLine() {
        Scanner im = new Scanner(System.in);
        String ups = im.nextLine();
        while (ups.isEmpty()) {
            System.out.println("Вы ввели пустую строку. Пожалуйста, повторите ввод.");
            ups = im.nextLine();
        }
        return ups;
    }
    /**
     * Считывает координаты
     * @return координаты
     */
    public Coordinates scanCoordinates() {
        Long inputX = null;
        Float inputY = null;
        while (inputX == null) {
            System.out.println("Введите х координату: ");
            try {
                inputX = Long.parseLong(scanLine());
            } catch (NullPointerException e) {
                System.out.println("Координата х должна быть целым числом.");
            }
        }
        System.out.println("Введите у координату: ");
        while (inputY == null) {
            try {
                inputY = Float.parseFloat(scanLine());
            } catch (NullPointerException e) {
                System.out.println("Координата у должна быть целым числом.");
            }
        }
        Coordinates coordinates = new Coordinates(inputX, inputY);
        return coordinates;
    }
    /**
     * Считывает поле studentsCount
     * @return long (количество студентов)
     */
    public Long scanStudentsCount(){
        System.out.println("Введите количество студентов в группе (`Должно быть >0): ");
        long StudentsCount = 1;
        boolean as = true;
        while (as){
            try{
                StudentsCount = Long.parseLong(scanLine());
                if (StudentsCount<1){
                    System.out.println("Количество студентов в группе  должно быть больше 0.\nВведите количество студентов: ");
                }
                else {
                    as=false;
                }
            }
            catch (NullPointerException e){
                System.out.println("Количество студентов должно быть введено .\\nВведите количество студентов:");
            }
        }
        return StudentsCount;
    }

    /**
     * Считывает поле ExpelledStudents
     * @return long (количество исключенных студентов)
     */
    public Long scanExpelledStudents(){
        System.out.println("Введите количество исключенных студентов в группе (`Должно быть >0): ");
        long ExpelledStudents = 1;
        boolean as = true;
        while (as){
            try{
                ExpelledStudents = Long.parseLong(scanLine());
                if (ExpelledStudents<1){
                    System.out.println("Количество студентов в группе  должно быть больше 0.\nВведите количество студентов: ");
                }
                else {
                    as=false;
                }
            }
            catch (NullPointerException e){
                System.out.println("Количество студентов должно быть введено .\\nВведите количество студентов:");
            }
        }
        return ExpelledStudents;
    }

    /**
     * Cчитывает форму обучения
     * @return formofEducation
     * @throws IOException
     */
    public FormOfEducation scanFormOfEducation() throws IOException {
        FormOfEducation formofEducation = null;
        boolean dw = true;
        while (dw) {
            System.out.println("Введите форму обучения:\n" + "distance_education\n" + "full_time_education\n" + "evening_classes\n");
            InputStreamReader sr = new InputStreamReader(System.in); // создать экземпляр InputStreamReader
            BufferedReader br = new BufferedReader(sr); // экземпляр класса буферизации
            String s = br.readLine();
            switch (s) {
                case "distance_education":
                    formofEducation = FormOfEducation.DISTANCE_EDUCATION;
                    dw = false;
                    break;
                case "full_time_education":
                    formofEducation = FormOfEducation.FULL_TIME_EDUCATION;
                    dw = false;
                    break;
                case "evening_classes":
                    formofEducation = FormOfEducation.EVENING_CLASSES;
                    dw = false;
                    break;
                case "":
                    formofEducation = null;
                    dw = false;
                    break;
                default:
                    System.out.println("Вы ввели некорректную форму обучения");
                    break;

            }


        }
        return formofEducation;
    }

    /**
     *  Считывает номер семместра
     * @return semesterEnum
     * @throws IOException
     */
    public Semester scanSemesterEnum() throws IOException {
        Semester semesterEnum= null;
        boolean dw = true;
        while (dw) {
            System.out.println("Введите номер семместра :\n" + "first\n" + "second\n" + "third\n"+"eight\n");
            InputStreamReader sr = new InputStreamReader(System.in); // создать экземпляр InputStreamReader
            BufferedReader br = new BufferedReader(sr); // экземпляр класса буферизации
            String s = br.readLine();
            switch (s) {
                case "first":
                    semesterEnum = Semester.FIRST;
                    dw = false;
                    break;
                case "second":
                    semesterEnum  = Semester.SECOND;
                    dw = false;
                    break;
                case "third":
                    semesterEnum  = Semester.THIRD;
                    dw = false;
                    break;
                case "eight":
                    semesterEnum  = Semester.EIGHTH;
                    dw = false;
                    break;
                case "":
                    semesterEnum= null;
                    dw = false;
                    break;
                default:
                    System.out.println("Вы ввели некорректный номер семместра");
                    break;

            }


        }
        return semesterEnum;
    }

    public String scanGroupAdmin() throws IOException {
        System.out.println("Введите имя старосты:");
        InputStreamReader sr = new InputStreamReader(System.in); // создать экземпляр InputStreamReader
        BufferedReader br = new BufferedReader(sr); // экземпляр класса буферизации
        String s = br.readLine();
        boolean as= true;
        while(as){
            try {
                if (s.isEmpty()) {
                    System.out.println("Имя не может быть пустым! Пожалуйста, введите имя:");
                    s = br.readLine();
                } else {
                    as = false;
                }
            }
            catch (NullPointerException e){
                System.out.println("Имя не может быть null");
            }

    }
        return s;
    }
    public  void updateId(int element){// метода для создания нового обьекта и помещение его в коллекцию

    }
    /**
     * Удаляет элемент коллекции, id которого равен введенному
     * @param id ключ
     */
    public  void remove_by_id(int id){
        genCollection.remove(id);
    }
    /**
     * Геттер genCollection
     * @return возвращает коллекцию
     */
    public TreeMap<Integer, StudyGroup> getGenCollection() {
        return genCollection;
    }
    /**
     * Удаляет все элементы из коллекции
     */
    public  void clear(){if (genCollection.size() == 0) {
        System.out.println("Коллекция уже пуста");
    } else {
        genCollection.clear();
    }}
    public  void save(){}
    public  void execute_script( String file_name){}
    /**
     * Завершает программу (без сохранения в файл)
     */
    public void exit() {
        System.exit(0);
    }
    public  void head(){}
    public  void add_if_min(String element){}
    public  void remove_greater(String element){}
    public  void min_by_id(){}
    public  void filter_by_group_admin(String groupAdmin){}
    public  void print_unique_semester_enum(){}

}