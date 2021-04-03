package general;

import general.Coordinates;
import general.FormOfEducation;
import general.Person;
import general.Semester;
import helpers.Messages;
import java.time.ZonedDateTime;
import java.util.Random;
public class StudyGroup  implements  Comparable<StudyGroup>{
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
        Integer num = random.nextInt(100000);
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
    public boolean setName(String name) {
        if (name == null || name.equals("")) {
            return false;
        } else {
            this.name = name;
            return true;
        }
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
    public boolean setStudentsCount(Long studentsCount) {
        if (studentsCount > 0) {
            this.studentsCount = studentsCount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Сеттер expelledStudents
     * @param expelledStudents expelledStudents
     */
    public boolean setExpelledStudents(long expelledStudents) {
        if (expelledStudents> 0) {
            this.expelledStudents = expelledStudents;
            return true;
        } else {
            return false;
        }
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
        return "StudyGroup{" +
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
    /**
     * Выводит информацию в строков виде об объекте
     */
    public void printInfoAboutElement() {
        Messages.normalMessageOutput("id - " + id + "\n" +
                "Имя - " + name + "\n" +
                "Координаты x и y - " + coordinates.getX() + ", " + coordinates.getY() + "\n" +
                "Дата создания - " + getCreationDate() + "\n" +
                "Количество студентов - " +  studentsCount+ "\n" +
                "Отчисленные студенты - " + expelledStudents+ "\n" +
                "Форма обучения - " + formOfEducation + "\n" +
                "Номер семместра - " + semesterEnum + "\n" +
                "Староста группы - " + groupAdmin + "\n");
    }

    /**
     * Метод сравнения 2 объектов класса Flat
     */
    @Override
    public int compareTo(StudyGroup o) {
        return this.getName().compareTo(o.getName());
    }
}


