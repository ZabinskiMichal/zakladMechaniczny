package com.example.zakladmechanicznyspringboot.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLogging {
    private String email;
    private String password;
    private String type;
}
