package person;

import enums.Places;
import interfaces.GiveName;
import interfaces.NoLying;
import interfaces.PlaceAction;
public class Gear extends Actors implements PlaceAction, NoLying, GiveName {
    private String location;
    private String action;
    private String description;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gear(String name, String type) {
        super(name, type);
    }

    @Override
    public void stored() {
        if (this.getType().equals("Something")) {
            setAction("stored");
            if (getDescription().equals("Ship's")) {
                setLocation("shelves");
            }
        }
    }


    @Override
    public void lyingDown() {
        if (this.getType().equals("Human")) {
            setAction("lying down");
            setLocation("on shelves");
        }
    }

    @Override
    public void noLying() {
        if (this.getType().equals("Something")) {
            setAction("no lying");
            setLocation("on shelves");
        }
    }

    @Override
    public void christened() {
        if (this.getName().equals("Prisoners")) {
            setAction("christened");
            setDescription("prison");
        }
    }

    private class FishingRod {
        public void Have() {
            setDescription("ship's gear with fishing rod");
        }
    }
        @Override
        public void called(Places places) {
            FishingRod fishingRod = new FishingRod();
            if (this.getType().equals("Something")) {
                if (places.equals(Places.SHIP))
                   fishingRod.Have();
            }
            if (this.getType().equals("Something")) {
                if (places.equals(Places.OFFICE))
                    setDescription("police gear");
            }

        }
    }


