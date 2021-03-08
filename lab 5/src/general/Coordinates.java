package general;

public class Coordinates {
    private Long x; //Поле не может быть null
    private Float y; //Значение поля должно быть больше -345, Поле не может быть null
    /**
     * Конструктор с координатами x и y
     * @param x X
     * @param y Y
     */
    public Coordinates(Long x, Float y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Геттер x
     * @return поле x
     */
    public Long getX() {
        return x;
    }
    /**
     * Геттер  y
     * @return поле y
     */
    public Float getY() {
        return y;
    }
    /**
     * Сеттер для x
     * @param x x
     */
    public void setX(Long x) {
        this.x = x;
    }
    /**
     * Сеттер для y
     * @param y y
     */
    public void setY(Float y) {
        this.y = y;
    }
    /**
     * Переопределение метода toString
     * @return Строковое представление класса
     */
    @Override
    public String toString() {
        return "x = " + getX() + "; y = " + getY();
    }
}
