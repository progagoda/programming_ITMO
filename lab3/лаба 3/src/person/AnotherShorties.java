package person;

import interfaces.TypicalAction;

public class AnotherShorties  extends Actors implements TypicalAction {
    private boolean shelves;
    private int weight;
    private boolean fatigue;
    private  String Action;
    public boolean isShelves() {
        return shelves;
    }
    public void setShelves(boolean shelves) {
        this.shelves = shelves;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isFatigue() {
        return fatigue;
    }
    public void setFatigue(boolean fatigue) {
        this.fatigue = fatigue;
    }

    public String getAction() {
        return Action;
    }
    public void setAction(String action) {
        Action = action;
    }

    public AnotherShorties(String name, String type) {
        super(name, type);
    }


    @Override
    public void Sat() {
        if (!isFatigue() & isShelves() & getWeight()<10 ){
            setAction("sat");
            }
        }

    @Override
    public void Engaged() {
        if (isFatigue() & !isShelves() & getWeight()>=10 ){
            setAction("engaged");
        }
    }
    }

