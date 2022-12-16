package com.example.zakladmechanicznyspringboot.controller;

import com.example.zakladmechanicznyspringboot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.regex.Pattern;


@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //pamietac o zasadzie pojedynczej odpowiedzialnosci


//    public boolean addUserToDb(UserRegistering user){
//        jdbcTemplate.update("INSERT INTO " + user.getType() +"(firstName, lastName, email, password) values(?, ?, ?, ?)",
//                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
//        System.out.println("Dodano do bazy");
//        //jezeli wszytko sie powiedzie zwracamy true,
//        return true;
//    }


    //pracownikow bedziemy doda
    public void createWorkshop(Zaklad zaklad) {
        jdbcTemplate.execute("CREATE TABLE " + zaklad.getName() + "(id int NOT NULL AUTO_INCREMENT," +
                "  role varchar(45) NOT NULL," +
                "  firstName varchar(45) NOT NULL," +
                "  lastName varchar(45) NOT NULL," +
                "  email varchar(45) NOT NULL," +
                "  password varchar(45) NOT NULL," +
                "  gender varchar(45) DEFAULT NULL," +
                "  PRIMARY KEY (id))");
    }


    public void addUserToDb(UserRegistering user, Zaklad zaklad) {
            jdbcTemplate.update("INSERT INTO " + zaklad.getName() + " (role, firstName, lastName, email, password, gender) values(?, ?, ?, ?, ?, ?)",
                    user.getRole(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getGender());
            System.out.println("Dodano do bazy");

            //dodamy tez tymmczasowo do tabeli Pracownik/kierownik/wlasciciel
        jdbcTemplate.update("INSERT INTO " + user.getRole() + " (role, firstName, lastName, email, password, gender) values(?, ?, ?, ?, ?, ?)",
                user.getRole(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getGender());
//        }
    }

    //wersja funkcji ktora zwraca usera
    //zwracamy Usera
    public User returnKierownik(UserRegistering userRegistering, Zaklad zaklad){
        try{
            return jdbcTemplate.queryForObject("SELECT id, role, firstName, lastName, email, password, gender FROM " + zaklad.getName() + " WHERE " +
                    "role = ?", BeanPropertyRowMapper.newInstance(User.class), userRegistering.getRole());
        }catch (DataAccessException e){
            e.printStackTrace();
            System.out.println("nie udalo sie znalesc takiego usera");
            return null;
        }
    }
    //wersja funkcji ktora zwraca usera
    //zwracamy Usera
//    public User loginUser(UserLogging userLogging){
//        try{
//            return jdbcTemplate.queryForObject("SELECT id, firstName, lastName, email, password  FROM User WHERE " +
//                    "email = ? AND password = ?", BeanPropertyRowMapper.newInstance(User.class), userLogging.getEmail(), userLogging.getPassword());
//        }catch (DataAccessException e){
//            e.printStackTrace();
//            System.out.println("nie udalo sie znalesc takiego usera");
//            return null;
//        }
//    }



    public User loginUser(UserLogging userLogging) {

        try {
            return jdbcTemplate.queryForObject("SELECT id, role, firstName, lastName, email, password, gender FROM " + userLogging.getType() + " WHERE " +
                    "email = ? AND password = ?", BeanPropertyRowMapper.newInstance(User.class), userLogging.getEmail(), userLogging.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("nie udalo sie znalesc takiego usera");
            return null;
        }
    }

    public boolean checkIfEmailExist(UserRegistering userRegistering){
        try {
            jdbcTemplate.execute("SELECT * FROM " + userRegistering.getRole() + " WHERE email = " + "'" +userRegistering.getEmail() + "'");

            //zwracamy true gdy user o podanym mailu istenije
            return true;
        }catch (DataAccessException e){
            e.printStackTrace();
        }

        //zwracamy false jesli takiego usera nie ma
        return false;
    }


    public boolean addWorkingHours(String date, int hours, int idPracownika){
        try{
            jdbcTemplate.update("INSERT INTO pracownicyGodzinyPracy (idPracownika, data, iloscGodzin) VALUES (? ? ?)", idPracownika, date, hours);
            return true;
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return false;

    }

    //jeszcze nie dzila
    //metoda, kora na podtawie wybranych pol z klasy pracownik zwraca jego id
//    public int returnId(Pracownik pracownik){
//        try{
////            return jdbcTemplate.query("SELECT id FROM Pracownik");
//            return jdbcTemplate.queryForObject("SELECT id FROM Pracownik WHERE email = '" + pracownik.getEmail() + "' AND password = '" + pracownik.getPassword() + "'", new BeanPropertyRowMapper<>(Integer.class));
//        }catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//
//        return -1;
//    }

//    }
}
