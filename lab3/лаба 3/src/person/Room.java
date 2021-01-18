package person;

import enums.Places;
import interfaces.BrainAction;
import interfaces.GiveName;

public class Room extends Actors implements GiveName, BrainAction {
    private String color;
    private  String passive;
    private String nik;



    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getPassive() {
        return passive;
    }
    public void setPassive(String passive) {
        this.passive = passive;
    }

    public String getNik() {
        return nik;
    }
    public void setNik(String nik) {
        this.nik = nik;
    }

    public Room(String name, String type) {
        super(name, type);
    }
    @Override
    public void christened() {
        if (this.getName().equals("Prisoners")) {
            if (getColor().equals("prison")){
                setPassive("christened");
            }
        }
    }

    @Override
    public void called(Places places) {
        if (this.getType().equals("Something")) {
            if (places.equals(Places.OFFICE))
                setPassive("called");
                setNik("big room");
        }
            if (places.equals(Places.SLAMMER)) {
                setPassive("called");
                setNik("Prison cell");
            }

            if (places.equals(Places.ROOM)) {
                setPassive("called");
                setNik("usually room");
            }
        }





    @Override
    public void reminded() {
        if (this.getType().equals("Something")) {
            setPassive("reminded");
            setNik("ship's room");
        }
    }
}
