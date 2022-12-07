package com.example.zakladmechanicznyspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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


