package person;

import exceptions.HumanExeption;
import interfaces.SewAction;

public class Someone  extends Actors implements SewAction {
    private boolean vision;
    private boolean Perseverance;
    private boolean clothes;
    private String Action;
    public boolean isVision() {
        return vision;
    }
    public void setVision(boolean vision) {
        this.vision = vision;
    }

    public boolean isPerseverance() {
        return Perseverance;
    }
    public void setPerseverance(boolean perseverance) {
        Perseverance = perseverance;
    }

    public boolean isClothes() {
        return clothes;
    }
    public void setClothes(boolean clothes) {
        this.clothes = clothes;
    }

    public String getAction() {
        return Action;
    }
    public void setAction(String action) {
        Action = action;
    }

    public Someone(String name, String type) {
        super(name, type);
    }

    @Override
    public void Darning() {
        if (isClothes() & isPerseverance() & isVision()){
            setAction("darning his old clothes");
        }
        if (!isVision()){
                try {
                    throw new HumanExeption();
                } catch (HumanExeption e) {
                   System.err.println(getName()+" must have vision");
                }
            setAction("Sorry,I don't see anything, but I try to do this method");
        }
        if (!isClothes()){
            setAction("What I need to darning, I don't have clothes");
        }
    }
}
