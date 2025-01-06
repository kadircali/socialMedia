package service;

import entity.Admin;
import entity.Post;
import entity.User;
import repository.AdminRepo;
import userInterface.General;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AdminService {

    AdminRepo adminRepo;
    List<Admin> admins ;
    Scanner scan = new Scanner(System.in);

    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }



    public boolean addAmin(Admin admin){

        this.admins = adminRepo.getAdmins();
        String uniqueID = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);

        for (Admin admin1 : admins){
            if(admin.getUsername().equals(admin1.getUsername())){

                return false;

            }

        }
        admin.setId(uniqueID);
        admin.setAuthority("admin");
        adminRepo.addAmin(admin);
        return true ;



    }



    public List<General> getAllUsers(){

        List<User> allUsers = adminRepo.getUsers();
        List<Admin> allAdmins = adminRepo.getAdmins();

        List<General> combinedList = new ArrayList<>();
        combinedList.addAll(allUsers);
        combinedList.addAll(allAdmins);

        return combinedList;

    }


    public  boolean deleteUser(String id){

        return adminRepo.deleteUser(id);
    }

    public void nextPrevious(){

        int currentIndex = 0;
        List<Post> posts = adminRepo.getPostsFromDb();
        while (true){
            System.out.println(posts.get(currentIndex));
            System.out.println("Bir sonraki postu görmek için 'a' ya basın.");
            System.out.println("Bir önceki postu görmek için 'b' ya basın.");
            System.out.println("Postu silmek için 'd' tuşuna basın");
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
            } else if (choice2.equals("d")) {
                //silme
                //mevcut post

                adminRepo.deletePost(posts.get(currentIndex));

            } else {
                System.out.println("Geçersiz seçenek. Lütfen 'a', 'b' ,'d' veya 'q' tuşlarından birine basın.");
            }
        }
    }
}
