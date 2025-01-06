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
        User user = new User("akincali","1234","ben akıncalı");
        user.setId("1");
        user.setAuthority("user");
        users.add(user);

        Admin admin = new Admin("admin","1234");
        admin.setAuthority("admin");
        admin.setId("2");
        admins.add(admin);


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


    public void addAdmin(Admin admin){
        this.admins.add(admin);
    }


    public  boolean deleteUser(String id){

        for(User user:users){
            if(user.getId().equals(id)){
                users.remove(user);
                return  true ;
            }
            else {
                for (Admin admin:admins){
                    if(admin.getId().equals(id)){
                        admins.remove(admin);
                        return true ;
                    }
                }
            }
        }

        return false ;
    }


    public void deletePost(Post post){
        posts.remove(post);
    }

}
