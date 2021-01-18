package person;

import enums.Places;
import interfaces.BakeAction;

public class Bake extends Actors implements BakeAction {
    private boolean castIron;
    private String Action;
    private String color;


    public boolean getCastIron() {
        return castIron;
    }
    public void setCastIron(boolean castIron) {
        this.castIron = castIron;
    }

    public String getAction() {
        return Action;
    }
    public void setAction(String action) {
        Action = action;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Bake(String name, String type) {
        super(name, type);
    }

    @Override
    public void Standing(Places place) {
        if (getCastIron()) {
            if (place.equals(Places.SLAMMER)& !getColor().equals("yellow")) {
                setAction("Standing in the slammer");
            }
            if (getColor().equals("yellow")){
                setAction("Beautiful standing in the slammer");
            }
        }

    }
}
