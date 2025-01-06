package controller;

import entity.Post;
import service.PostService;
import userInterface.General;

import java.util.List;
import java.util.Scanner;

public class PostController {


    PostService postService;
    Post post ;
    Scanner scan = new Scanner(System.in);

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public void sendPost(String content, General user){
         this.post = new Post(content,user);
         postService.sendPostToRepo(post);
    }

    //kullanılmadı
    public List<Post> getPostsFromServive(){
        return postService.getPostsFromRepo();
    }


    public void nextPrevious(){

        postService.nextPrevious();

    }
}
