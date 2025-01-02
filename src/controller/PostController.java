package controller;

import entity.Post;
import service.PostService;
import userInterface.General;

import java.util.List;

public class PostController {


    PostService postService;
    Post post ;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public void sendPost(String content, General user){
         this.post = new Post(content,user);
         postService.sendPostToRepo(post);
    }

    public List<Post> getPostsFromServive(){
        return postService.getPostsFromRepo();
    }
}
