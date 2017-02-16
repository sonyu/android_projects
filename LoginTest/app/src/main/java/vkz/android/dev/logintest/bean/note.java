package vkz.android.dev.logintest.bean;

/**
 * Created by Administrator on 12/24/2016.
 */
import java.io.Serializable;
public class note   implements Serializable{
    private int login_id;
    private String login_username;
    private String login_password;

    public note(){

    }
    public note(String login_username, String login_password){
        this.login_password = login_password;
        this.login_username = login_username;
    }
    public note(int login_id,String login_username,String login_password){
        this.login_id = login_id;
        this.login_username = login_username;
        this.login_password = login_password;
    }

    public int getLogin_id() {
        return login_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getLogin_username() {
        return login_username;
    }

    public void setLogin_username(String login_username) {
        this.login_username = login_username;
    }

    @Override
    public String toString() {
        return this.login_username;
    }
}
