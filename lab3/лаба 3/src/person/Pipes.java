package person;

import exceptions.NumException;
import interfaces.PipesAction;

public class Pipes extends Actors  implements PipesAction {
    private boolean tinCans;
    private String Action;
    private int length;


    public boolean getTinCans() {
        return tinCans;
    }
    public void setTinCans(boolean tinCans) {
        this.tinCans = tinCans;
    }

    public String getAction() {
        return Action;
    }
    public void setAction(String action) {
        Action = action;
    }

    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public Pipes(String name, String type) {
        super(name, type);
    }


    @Override
    public void Stretched() {
        class Strenght{
            public void level(int level){
                if (level>=5){
                    System.out.println("Reliable");
                }
                else{
                    System.out.println("Unreliable");
                }
            }
        }
        if (getTinCans() & getLength()>20){
            setAction("stretched");
        }
        else{
            setAction("don't stretched because pipes don't tin cans or have a small length");
        }
        if (getLength()<0){
            try{
                throw new NumException();
            }
            catch (NumException e){
                System.err.println(getName()+" length must be >0");
            }
        }
    }

}
