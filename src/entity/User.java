package entity;

import userInterface.General;

public class User extends General implements userInterface.User {


    public User(String username, String password) {
        super(username, password);
    }

    private String about ;

}
