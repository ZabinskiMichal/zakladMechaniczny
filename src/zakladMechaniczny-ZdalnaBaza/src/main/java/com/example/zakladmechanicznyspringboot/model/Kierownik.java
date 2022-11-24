package com.example.zakladmechanicznyspringboot.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Kierownik {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
