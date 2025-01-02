package dataBaseSim;

import entity.Admin;
import entity.Post;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

 List<Admin> admins = new ArrayList<>();

 List<User> users = new ArrayList<>();

List<Post> posts = new ArrayList<>();

    //ön tanımlı admin/post,kullanıcı
    public DataBase() {
        User user = new User("akincali","1234");
        admins.add(new Admin("admin","1234"));
        users.add(user);
        posts.add(new Post("merhaba dünya",user));
        posts.add(new Post("selamlar",user));
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

    public  void addPost(Post post){
        posts.add(post);
    }

    public List<Post> getAllPosts(){
        return this.posts;
    }


}
