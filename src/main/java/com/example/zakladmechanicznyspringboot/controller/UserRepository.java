package com.example.zakladmechanicznyspringboot.controller;

import com.example.zakladmechanicznyspringboot.model.User;
import com.example.zakladmechanicznyspringboot.model.UserLogging;
import com.example.zakladmechanicznyspringboot.model.UserRegistering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //pamietac o zasadzie pojedynczej odpowiedzialnosci

    public boolean addUserToDb(UserRegistering user){
        jdbcTemplate.update("INSERT INTO " + user.getType() +"(firstName, lastName, email, password) values(?, ?, ?, ?)",
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        System.out.println("Dodano do bazy");
        //jezeli wszytko sie powiedzie zwracamy true,
        return true;
    }


    public User loginUser(UserLogging userLogging){

        try{

            return jdbcTemplate.queryForObject("SELECT id, firstName, lastName, email, password  FROM "  + userLogging.getType() + " WHERE " +
                    "email = ? AND password = ?", BeanPropertyRowMapper.newInstance(User.class), userLogging.getEmail(), userLogging.getPassword());
        }catch (DataAccessException e){
            e.printStackTrace();
            System.out.println("nie udalo sie znalesc takiego usera");
            return null;
        }
    }

}
