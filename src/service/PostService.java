package service;

import entity.Post;
import repository.PostRepo;
import userInterface.General;

import java.util.List;

public class PostService {

    PostRepo postRepo ;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }


    public void sendPostToRepo(Post post){
        postRepo.sendPostToDb(post);
    }

    public List<Post> getPostsFromRepo(){
        return postRepo.getPostsFromDb();
    }
}
