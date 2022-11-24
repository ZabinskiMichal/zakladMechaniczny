package com.example.zakladmechanicznyspringboot.controller;
import com.example.zakladmechanicznyspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean addUserToDb(User user){
        jdbcTemplate.update("INSERT INTO User(firstName, lastName, email, password) values(?, ?, ?, ?)",
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        System.out.println("Dodano do bazy");
        //jezeli wszytko sie powiedzie zwracamy true,
        return true;
    }
}
