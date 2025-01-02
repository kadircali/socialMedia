package service;

import entity.Post;
import repository.PostRepo;
import userInterface.General;

import java.util.List;
import java.util.Scanner;

public class PostService {

    PostRepo postRepo ;
    Scanner scan = new Scanner(System.in);

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }


    public void sendPostToRepo(Post post){
        postRepo.sendPostToDb(post);
    }

    public List<Post> getPostsFromRepo(){
        return postRepo.getPostsFromDb();
    }

    public void nextPrevious(){

        int currentIndex = 0;
        List<Post> posts = postRepo.getPostsFromDb();
        while (true){
            System.out.println(posts.get(currentIndex));
            System.out.println("Bir sonraki postu görmek için 'a' ya basın.");
            System.out.println("Bir önceki postu görmek için 'b' ya basın.");
            System.out.println("Postu beğenmek için 'k' tuşuna basın");
            System.out.println("Postu beğenmemek için 'm' tuşuna basın");
            System.out.println("Çıkmak için 'q' ya basın.");
            String choice2 = scan.next();


            if (choice2.equals("a")) {
                // Bir sonraki postu göster
                if (currentIndex < posts.size() - 1) {
                    currentIndex++;
                } else {
                    System.out.println("Zaten son postu görüntülüyorsunuz.");
                }
            } else if (choice2.equals("b")) {
                // Bir önceki postu göster
                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    System.out.println("Zaten ilk postu görüntülüyorsunuz.");
                }
            } else if (choice2.equals("q")) {

                break;
            } else if (choice2.equals("k")) {
                //like
                posts.get(currentIndex).likePost();

            } else if (choice2.equals("m")) {
                //unlike
                posts.get(currentIndex).unlikePost();

            } else {
                System.out.println("Geçersiz seçenek. Lütfen 'a', 'b' ,'k' ,'m' veya 'q' tuşlarından birine basın.");
            }
        }
    }
}
