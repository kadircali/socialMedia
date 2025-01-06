package repository;

import dataBaseSim.DataBase;
import entity.Admin;
import entity.Post;
import entity.User;

import javax.xml.crypto.Data;
import java.util.List;

public class AdminRepo {


    DataBase dataBase ;

    public AdminRepo(DataBase dataBase) {
        this.dataBase = dataBase;
    }



    public void addAmin(Admin admin){

    dataBase.addAdmin(admin);


    }



    public List<Admin> getAdmins(){
        return dataBase.getAllAdmins();
    }

    public List<User> getUsers(){
        return dataBase.getAllUsers();
    }



    public  boolean deleteUser(String id){
        return dataBase.deleteUser(id);
    }

    public List<Post> getPostsFromDb(){
        return dataBase.getAllPosts();
    }

    public void deletePost(Post post){

        dataBase.deletePost(post);
    }
}
