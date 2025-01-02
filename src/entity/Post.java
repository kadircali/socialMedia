package entity;

import userInterface.General;

public class Post {

    private String postContent ;
    private int likes =  0 ;
    private int unlikes = 0 ;
    General user ;

    public Post(String postContent,General user) {
        this.postContent = postContent;
        this.user = user;
    }


    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getUnlikes() {
        return unlikes;
    }

    public void setUnlikes(int unlikes) {
        this.unlikes = unlikes;
    }


    public void likePost() {
        likes++;
    }

    public void unlikePost() {
        unlikes++;
    }

    public General getUser() {
        return user;
    }

    public void setUser(General user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "İçerik :" + postContent +"\n"+
                "Likes :" + likes + "\n"+
                "Unlikes :" + unlikes + "\n"+
                "Gönderen:" + user.getUsername()

                ;
    }
}
