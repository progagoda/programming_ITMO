package person;

public abstract class Actors {
    private final String name;
    private final String type;

    protected Actors(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public  String toString() {
            return "Something by name " + this.name;

    }
}