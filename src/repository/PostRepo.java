package repository;

import dataBaseSim.DataBase;
import entity.Post;

import java.util.List;

public class PostRepo {

    DataBase dataBase ;

    public PostRepo(DataBase dataBase) {
        this.dataBase = dataBase;
    }



    public void sendPostToDb(Post post){
        dataBase.addPost(post);
    }

    public List<Post> getPostsFromDb(){
        return dataBase.getAllPosts();
    }
}
