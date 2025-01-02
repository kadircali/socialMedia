import controller.PostController;
import controller.UserController;
import dataBaseSim.DataBase;
import entity.Admin;
import entity.Post;
import entity.User;
import exceptions.UserNotFoundException;
import repository.PostRepo;
import repository.UserRepo;
import service.PostService;
import service.UserService;
import userInterface.General;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {


        //frontend kısmı
        /*

        SOSYAL MEDYA


KULLANICILAR

• Post paylaşacak.
• Başka insanların postlarını görecek
o Post bilgilerinde : kim paylaşmış,post açıklaması, like ve dislike
sayıları gözükecek.
• Kendi profillerini düzenleyecek.
• Başka insanların postlarına like ve dislike atabilecek.
• Post güncelle
Admin
• Üye ol
• Giriş yap
• Post sil
• Kullanici sil

STT: 18/01/2025 cumartesi derse kadar

         */

        //ön tanımlı admin girişi --> kullanıcı adı : admin şifre :1234

        int choice ;
        Scanner scan = new Scanner(System.in);


        DataBase dataBase = new DataBase();
        UserRepo userRepo = new UserRepo(dataBase);
        UserService userService = new UserService(userRepo);
        UserController userController = new UserController(userService);


        PostRepo postRepo = new PostRepo(dataBase);
        PostService postService = new PostService(postRepo);
        PostController postController = new PostController(postService);



        String userName ;
        String password;




        System.out.println("Kurusevka Sosyal Medya Uygulamasına Hoşgeldiniz");


        while (true){

            mainMenu();
            choice = scan.nextInt();



            switch (choice){

                case 1 :
                    //üye ol

                    System.out.print("lütfen kullanıcı adı belirleyiniz:");
                    userName = scan.next();
                    System.out.print("lütfen şifre belirleyiniz");
                    password =scan.next();


                    if(userController.userRegister(userName,password) ){

                        System.out.println("******************");
                        System.out.println("kullanici eklendi");


                    }else{
                        System.out.println("******************");
                        System.out.println("kullanici adi mevcut");
                        break ;
                    }



                    break;

                case 2 :
                    //giriş yap, admin ve user aynı yerden giriş yapsın
                    System.out.print("kullanıcı adınız:");
                    userName = scan.next();
                    System.out.print("şifreniz:");
                    password = scan.next();




                    try {
                        General loggedUser = userController.userLogin(userName, password);

                        if (loggedUser instanceof User) {
                            // user girişi başarılı : profil açılsın, profil düzenleme vs.





                            while (true){
                                userMenu();
                                choice = scan.nextInt();
                                switch (choice){
                                    case 1 :
                                        //profili görüntüle

                                        userController.displayProfile(loggedUser);
                                        continue;

                                    case 2:
                                        //gönderileri gör
                                        List<Post> posts = postController.getPostsFromServive();

                                        int currentIndex = 0;

                                       while (true){
                                           System.out.println(posts.get(currentIndex));
                                           System.out.println("Bir sonraki postu görmek için 'a' ya basın.");
                                           System.out.println("Bir önceki postu görmek için 'b' ya basın.");
                                           System.out.println("Postu beğenmek için k tuşuna basın");
                                           System.out.println("Postu beğenmemek için m tuşuna basın");
                                           System.out.println("Çıkmak için 'q' ya basın.");
                                           String choice2 = scan.next();

                                           // Seçime göre işlem
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
                                               System.out.println("Geçersiz seçenek. Lütfen 'a', 'b' veya 'q' tuşlarından birine basın.");
                                           }
                                       }


                                        continue;
                                    case 3:
                                        //çıkış yap
                                        break;

                                }
                                break;
                            }



                        } else if (loggedUser instanceof Admin) {
                            // admin girişi başarılı :adminlerin de profili olsun, admin paneli olsun, admin ekleme vs.
                            adminMenu();
                            choice = scan.nextInt();
                            switch (choice){
                                case 1 :

                                    break ;

                                case 2:


                                    break ;
                                case 3:
                                    break ;
                                case 4 :
                                    break ;

                            }
                        }


                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());  // Kullanıcı bulunamadı mesajı
                    }


                    break;



            }

        }

    }


    public static void mainMenu(){
        System.out.println("Ne yapmak istiyorsunuz?");
        System.out.println("1-)Üye Ol");
        System.out.println("2-)Giriş Yap");
        System.out.print("seçiminiz :");
    }


    public static void userMenu(){

        System.out.println("******************");
        System.out.println("1-)profilimi görüntüle");
        System.out.println("2-)gönderileri gör");
        System.out.println("3-)çıkış yap");
        System.out.print("ne yapmak istiyorsunuz?:");
    }

    public static void adminMenu(){
        System.out.println("******************");
        System.out.println("1-)admin ekle");
        System.out.print("2-)tüm kullanıcıları gör");
        System.out.println("3-)gönderileri gör");
        System.out.println("4-)çıkış yap");
        System.out.print("ne yapmak istiyorsunuz?:");
    }

}