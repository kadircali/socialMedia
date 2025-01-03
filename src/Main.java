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
        String about ;




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
                    System.out.print("hakkınızda birkaç şey yazın:");
                    about = scan.next();


                    if(userController.userRegister(userName,password,about) ){

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
                                        //gönderileri gör , like at
                                        postController.nextPrevious();



                                        continue;

                                    case 3 :
                                        //gönderi paylaş
                                        System.out.print("Gnderi içeriğini giriniz:");

                                        String content = scan.next();
                                        scan.nextLine();

                                        postController.sendPost(content,loggedUser);
                                        continue;
                                    case 4 :
                                        userController.displayProfile(loggedUser);
                                        //profili düzenle
                                        String newName ;
                                        String newPassword ;
                                        String oldPassword ;
                                        String newAbout ;

                                        System.out.print("yeni kullanıcı adınızı giriniz:");
                                        newName = scan.next();
                                        System.out.print("eski şifrenizi giriniz :");
                                        oldPassword = scan.next();

                                        System.out.print("yeni şifrenizi giriniz:");
                                        newPassword = scan.next();
                                        System.out.print("hakkınızda:");
                                        newAbout = scan.next();
                                        boolean sonuc = userController.editProfile(loggedUser,newName,oldPassword,newPassword,newAbout);
                                        System.out.println(sonuc);
                                        for (User user : dataBase.getAllUsers()){
                                            System.out.println(user.getUsername());
                                        }


                                        break ;

                                    case 5:
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
        System.out.println("1-)Profilimi görüntüle");
        System.out.println("2-)Gönderileri gör");
        System.out.println("3-Gönderi paylaş");
        System.out.println("4-)Prolimi düzenle");
        System.out.println("5-)Çıkış yap");
        System.out.print("ne yapmak istiyorsunuz?:");
    }

    public static void adminMenu(){
        System.out.println("******************");
        System.out.println("1-)Admin ekle");
        System.out.print("2-)Tüm kullanıcıları gör");
        System.out.println("3-)Gönderileri gör");
        System.out.println("4-)Çıkış yap");
        System.out.print("ne yapmak istiyorsunuz?:");
    }

    public static void editProfile(){

    }
}