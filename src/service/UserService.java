package service;

import entity.Admin;
import entity.User;
import exceptions.UserNotFoundException;
import repository.UserRepo;
import userInterface.General;

import java.util.List;
import java.util.UUID;

public class UserService {


    UserRepo userRepo ;
    General generalUser ;
    List<User> users;
    List<Admin> admins ;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //user add
    public boolean addUser(User user){

        this.users = userRepo.getUsers();
        String uniqueID = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);

        for (User userName : users){
            if(user.getUsername().equals(userName.getUsername())){

                return false;

            }

        }
        user.setId(uniqueID);
        userRepo.saveUser(user);
        return true ;
    }




    //user/admin log
    public General userLog(General user){

        //admin ya da user kontrolü burada yapılacak
        this.generalUser = user ;
        users = userRepo.getUsers();
        admins = userRepo.getAdmins();




        // User kontrolü
        for (User eleman : users) {
            if (generalUser.getUsername().equals(eleman.getUsername()) &&
                    generalUser.getPassword().equals(eleman.getPassword())) {
                return eleman;
            }
        }

        // Admin kontrolü
        for (Admin adminEleman : admins) {
            if (generalUser.getUsername().equals(adminEleman.getUsername()) &&
                    generalUser.getPassword().equals(adminEleman.getPassword())) {
                return adminEleman;
            }
        }


        return generalUser;



    }


    public boolean editProfile(General loggedUser,String newUsername,String oldPass,String newPass,String newAbout) {
        //kullanıcı adı ve eski şifre kontrolü
        this.users = userRepo.getUsers();
        for (User user : users){

            if((user.getId().equals(loggedUser.getId())) && !(user.getUsername().equals(newUsername))){

                user.setUsername(newUsername);
                user.setPassword(newPass);
                user.setAbout(newAbout);
                return true;
            }

        }
        return  false ;
    }
}
