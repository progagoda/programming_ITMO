package person;

import interfaces.PastAction;

public class Difference extends Actors implements PastAction {
    private String time;
    private String topic;
    private String action;
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public Difference(String name, String type) {
        super(name, type);
    }

    @Override
    public void was() {
        if (this.getName().equals("Difference")) {
            if (getTopic().equals("shelves")) {
                if (getTime().equals("past")) {
                    setAction("was that what");
                }
                if (getTime().equals("future")) {
                    setAction("will be that what");
                }
            }
        }
    }
}