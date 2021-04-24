package general;

import collection.IdManager;
import collection.PassportManager;
import helpers.Messages;

import java.util.ArrayList;
import java.util.Random;

public class Person implements Comparable<Person> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Длина строки не должна быть больше 44, Строка не может быть пустой, Значение этого поля должно быть уникальным, Поле не может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле не может быть null
    /**
     * Геттер name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер name
     *
     * @param name
     */
    public boolean setName(String name) {
        if (name.trim().equals("")){
            Messages.normalMessageOutput("Имя не может быть null или пустой строкой");
            return  false;
        }
        else {
            this.name = name;
            return true;
        }
    }

    /**
     * Геттер  passportID
     *
     * @return passportID
     */
    public String getPassportID() {
        return passportID;
    }

    /**
     * Сеттер  passportID
     *
     * @param passportID
     */

    public boolean setPassportID(String passportID) {//выводит рекурсию почему-то
//        try{
//            int passportIDInt=Integer.parseInt(passportID);
//            PassportManager.addId(passportIDInt);
//            if (!PassportManager.checkUniq(passportIDInt)) {
//                this.passportID=PassportManager.findUniq(Math.abs(new Random().nextInt())).toString();
//                Messages.normalMessageOutput("passportId  должен быть уникальным,но не волнуйтесь мы создали его автоматически за вас");
//                return true;
//            }
//        }
//        catch (NumberFormatException e){
//            Messages.normalMessageOutput("Номер паспорта должен содержать только цифры");
//            return  false;
//        }
        if (passportID.equals(passportID.trim()=="")) {
            Messages.normalMessageOutput("passportId не может быть  пустой строкой");
            return  false;
        }
        if(passportID.length() > 44){
            Messages.normalMessageOutput("passportId не может быть длинне 44 символов");
            return  false;
        }
        if(passportID == null){
            Messages.normalMessageOutput("passportId не может быть null");
            return  false;
        }
        else {
            this.passportID = passportID;
            return true;
        }
        }

    /**
     * Геттер  eyeColor
     *
     * @return eyeColor
     */
    public Color getEyeColor() {
        return eyeColor;
    }

    /**
     * Сеттер  eyeColor
     *
     * @param eyeColor
     */
    public boolean setEyeColor(Color eyeColor) {
        if (eyeColor == null) {
            Messages.normalMessageOutput("eyeColor  не может быть  null");
            return false;
        } else {
            this.eyeColor = eyeColor;
            return true;
        }
    }

    /**
     * Геттер  hairColor
     *
     * @return passportID
     */
    public Color getHairColor() {
        return hairColor;
    }

    /**
     * Сеттер  hairColor
     *
     * @param hairColor
     */
    public boolean setHairColor(Color hairColor) {
        if (hairColor == null) {
            Messages.normalMessageOutput("hairColor  не может быть  null");
            return false;
        } else {
            this.hairColor = hairColor;
            return true;
        }
    }

    /**
     * Геттер  nationality
     *
     * @return nationality
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * Сеттер  nationality
     *
     * @param nationality
     */
    public boolean setNationality(Country nationality) {
        if (nationality == null) {
            Messages.normalMessageOutput("Nationality  не может быть  null");
            return false;
        } else {
            this.nationality = nationality;
            return true;
        }
    }

    @Override
    public String toString() {
        return " " +
                "  Имя = '" + name + '\'' +
                ", номер паспорта = " + passportID +
                ", цвет глаз = " + eyeColor +
                ", цвет волос = " + hairColor +
                ", национальность = " + nationality +
                ' ';
    }
    /**
     * Метод сравнения 2 объектов класса Person
     */
    @Override
    public int compareTo(Person o) {
        return this.getName().compareTo(o.getName());
    }
}
