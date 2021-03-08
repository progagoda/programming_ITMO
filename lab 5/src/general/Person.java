package general;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Длина строки не должна быть больше 44, Строка не может быть пустой, Значение этого поля должно быть уникальным, Поле не может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле не может быть null

    public Person(String name, String passportID, Color eyeColor, Color hairColor, Country nationality) {
        this.name = name;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    /**
     * Геттер name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "general.Person{" +
                ", name = '" + name + '\'' +
                ", passportID = " + passportID +
                ", eyeColor = " + eyeColor +
                ", hairColor = " + hairColor +
                ", nationality = " + nationality +
                '}';
    }
}
