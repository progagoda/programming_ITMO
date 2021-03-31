package collection;
import general.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.*;
import java.io.*;
import java.lang.reflect.Type;
/**
 * Класс для колекции с объектами StudyGroup и его управлением
 */
public class GeneralColl {
    /**
     * Поле genCollection, ключи - Integer, значения - HumanBeing
     */
    private PriorityQueue<StudyGroup> genCollection = new PriorityQueue<>();

    /**
     * Чтение данных из файла HumanBeing.json генерация id происходит автоматически в диапазоне от 0 до 10000
     *
     * @throws IOException ошибка пользовательского ввода
     */
    public GeneralColl() throws IOException {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("StudyGroup.json")) {
            Type foundMap = new TypeToken<PriorityQueue>(){}.getType();
            genCollection = gson.fromJson(reader, foundMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HashSet<String> invalidID = new HashSet<>();
        HashSet<String> newID = new HashSet<>();
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
     * Геттер genCollection
     *
     * @return возвращает коллекцию
     */
    public PriorityQueue<StudyGroup> getGeneralColl() {
        return genCollection;
    }

    /**
     * Считывает строку, введенную пользователем
     *
     * @return строка, введенная пользователем
     */
    public String scanLine() throws IOException {
        BufferedReader im = new BufferedReader(new InputStreamReader(System.in));
        String ups = im.readLine();
        while (ups.isEmpty()) {
            System.out.println("Вы ввели пустую строку. Пожалуйста, повторите ввод.");
            ups = im.readLine();
        }
        return ups;
    }

    /**
     * Считывает поле studentsCount
     *
     * @return long (количество студентов)
     */
    public Long scanStudentsCount() {
        System.out.println("Введите количество студентов в группе (`Должно быть >0): ");
        long StudentsCount = 1;
        boolean as = true;
        while (as) {
            try {
                StudentsCount = Long.parseLong(scanLine());
                if (StudentsCount < 1) {
                    System.out.println("Количество студентов в группе  должно быть больше 0.\nВведите количество студентов: ");
                } else {
                    as = false;
                }
            } catch (NullPointerException | IOException e) {
                System.out.println("Количество студентов должно быть введено .\\nВведите количество студентов:");
            }
        }
        return StudentsCount;
    }

    /**
     * Считывает поле name
     *
     * @return name
     */
    public String scanNameGroup() throws IOException {
        System.out.println("Введите название группы:");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String go = in.readLine();
        while (go.isEmpty()) {
            System.out.println("Название не может быть пустым! Пожалуйста, введите название:");
            go = in.readLine();
        }
        return go;
    }

    /**
     * Считывает поле ExpelledStudents
     *
     * @return long (количество исключенных студентов)
     */
    public Long scanExpelledStudents() {
        System.out.println("Введите количество исключенных студентов в группе (`Должно быть >0): ");
        long ExpelledStudents = 1;
        boolean as = true;
        while (as) {
            try {
                ExpelledStudents = Long.parseLong(scanLine());
                if (ExpelledStudents < 1) {
                    System.out.println("Количество студентов в группе  должно быть больше 0.\nВведите количество студентов: ");
                } else {
                    as = false;
                }
            } catch (NullPointerException | IOException e) {
                System.out.println("Количество студентов должно быть введено .\\nВведите количество студентов:");
            }
        }
        return ExpelledStudents;
    }

    /**
     * Считывает координаты
     *
     * @return координаты
     */
    public Coordinates scanCoordinates() {
        Long inputX = null;
        Float inputY = null;
        while (inputX == null) {
            System.out.println("Введите х координату: ");
            try {
                inputX = Long.parseLong(scanLine());
            } catch (NullPointerException | IOException e) {
                System.out.println("Координата х должна быть целым числом.");
            }
        }
        System.out.println("Введите у координату: ");
        while (inputY == null) {
            try {
                inputY = Float.parseFloat(scanLine());
            } catch (NullPointerException | IOException e) {
                System.out.println("Координата у должна быть целым числом.");
            }
        }
        Coordinates coordinates = new Coordinates(inputX, inputY);
        return coordinates;
    }

    /**
     * Cчитывает форму обучения
     *
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
     * Считывает номер семместра
     *
     * @return semesterEnum
     * @throws IOException
     */
    public Semester scanSemesterEnum() throws IOException {
        Semester semesterEnum = null;
        boolean dw = true;
        while (dw) {
            System.out.println("Введите номер семместра :\n" + "first\n" + "second\n" + "third\n" + "eight\n");
            InputStreamReader sr = new InputStreamReader(System.in); // создать экземпляр InputStreamReader
            BufferedReader br = new BufferedReader(sr); // экземпляр класса буферизации
            String s = br.readLine();
            switch (s) {
                case "first":
                    semesterEnum = Semester.FIRST;
                    dw = false;
                    break;
                case "second":
                    semesterEnum = Semester.SECOND;
                    dw = false;
                    break;
                case "third":
                    semesterEnum = Semester.THIRD;
                    dw = false;
                    break;
                case "eight":
                    semesterEnum = Semester.EIGHTH;
                    dw = false;
                    break;
                case "":
                    semesterEnum = null;
                    dw = false;
                    break;
                default:
                    System.out.println("Вы ввели некорректный номер семместра");
                    break;

            }


        }
        return semesterEnum;
    }

    /**
     * Cчитывает всю инофрмацию об админе
     *
     * @return person
     * @throws IOException
     */
    public Person scanGroupAdmin() throws IOException {
        Person person = new Person();
        System.out.println("Введите имя старосты:");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String name = in.readLine();
        while (name.isEmpty()) {
            System.out.println("Имя старосты не может быть пустым, введите пожалуйста имя");
            name = scanLine();
        }
        person.setName(name);
        System.out.println("Введите id паспорта админа:");
        BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
        String id = in1.readLine();
        while (id.isEmpty()) {
            System.out.println("Имя старосты не может быть пустым, введите пожалуйста имя");
            id = scanLine();
        }
        person.setPassportID(id);
        Color colorEnum = null;//начало считывания цвета глаз
        boolean dw = true;
        while (dw) {
            System.out.println("Введите цвет глаз :\n" + "black\n" + "blue\n" + "orange\n" + "white\n" + "brown\n" + "green\n");
            InputStreamReader sr = new InputStreamReader(System.in); // создать экземпляр InputStreamReader
            BufferedReader br = new BufferedReader(sr); // экземпляр класса буферизации
            String s = br.readLine();
            switch (s) {
                case "black":
                    colorEnum = Color.BLACK;
                    dw = false;
                    break;
                case "blue":
                    colorEnum = Color.BLUE;
                    dw = false;
                    break;
                case "orange":
                    colorEnum = Color.ORANGE;
                    dw = false;
                    break;
                case "white":
                    colorEnum = Color.WHITE;
                    dw = false;
                    break;
                case "green":
                    colorEnum = Color.GREEN;
                    dw = false;
                    break;
                case "brown":
                    colorEnum = Color.BROWN;
                    dw = false;
                    break;
                case "":
                    while (s.isEmpty()) {
                        System.out.println("Цвет глаз старосты не может быть пустым,выберете цвет глаз");
                        s = scanLine();
                        break;
                    }

                default:
                    System.out.println("Вы ввели некорректный цвет глаз");
                    break;
            }
        }
        Color colorEnum1 = null;//начало считывания цвета волос
        boolean dw1 = true;
        while (dw1) {
            System.out.println("Введите цвет волос :\n" + "black\n" + "blue\n" + "orange\n" + "white\n" + "brown\n" + "green\n");
            InputStreamReader sr1 = new InputStreamReader(System.in); // создать экземпляр InputStreamReader
            BufferedReader br1 = new BufferedReader(sr1); // экземпляр класса буферизации
            String s1 = br1.readLine();
            switch (s1) {
                case "black":
                    colorEnum = Color.BLACK;
                    dw1 = false;
                    break;
                case "blue":
                    colorEnum = Color.BLUE;
                    dw1 = false;
                    break;
                case "orange":
                    colorEnum = Color.ORANGE;
                    dw1 = false;
                    break;
                case "white":
                    colorEnum = Color.WHITE;
                    dw1 = false;
                    break;
                case "green":
                    colorEnum = Color.GREEN;
                    dw1 = false;
                    break;
                case "brown":
                    colorEnum = Color.BROWN;
                    dw1 = false;
                    break;
                case "":
                    while (s1.isEmpty()) {
                        System.out.println("Цвет волос старосты не может быть пустым,выберете цвет глаз");
                        s1 = scanLine();
                        break;
                    }
                default:
                    System.out.println("Вы ввели некорректный цвет глаз");
                    break;
            }
        }
        Country countryEnum = null;//начало считывания страны происхождения
        boolean dw3 = true;
        while (dw3) {
            System.out.println("Введите страну проихожжения старосты :\n" + "usa\n" + "spain\n" + "vatican\n" + "japan\n");
            InputStreamReader sr3 = new InputStreamReader(System.in); // создать экземпляр InputStreamReader
            BufferedReader br3 = new BufferedReader(sr3); // экземпляр класса буферизации
            String s3 = br3.readLine();
            switch (s3) {
                case "usa":
                    countryEnum = Country.USA;
                    dw3 = false;
                    break;
                case "spain":
                    countryEnum = Country.SPAIN;
                    dw3 = false;
                    break;
                case "vatican":
                    countryEnum = Country.VATICAN;
                    dw = false;
                    break;
                case "japan":
                    countryEnum = Country.JAPAN;
                    dw = false;
                    break;
                case "":
                    while (s3.isEmpty()) {
                        System.out.println("Страна проихождения старосты не может быть пустым,выберете страну происхождения");
                        s3 = scanLine();
                        break;
                    }
                default:
                    System.out.println("Вы ввели некорректную страну происхождения");
                    break;
            }
        }
        return person;
    }

        /**
         * Добавляет новый элемент в коллекцию
         */
        public void addStudyGroup(int key) throws IOException {
            {  // метода для создания нового обьекта и помещение его в коллекцию
                StudyGroup studyGroup1 = new StudyGroup(scanNameGroup(), scanCoordinates(), scanStudentsCount(), scanExpelledStudents(), scanFormOfEducation(), scanSemesterEnum(),scanGroupAdmin());
                getGeneralColl().remove(key);
                getGeneralColl().add(studyGroup1);
            }
        }
    /**
     * Удаляет все элементы из коллекции
     */
    public void clear() {
        if (genCollection.size() == 0) {
            System.out.println("Коллекция уже пуста");
        } else {
            genCollection.clear();
        }
    }
    /**
     * Выводит информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     */
    public void info() {
      System.out.println("Тип: StudyGroup\n"
            + "Дата инициализации: " + genCollection.peek().getCreationDate().now() + '\n'
          + "Количество элементов: " + genCollection.size());
    }
    /**
     * Сохраняет коллекцию в файл afterHumanBeing.json
     */
    public void save() {
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("afterStudyGroup.json"))) {
            Gson gson = new Gson();
            String json = gson.toJson(genCollection);
            stream.write(json.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void show() {
        if (!genCollection.isEmpty()) {
            for (StudyGroup studyGroup : getGeneralColl()) {
                System.out.println(studyGroup.toString());
            }
        } else {
            System.out.println("Коллекция пуста.");
        }
    }
}

