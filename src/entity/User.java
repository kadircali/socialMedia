package entity;

import userInterface.General;

public class User extends General implements userInterface.User {



    private String about ;



    public User(String username, String password,String about) {
        super(username, password);
        this.about = about;

    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
