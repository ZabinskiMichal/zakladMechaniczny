package com.example.zakladmechanicznyspringboot.controller.wlascicielControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller 
public class wlascicielController {

    @PostMapping("/wyborWlasciciel")
    public String choseAccountType(@RequestBody String wybor) {
        return switch (wybor) {
            case "upvote=wybor1" -> "stworzZaklad";
            case "upvote=wybor2" -> "dodajPracownika";
            case "upvote=wybor3" -> "deleteEmp";
            case "upvote=wybor4" -> "devInProgress";
            case "upvote=wybor5" -> "deleteMan";
            default -> "";
        };
    }

}
