package general;

import helpers.Messages;
import helpers.StudyGroupMaker;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class StudyGroup implements Comparable<StudyGroup> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private long expelledStudents; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле не может быть null
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
            Messages.normalMessageOutput("Name can't be null");
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
     */
    public void setX(Long x) {
        coordinates.setX(x);
    }

    /**
     * Сеттер для y
     *
     * @param y x
     */
    public void setY(Float y) {
        coordinates.setY(y);
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
        } else {
            if(studentsCount < 0){
                Messages.normalMessageOutput("Количество студентов в группе должно быть больше 0");
            }
            if(studentsCount.equals("")){
                Messages.normalMessageOutput("Количество студентов не может быть null");
            }
            return false;
        }
    }

    public boolean setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return true;
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
                Messages.normalMessageOutput("Количество отчисленных не должно превышать или равняться общему кол-ву студентов в группе");
            }
            if (expelledStudents <= 0){
                Messages.normalMessageOutput("Количество отчисленных должно быть больше нуля");
            }
            return false;
        }
        }


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


    public void printInfoAboutElement() {
       try {
           Messages.normalMessageOutput("id - " + id + "\n" +
                   "Name- " + name + "\n" +
                   "Координаты x и y - " + coordinates.getX() + ", " + coordinates.getY() + "\n" +
                   "Дата создания - " + creationDate + "\n" +
                   "Количество студентов - " + studentsCount + "\n" +
                   "Отчисленные студенты - " + expelledStudents + "\n" +
                   "Форма обучения - " + formOfEducation + "\n" +
                   "Номер семместра - " + semesterEnum + "\n" +
                   "Староста группы - " + groupAdmin + "\n");
       }
       catch (NullPointerException e){
           System.out.println("Ваша коллекция загружена некорректно");
       }
    }

    /**
     * Метод сравнения 2 объектов класса StudyGroup
     */
    @Override
    public int compareTo(StudyGroup o) {
        return this.getName().compareTo(o.getName());
    }
}


