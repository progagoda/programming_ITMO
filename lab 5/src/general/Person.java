package general;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Длина строки не должна быть больше 44, Строка не может быть пустой, Значение этого поля должно быть уникальным, Поле не может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле не может быть null
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
    /**
     * Геттер  passportID
     * @return passportID
     */
    public String getPassportID() {
        return passportID;
    }
    /**
     * Сеттер  passportID
     * @param  passportID
     */
    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }
    /**
     * Геттер  eyeColor
     * @return eyeColor
     */
    public Color getEyeColor() {
        return eyeColor;
    }
    /**
     * Сеттер  eyeColor
     * @param  eyeColor
     */
    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }
    /**
     * Геттер  hairColor
     * @return passportID
     */
    public Color getHairColor() {
        return hairColor;
    }
    /**
     * Сеттер  hairColor
     * @param  hairColor
     */
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }
    /**
     * Геттер  nationality
     * @return nationality
     */
    public Country getNationality() {
        return nationality;
    }
    /**
     * Сеттер  nationality
     * @param  nationality
     */
    public void setNationality(Country nationality) {
        this.nationality = nationality;
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
