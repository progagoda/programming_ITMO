package general;

import helpers.Messages;

public class Coordinates {
    private Long x; //Поле не может быть null
    private Float y; //Максимальное значение поля: -345, Поле не может быть null

    public Long  getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public boolean setX(Long x) {
        if (x == null) {
            Messages.normalMessageOutput("X не может быть null");
            return false;
        } else {
            this.x = x;
            return true;
        }
    }

    public boolean setY(Float y) {
        if (y == null || y > -345) {
            Messages.normalMessageOutput("Y  не может быть null или больше -345");
            return false;
        } else {
            this.y = y;
            return true;
        }
    }
}
