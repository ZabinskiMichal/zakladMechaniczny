package com.example.zakladmechanicznyspringboot.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wlasciciel {
    private int id;
    private String Name;
    private String Lastname;

}
