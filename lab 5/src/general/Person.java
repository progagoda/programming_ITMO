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
    public boolean setName(String name) {
        this.name = name;
        return true;
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
    public boolean setPassportID(String passportID) {
        this.passportID=passportID;
        return true;
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
    public boolean setEyeColor(Color eyeColor) {
        if (eyeColor == null) {
            return false;
        } else {
            this.eyeColor = eyeColor;
            return true;
        }
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
    public boolean setHairColor(Color hairColor) {
        if (hairColor == null) {
            return false;
        } else {
            this.hairColor = hairColor;
            return true;
        }
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
    public boolean setNationality(Country nationality) {
        if (nationality == null) {
            return false;
        } else {
            this.nationality= nationality;
            return true;
        }
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
