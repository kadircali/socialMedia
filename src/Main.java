import controller.UserController;
import dataBaseSim.DataBase;
import entity.Admin;
import entity.User;
import exceptions.UserNotFoundException;
import repository.UserRepo;
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
• Kayıt olacak.
• Giriş yapacak.
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
• Tüm kullanıcıları listeleme
• Tüm postları listeleme
• Başka adminler kaydet
STT: 18/01/2025 cumartesi derse kadar
Proje github a gönderilip github linki gruptan paylaşılmalı.
         */

        //ön tanımlı admin girişi --> kullanıcı adı : admin şifre :1234

        int choice ;
        Scanner scan = new Scanner(System.in);


        DataBase dataBase = new DataBase();
        UserRepo userRepo = new UserRepo(dataBase);
        UserService userService = new UserService(userRepo);
        UserController userController = new UserController(userService);
        String userName ;
        String password;



        System.out.println("Kurusevka Sosyal Medya Uygulamasına Hoşgeldiniz");


        while (true){
            System.out.println("Ne yapmak istiyorsunuz?");
            System.out.println("1-)Üye Ol");
            System.out.println("2-)Giriş Yap");
            System.out.print("seçiminiz :");
            choice = scan.nextInt();



            switch (choice){

                case 1 :
                    //üye ol

                    System.out.print("lütfen kullanıcı adı belirleyiniz:");
                    userName = scan.next();
                    System.out.print("lütfen şifre belirleyiniz");
                    password =scan.next();


                    if(userController.userRegister(userName,password) ){

                        System.out.println("kullanici eklendi");


                    }else{
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
                            System.out.println("user");
                        } else if (loggedUser instanceof Admin) {
                            // admin girişi başarılı :adminlerin de profili olsun, admin paneli olsun, admin ekleme vs.
                            System.out.println("admin");
                        }


                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());  // Kullanıcı bulunamadı mesajı
                    }


                    break;



            }

        }

    }
}