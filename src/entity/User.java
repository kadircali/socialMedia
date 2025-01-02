package entity;

import userInterface.General;

public class User extends General implements userInterface.User {


    public User(String username, String password) {
        super(username, password);
    }

    private String about ;


    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
