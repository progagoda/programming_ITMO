package person;

import interfaces.FunAction;
import exceptions.HistoryExeption;
public class Someone2 extends Actors  implements FunAction {
    private  boolean historyLive;
    private  boolean game;
    private String Action;

    public boolean isHistoryLive() {
        return historyLive;
    }
    public void setHistoryLive(boolean historyLive) {
        this.historyLive = historyLive;
    }

    public boolean isGame() {
        return game;
    }
    public void setGame(boolean game) {
        this.game = game;
    }

    public String getAction() {
        return Action;
    }
    public void setAction(String action) {
        Action = action;
    }

    public Someone2(String name, String type) {
        super(name, type);
    }

    @Override
    public void Played(String game) {
        if (isGame()) {
            if (game.equals("Rashibalochka")) {
                setAction("played rashibalochka");
            }
            else{
                setAction("played another game");
            }
        }
    }
    @Override
    public void Told(String history) {
        if (isHistoryLive()){
            if(history.equals("sad")){
                setAction("told sad history");
            }

        }
        if (!isHistoryLive()){
            try{
                throw new HistoryExeption();
            }
            catch (HistoryExeption e){
                System.err.println("History Live must be true");
            }
        }
    }
}

