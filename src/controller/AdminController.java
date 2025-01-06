package controller;


import entity.Admin;
import service.AdminService;
import userInterface.General;

import java.util.List;

public class AdminController {

    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }




    public boolean adminRegister(String name,String password){


        Admin admin = new Admin(name,password);
       return adminService.addAmin(admin);

    }


    public List<General> combinedUsers(){

        return adminService.getAllUsers();
    }

    public boolean deleteUser(String id){

        return adminService.deleteUser(id);
    }

    public void nextPrevious(){

        adminService.nextPrevious();

    }
}
