package com.example.zakladmechanicznyspringboot.controller;

import com.example.zakladmechanicznyspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "zalogujSieJako";
    }

    //tutaj trzeba zrobiz meotode post z poprzedniego get mapping

    @PostMapping("/choose")
    public String choseAccountType(@RequestBody String wybor){
        System.out.println(wybor);
//        if(wybor == "logujeSieJako=wlasciciel"){
//            //zwrocimy logowanie dla wlasciciela
//        }else if(wybor == "logujeSieJako=kierownik"){
//        }else if(wybor == "logujeSieJako=pracownik"){
//        }
        //zwaracmy docelowa strone do logowania
        return "index";
    }

    @PostMapping("/register")
    public String loging(@ModelAttribute User user, Model model) {
        //ten model to jest interfejs przechowujacy dane
        System.out.println(user.toString());
        userRepository.addUserToDb(user);
        //zwracamy template welcome, ktory znajduje sie w tempaltes
        //wswietlimy z nim pierwsz eoraz drugie imie usera
        model.addAttribute("firstname", user.getFirstName());
        model.addAttribute("lastname", user.getLastName());
        return "welcome";
    }
    @GetMapping("/id")
    public User getById(@PathVariable("id") int id) {

         return UserRepository.getByIdMan(id);
    }
    @DeleteMapping("/id")
    public void deleteMan(@PathVariable("id") int id){

    }
}
