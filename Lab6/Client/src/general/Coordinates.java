package general;

import message.MessageColor;
import message.Messages;

import java.io.Serializable;

public class Coordinates implements Serializable {
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
            Messages.normalMessageOutput("X не может быть null", MessageColor.ANSI_RED);
            return false;
        } else {
            try {
                this.x = x;
                return true;
            } catch (Exception e) {
                Messages.normalMessageOutput("Поле x должно быть числом",MessageColor.ANSI_RED);
                return false;
            }

        }
    }

    public boolean setY(Float y) {
        if (y == null || y > -345) {
            Messages.normalMessageOutput("Y  не может быть null или больше -345", MessageColor.ANSI_RED);
            return false;
        } else {
            try {
                this.y = y;
                return true;
            }
            catch (Exception e){
                Messages.normalMessageOutput("Поле Y должно быть числом", MessageColor.ANSI_RED);
                return false;
            }
        }
    }
}
