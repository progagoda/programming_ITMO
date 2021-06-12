package helpers;

import java.io.Serializable;
import java.util.Random;

public class ServerUser implements Serializable {
    private String login, password;
    private boolean authorized;
    private int id;
    private static final long serialVersionUID = 6529685098267757690L;

    public ServerUser(){}

    public ServerUser(String login, String password){
        this.login = login;
        this.password = password;
        this.id=Math.abs(new Random().nextInt());
        this.authorized = false;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public int setId(int id) {
        this.id=id;
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorized(boolean authorized){
        this.authorized = authorized;
    }
}