package helpers;

import java.io.Serializable;

public class ServerUser implements Serializable {
    private String login, password;
    private boolean authorized;
    private static final long serialVersionUID = 6529685098267757690L;

    public ServerUser(){}

    public ServerUser(String login, String password){
        this.login = login;
        this.password = password;
        this.authorized = false;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getAuthorized(){
        return this.authorized;
    }

    public void setAuthorized(boolean authorized){
        this.authorized = authorized;
    }
}