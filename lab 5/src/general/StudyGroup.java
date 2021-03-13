package general;

import java.time.ZonedDateTime;
import java.util.Random;
public class StudyGroup {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private long expelledStudents; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле не может быть null
    /**
     * Конструктор HumanBeing
     * @param name имя
     * @param coordinates координаты
     * @param studentsCount число студентов
     * @param expelledStudents отчисленные студенты
     * @param formOfEducation вид обучения
     * @param semesterEnum  номер семместра
     * @param groupAdmin староста группы
     */
    public StudyGroup(String name, Coordinates coordinates, Long studentsCount, long expelledStudents, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.expelledStudents = expelledStudents;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }
    /**
     * Геттер id
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Геттер name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер x
     * @return x
     */
    public Long getX() {
        return coordinates.getX();
    }

    /**
     * Геттер y
     * @return y
     */
    public Float getY() {
        return coordinates.getY();
    }

    /**
     * Генерирует значения id (0-10000)
     * @return сгенерировнный id
     */
    public Integer randomId() {
        Random random = new Random();
        Integer num = random.nextInt(10000);
        return num;
    }

    /**
     * Геттер creationDate
     * @return creationDate
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Cеттер id
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Сеттер name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Сеттер для x
     * @param x x
     */
    public void setX(Long x) {
        coordinates.setX(x);
    }

    /**
     * Сеттер для y
     * @param y x
     */
    public void setY(Float y) {
        coordinates.setY(y);
    }

    /**
     * Сеттер creationDate
     * @param creationDate creationDate
     */
    public void setDate(ZonedDateTime creationDate) {
        this.creationDate= creationDate;
    }

    /**
     * Геттер studentsCount
     * @return  studentsCount
     */
    public Long getStudentsCount() {
        return studentsCount;
    }

    /**
     * Геттер expelledStudents
     * @return expelledStudents
     */
    public long getExpelledStudents() {
        return expelledStudents;
    }

    /**
     * Геттер formOfEducation
     * @return formOfEducation
     */
    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    /**
 * Геттер semesterEnum
 * @return semesterEnum
 */
    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    /**
     * Геттер groupAdmin
     * @return groupAdmin
     */
    public Person getGroupAdmin() {
        return groupAdmin;
    }

    /**
     * Сеттер studentsCount
     * @param  studentsCount studentsCount
     */
    public void setStudentsCount(Long studentsCount) {
        this.studentsCount = studentsCount;
    }

    /**
     * Сеттер expelledStudents
     * @param expelledStudents expelledStudents
     */
    public void setExpelledStudents(long expelledStudents) {
        this.expelledStudents = expelledStudents;
    }

    /**
     * Сеттер formOfEducation
     * @param  formOfEducation formOfEducation
     */
    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    /**
     * Сеттер semesterEnum
     * @param semesterEnum semesterEnum
     */
    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    /**
     * Сеттер groupAdmin
     * @param groupAdmin groupAdmin
     */
    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    /**
     * Переопределение метода toString
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "HumanBeing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", studentCount=" + studentsCount +
                ", expelledStudents=" + expelledStudents +
                ", formOfEducation=" + formOfEducation +
                ", semesterEnum=" + semesterEnum +
                ", groupAdmin=" + groupAdmin +
                '}';
    }
}


