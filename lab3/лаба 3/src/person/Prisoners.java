package person;

import enums.Places;
import interfaces.GiveName;

public class Prisoners extends Actors implements GiveName {
    private int term;
    private String action;

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Prisoners(String name, String type) {
        super(name, type);
    }

    @Override
    public void christened() {
        if (this.getName().equals("Prisoners")) {
            if (getTerm() < 10) {
                setAction("christened");
            } else {
                setAction("christened with rite of passage ");
            }
        }
    }

    @Override
    public void called(Places places) {
        if (this.getType().equals("Something")) {
            if (places.equals(Places.OFFICE))
                if (getTerm() < 10) {
                    setAction("called shelving department");
                } else {
                    setAction("called dark place");
                }
        } else {
            if (places.equals(Places.SLAMMER)) {
                if (getTerm() < 10) {
                    setAction("called shelving department");
                } else {
                    setAction("called trashcan");
                }
            }
            if (places.equals(Places.ROOM)) {
                if (getTerm() < 10) {
                    setAction("called shelving department");
                } else {
                    setAction("called bad place");
                }
            }
        }

    }
}