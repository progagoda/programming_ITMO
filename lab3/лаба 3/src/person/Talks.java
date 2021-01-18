package person;

import interfaces.NoGive;

public class Talks extends Actors implements NoGive {
private String description;
private int length;
private String remains;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public String getRemains() {
        return remains;
    }
    public void setRemains(String remains) {
        this.remains = remains;
    }

    public Talks(String name, String type) {
        super(name, type);
    }

    @Override
    public void notBring() {
        if (this.getType().equals("Something")) {
            if(getDescription().equals("sky") & getLength()>60){
                setRemains("not bring benefit");
            }
            if(getDescription().equals("science") & getLength()>120) {
                setRemains("bring benefit");
            }
            if(getDescription().equals("art") & getLength()>70) {
                setRemains(" maybe bring benefit");
            }
        }
    }
}
