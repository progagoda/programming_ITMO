package person;
import interfaces.BakerAction;
import interfaces.PlaceAction;

public class Shorties extends Actors implements PlaceAction, BakerAction {
    private String location;
    private String action;
    private String character;

    public Shorties(String name, String type) {
        super(name, type);
    }

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

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public void stored() {
        if (this.getType().equals("Something")) {
            if (getLocation() != "shelves") {
                setAction("stored");
                setLocation("shelves");
            }
        }
    }

    @Override
    public void lyingDown() {
        if (this.getType().equals("Human")) {
            setLocation("shelves");
            if (getCharacter().equals("violent")) {
                setAction("lying down restlessly");
            } else {
                setAction("lying down");
            }
        }
    }

    @Override
    public void Bake() {
        if (getCharacter().equals("cooker")) {
            setAction("Bake potato");
        }
    }
        @Override
        public void Sat(){
            if (getCharacter().equals("lazy")) {
                setAction("Sat around the stove");
            }
        }
    }

