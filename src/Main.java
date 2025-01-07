import controller.AdminController;
import controller.PostController;
import controller.UserController;
import dataBaseSim.DataBase;
import entity.Admin;
import entity.Post;
import entity.User;
import exceptions.UserNotFoundException;
import repository.AdminRepo;
import repository.PostRepo;
import repository.UserRepo;
import service.AdminService;
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



Admin
• Üye ol
• Giriş yap
• Post sil
• Kullanici sil

STT: 18/01/2025 cumartesi derse kadar

         */

        //ön tanımlı admin girişi --> kullanıcı adı : admin şifre :1234

        int choice =0 ;
        Scanner scan = new Scanner(System.in);


        DataBase dataBase = new DataBase();
        UserRepo userRepo = new UserRepo(dataBase);
        UserService userService = new UserService(userRepo);
        UserController userController = new UserController(userService);


        PostRepo postRepo = new PostRepo(dataBase);
        PostService postService = new PostService(postRepo);
        PostController postController = new PostController(postService);


        AdminRepo adminRepo = new AdminRepo(dataBase);
        AdminService adminService = new AdminService(adminRepo);
        AdminController adminController = new AdminController(adminService);



        String userName ;
        String password;
        String about ;




        System.out.println("Kurusevka Sosyal Medya Uygulamasına Hoşgeldiniz");


        while (true){

            mainMenu();

            try {
                choice = scan.nextInt();
            }catch (Exception e){
                System.out.println(e.getMessage());
                scan.next();
                continue;

            }


            switch (choice) {

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
                        while (true){

                            adminMenu();
                            choice = scan.nextInt();
                            switch (choice){
                                case 1 :
                                    //admin ekle
                                    System.out.print("admin kullanıcı adı giriniz:");
                                    userName = scan.next();
                                    System.out.print("admin şifresi giriniz:");
                                    password = scan.next();



                                    if(adminController.adminRegister(userName,password) ){

                                        System.out.println("******************");
                                        System.out.println("admin eklendi");


                                    }else{
                                        System.out.println("******************");
                                        System.out.println("kullanici adi mevcut");
                                        break ;
                                    }
                                    continue ;

                                case 2:
                                    //tüm kullanıcıları gör, buradan kullanıcı silinsin
                                List<General> users = adminController.combinedUsers();

                                for (General general : users){
                                    System.out.println("*************");
                                    System.out.println("kullanıcı adı:"+general.getUsername()+"\n" +
                                            "yetki:"+general.getAuthority() +"\n" +
                                            "ID:"+general.getId()
                                            );

                                }

                                    String id;
                                    System.out.println("silmek istediğiniz kullanıcının ID'sini giriniz");
                                    System.out.println("kullanıcı silmek istemiyorsanız 0 giriniz");
                                    System.out.print("ID:");
                                    id = scan.next();

                                    if(adminController.deleteUser(id)){
                                        System.out.println("kullanıcı silindi");
                                    }else{
                                        System.out.println("kullanıcı silinemedi");
                                    }

                                    continue ;
                                case 3:

                                    //tüm gönderileri gör,gönderi sil
                                    adminController.nextPrevious();

                                    break ;

                                case 4:
                                    //çıkış yap

                                    break ;


                             }
                             break;
                            }
                        }

                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());  // Kullanıcı bulunamadı mesajı
                    }
                    break;

            }//switch bitiş
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
        System.out.println("2-)Tüm kullanıcıları gör");
        System.out.println("3-)Tüm Gönderileri gör");
        System.out.println("4-)Çıkış yap");
        System.out.print("ne yapmak istiyorsunuz?:");
    }

    public static void editProfile(){

    }
}