package com.example.zakladmechanicznyspringboot.controller;

import com.example.zakladmechanicznyspringboot.model.User;
import com.example.zakladmechanicznyspringboot.model.UserLogging;
import com.example.zakladmechanicznyspringboot.model.UserRegistering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class IndexController {

    //wstrzykujemy zależność userRepository
    //jest to tzw bean ktory utworzy nam obiekt tej klasy gdy jej potrzebujemy
    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    public String welcomePage() {

//            return "rejestracja";
        return "stronaPowitalna";
    }

    //    action zalogowany jest w htmlu stronaPowitalna
    @PostMapping("/zalogowany")
    public String logowanie(@ModelAttribute UserLogging userLogging, Model model) {
        //tutaj trzeba będzie zaimplenetować logowanie do systemu

        if (Objects.isNull(userRepository.loginUser(userLogging))) {
            System.out.println("mamy nula");
            System.out.println("nie ma takiego usera w wybranej tabeli");
            return "stronaPowitalna";
        } else {

            //jezeli nie zwroci nulla to tworzymy obiekt
            User zalogowany = new User(userRepository.loginUser(userLogging));

            if (Objects.equals(userLogging.getType(), "Wlasciciel")) {
                System.out.println(zalogowany);
                //tutaj bedziemu tworzyc poszczegolne pbiekty
//                rejestracja();
                return "widokHomeWlasciciela";

            } else if (Objects.equals(userLogging.getType(), "Kierownik")) {
                System.out.println("Loguje sie Kierownik");

                System.out.println(zalogowany);
                return "widokHomeKierownika";


            } else if (Objects.equals(userLogging.getType(), "Pracownik")) {
                System.out.println(zalogowany);
                return "widokHomePracownika";
            }
        }
        return "welcome";
    }



    //włascicel rejestruje usera do bazy
    //poki co dodajemy do ogólnej tabeli User, trzeba bedzie to zmienic
    @PostMapping("/rejestracja")
    public String rejestracja(@ModelAttribute UserRegistering userRegistering, Model model){
        System.out.println(userRegistering);
        userRepository.addUserToDb(userRegistering);
//        userRepository.addUserToDb(user);
        return "welcome";

    }


//    @PostMapping("/rejestracja")
//    public String register(@ModelAttribute User user, Model model) {
//        //ten model to jest interfejs przechowujacy dane
//        System.out.println(user.toString());
//        userRepository.addUserToDb(user);
//        //zwracamy template welcome, ktory znajduje sie w tempaltes
//        //wswietlimy z nim pierwsz eoraz drugie imie usera
//        model.addAttribute("firstname", user.getFirstName());
//        model.addAttribute("lastname", user.getLastName());
//        return "welcome";
//    }


}
