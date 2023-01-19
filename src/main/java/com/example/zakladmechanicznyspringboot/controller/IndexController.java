package com.example.zakladmechanicznyspringboot.controller;

import com.example.zakladmechanicznyspringboot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller

public class IndexController {

    //wstrzykujemy zależność userRepository
    //jest to tzw bean ktory utworzy nam obiekt tej klasy gdy jej potrzebujemy
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String welcomePage() {

        return "stronaPowitalna";
    }

    //action zalogowany jest w htmlu stronaPowitalna
    @PostMapping("/zalogowany")
    public String logowanie(@ModelAttribute UserLogging userLogging) {
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
                return "wyborWlasciciel";

            } else if (Objects.equals(userLogging.getType(), "Kierownik")) {
                System.out.println("Loguje sie Kierownik");
                System.out.println(zalogowany);
                return "widokHomeKierownik";


            } else if (Objects.equals(userLogging.getType(), "Pracownik")) {
                System.out.println(zalogowany);
                return "widokHomePracownik";
            }

        }

        return "welcome";
    }




    @PostMapping("/tworzenieZakladu")
    public String tworzenieZakladu(@ModelAttribute Zaklad zaklad, Model model) {
        userRepository.createWorkshop(zaklad);
        model.addAttribute("firstname", zaklad.getName());
        return "welcome";
    }

    @PostMapping("/rejestracja")
    public String rejestracja(@ModelAttribute UserRegistering userRegistering, Model model, Zaklad zaklad) {
        System.out.println(userRegistering);
        if (userRegistering.getRole() == null){
            System.out.println("Rejestrujemy pracownika ktory nie ma typu, ustawimy domyslny typ czyli pracownik");
            userRegistering.setRole("Pracownik");
        }
        userRepository.addUserToDb(userRegistering, zaklad);
        model.addAttribute("firstname", userRegistering.getFirstName());
        model.addAttribute("lastname", userRegistering.getLastName());
        return "welcome";
    }


    @PostMapping("/deleteMan")
    public String deleteMan(int id) {
        userRepository.deleteMan(id);
        return "success";
    }
    @PostMapping("/id")
    public String deleteEmp(int id) {
            userRepository.deleteEmp(id);
            return "success";
            // }
        }


}
