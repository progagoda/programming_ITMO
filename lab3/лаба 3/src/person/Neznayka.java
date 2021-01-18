package person;

import interfaces.PhysAction;

public class Neznayka extends Actors implements PhysAction {
private int hand;
private String character;
private String sound;
private String hit;
    public int getHand() {
        return hand;
    }
    public void setHand(int hand) {
       this.hand = hand;
}

    public String getCharacter() {
        return character;
    }
    public void setCharacter(String character) {
        this.character = character;
    }


    public void setSound(String sound) {
        this.sound = sound;
    }


    public void setHit(String hit) {
        this.hit = hit;
    }

    public Neznayka(String name, String type) {
        super(name, type);
    }

    @Override
    public void waved() {
        if (this.getType().equals("Human")) {
            if (getHand()==10){
                setSound("boom");
            }
            if( getHand()>10){
                setSound("boooooooom");
            }
        }
    }

    @Override
    public void cameOut() {
        if (this.getType().equals("Human")) {
            if (getCharacter().equals("angry")) {
                setHit("strong");
            }
            else{
                setHit("weak");
                }
        }
    }
    public static class Hat{
        private String Action;
        public String getAction() {
            return Action;
        }
        public void setAction(String action) {
            Action = action;
        }
        public void removed(boolean hat){
            if (hat==true){
                setAction("Hat off");
            }
            else{
                setAction("Hat doesn't off, because Neznayka without a hat");
            }
        }

    }
}



