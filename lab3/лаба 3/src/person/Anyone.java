package person;

import interfaces.PotatoAction;

public class Anyone extends Actors  implements PotatoAction {
private boolean shorties;
private String  character;

private String Action;
    public boolean isShorties() {
        return shorties;
    }
    public void setShorties(boolean shorties) {
        this.shorties = shorties;
    }

    public String getCharacter() {
        return character;
    }
    public void setCharacter(String character) {
        this.character = character;
    }
    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public Anyone(String name, String type) {
        super(name, type);
    }

    @Override
    public void BeganToBlow() {
        if (isShorties()) {
            if (getCharacter().equals("funny")) {
                setAction("funny began to blow");
            } else {
                setAction("not though began to blow");
            }
        }
    }

    @Override
    public void Throwing(int power) {
        if (isShorties()){
            if (power >=10){
                setAction("Strongly throwing");
            }
            else{
                setAction("throwing");
            }
        }
    }
}
