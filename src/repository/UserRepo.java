package repository;

import dataBaseSim.DataBase;
import entity.Admin;
import entity.User;
import userInterface.General;

import java.util.List;

public class UserRepo {


    DataBase dataBase ;


    public UserRepo(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public  void saveUser(User user){

    dataBase.addUserToList(user);
    }



    public List<User> getUsers(){
        return dataBase.getAllUsers();
    }

    public List<Admin> getAdmins(){
        return dataBase.getAllAdmins();
    }




}
