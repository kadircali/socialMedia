package controller;

import entity.User;
import exceptions.UserNotFoundException;
import service.UserService;
import userInterface.Admin;
import userInterface.General;

public class UserController {




    UserService userService ;
    User user ;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean userRegister(String username, String password){
        User user = new User(username,password);
        return userService.addUser(user);
    }


    public General userLogin(String name,String password) throws UserNotFoundException {

        General general = new General(name,password);
        General loggedUser = userService.userLog(general);

        // Eğer giriş başarılı değilse, exception fırlatıyoruz


         if (!(loggedUser instanceof User)  && !(loggedUser instanceof Admin) ) {
            throw new UserNotFoundException("Kullanıcı bulunamadı!");
        }


        return loggedUser;

    }


    public void displayProfile(General user){

        this.user =(User) user;
        System.out.println("******************");
        System.out.println("kullanıcı adı :"+user.getUsername());
        System.out.println("hakkında :"+((User) user).getAbout());
    }



}
