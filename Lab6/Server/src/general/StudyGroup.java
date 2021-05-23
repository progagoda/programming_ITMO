package general;

import collection.GeneralColl;
import com.sun.xml.internal.bind.marshaller.MinimumEscapeHandler;
import message.MessageColor;
import message.Messages;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;


public class StudyGroup implements Comparable<StudyGroup>, Serializable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private long expelledStudents; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле не может быть null
    private static final long serialVersionUID = -4333316296251054416L;

    /**
     * Геттер id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Геттер name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер x
     *
     * @return x
     */
    public Long getX() {
        return coordinates.getX();
    }

    /**
     * Геттер y
     *
     * @return y
     */
    public Float getY() {
        return coordinates.getY();
    }

    /**
     * Генерирует значения id (0-10000)
     *
     * @return сгенерировнный id
     */
    public Integer randomId() {
        Random random = new Random();
        Integer num = random.nextInt(100000);
        return num;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Геттер creationDate
     *
     * @return creationDate
     */
    public String getCreationDate() {
        return creationDate.toString();
    }
        /**
     * Cеттер id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public boolean setCreationDate(LocalDateTime creationDate) {
        if (creationDate == null) {
            return false;
        } else {
            this.creationDate = creationDate;
            return true;
        }
    }


    public boolean setName(String name) {
        if (name == null || name.equals("")) {
            Messages.normalMessageOutput("Name can't be null", MessageColor.ANSI_RED);
            return false;
        } else {
            this.name = name;
            return true;
        }
    }

    /**
     * Сеттер для x
     *
     * @param x x
     * @return
     */
    public boolean setX(Long x) {
        coordinates.setX(x);
        return true;
    }

    /**
     * Сеттер для y
     *
     * @param y x
     */
    public boolean setY(Float y) {
        coordinates.setY(y);
        return  true;
    }


    /**
     * Геттер studentsCount
     *
     * @return studentsCount
     */
    public Long getStudentsCount() {
        return studentsCount;
    }

    /**
     * Геттер expelledStudents
     *
     * @return expelledStudents
     */
    public long getExpelledStudents() {
        return expelledStudents;
    }

    /**
     * Геттер formOfEducation
     *
     * @return formOfEducation
     */
    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    /**
     * Геттер semesterEnum
     *
     * @return semesterEnum
     */
    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    /**
     * Геттер groupAdmin
     *
     * @return groupAdmin
     */
    public Person getGroupAdmin() {
        return groupAdmin;
    }

    /**
     * Сеттер studentsCount
     *
     * @param studentsCount studentsCount
     */
    public boolean setStudentsCount(Long studentsCount) {
       if (studentsCount > 0 && !studentsCount.equals("")) {
            this.studentsCount = studentsCount;
            return true;
        }
        else {
            if(studentsCount < 0){
                Messages.normalMessageOutput("Количество студентов в группе должно быть больше 0", MessageColor.ANSI_RED);
            }
            if(studentsCount.equals("")){
                Messages.normalMessageOutput("Количество студентов не может быть null", MessageColor.ANSI_RED);
            }
            return false;
        }
    }

    public boolean setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return true;
    }
    public boolean write_coordinatesX(Long x){
      return   coordinates.setX(x);

    }
    public boolean write_coordinatesY(Float y){
        return  coordinates.setY(y);

    }
    /**
     * Сеттер expelledStudents
     *
     * @param expelledStudents expelledStudents
     */
    public boolean setExpelledStudents(long expelledStudents) {
        if (expelledStudents > 0 && expelledStudents<getStudentsCount()) {
            this.expelledStudents = expelledStudents;
            return true;
        } else {
            if (expelledStudents >= getStudentsCount()) {
                Messages.normalMessageOutput("Количество отчисленных не должно превышать или равняться общему кол-ву студентов в группе", MessageColor.ANSI_RED);
            }
            if (expelledStudents <= 0){
                Messages.normalMessageOutput("Количество отчисленных должно быть больше нуля", MessageColor.ANSI_RED);
            }
            return false;
        }
        }

    /**
     * Сеттер formOfEducation
     *
     * @param formOfEducation formOfEducation
     */

    /**
     * Сеттер formOfEducation
     *
     * @param formOfEducation formOfEducation
     */
    public boolean setFormOfEducation(FormOfEducation formOfEducation) {
        if (formOfEducation == null) {
            return false;
        } else {
            this.formOfEducation = formOfEducation;
            return true;
        }
    }

    /**
     * Сеттер semesterEnum
     *
     * @param semesterEnum semesterEnum
     */
    public boolean setSemesterEnum(Semester semesterEnum) {
        if (semesterEnum == null) {
            return false;
        } else {
            this.semesterEnum = semesterEnum;
            return true;
        }
    }

    /**
     * Сеттер groupAdmin
     *
     * @param groupAdmin groupAdmin
     */
    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }


    public String printInfoAboutElement() {
       try {
           return
           Messages.unnormalMessageOutput("id - " + id + "\n" +
                   "Name- " + name + "\n" +
                   "Координаты x и y - " + coordinates.getX() + ", " + coordinates.getY() + "\n" +
                   "Дата создания - " + creationDate + "\n" +
                   "Количество студентов - " + studentsCount + "\n" +
                   "Отчисленные студенты - " + expelledStudents + "\n" +
                   "Форма обучения - " + formOfEducation + "\n" +
                   "Номер семместра - " + semesterEnum + "\n" +
                   "Староста группы - " + groupAdmin + "\n", MessageColor.ANSI_CYAN);
       }
       catch (NullPointerException e){
           return "Ваш объект с id="+getId()+" записан некорректно и поэтому был пропущен";
       }
    }
    public StudyGroup check_fields(StudyGroup group) {//это ерунда она нигде не используется
        String line;
        Scanner scanner = new Scanner(System.in);
        if (getName().equals(null)) {
            System.out.println("Незаполнено поле Name");
        }
        if (getStudentsCount().equals(null)) {
            System.out.println("Незаполнено поле studentCount у группы с id " + getId());
        }
        if (getExpelledStudents() == 0) {
            System.out.println("Незаполнено поле ExpelledStudents у группы с id " + getId());
            while (true) {
                System.out.println("Хотите заполнить данный объект?В ином случае он не загрузиться(true/false)");
                try {
                    if (scanner.hasNextLine()) {
                        line = scanner.nextLine().trim();
                    } else {
                        return null;
                    }
                    if (line.equals("true")) {
                        System.out.print("Введите значение для поля expelledStudents: ");
                        line = scanner.nextLine().trim();
                        if (line.equals("end")) {
                            System.out.println("Добавление элемента остановлено.");
                            return null;
                        }
                        if (setExpelledStudents(Long.valueOf(line))) {
                            System.out.println("Поле expelledStudents дописано");
                            break;
                        }
                    }
                    if (line.equals("false")) {
//                        StudyGroup group1=new StudyGroup();
//                        group1.setId(getId());
                        GeneralColl.collection.removeIf(group1 -> group.getId().equals(id));
//                        IdManager.removeUsedId(id);
//                        IdManager.getHashSetOfIds().add(GeneralColl.collection.element().getId());
//                        IdManager.removeUsedId(GeneralColl.collection.element().getId());
//                        GeneralColl.collection.remove(GeneralColl.collection.element());
                    System.out.println("Тк вы не заполнили поле объект был пропущен");
                    break;
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка ввода поля expelledStudents, попробуйте еще раз или напишите end");
                }
            }
        }
        return group;
    }

    /**
     * Метод сравнения 2 объектов класса StudyGroup
     */
    @Override
    public int compareTo(StudyGroup o) {
        try {
            return this.getName().compareTo(o.getName());
        }
        catch (NullPointerException e){
            Messages.normalMessageOutput("Для сортировки должно быть введено поле Name", MessageColor.ANSI_RED);
            return 0;
        }
    }
}


