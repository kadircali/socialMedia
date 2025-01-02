package dataBaseSim;

import entity.Admin;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

 List<Admin> admins = new ArrayList<>();

 List<User> users = new ArrayList<>();


    //ön tanımlı admin
    public DataBase() {
        admins.add(new Admin("admin","1234"));
    }

    public List<User> getAllUsers(){

        return this.users;
    }


    public List<Admin> getAllAdmins(){

        return this.admins;
    }

    public void addUserToList(User user){
        users.add(user);
    }



}
