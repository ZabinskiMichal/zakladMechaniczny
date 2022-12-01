package com.example.zakladmechanicznyspringboot.model;

import com.example.zakladmechanicznyspringboot.controller.UserRepository;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistering extends User{
    private String type;

    public UserRegistering(String firstName, String lastName, String email, String password, String type){
        super(firstName, lastName, email, password);
        this.type = type;
    }




    @Override
    public String toString() {
        return super.toString() + " oraz userRegistering{" +
                "type='" + this.type + '\'' +
                '}';
    }
}


