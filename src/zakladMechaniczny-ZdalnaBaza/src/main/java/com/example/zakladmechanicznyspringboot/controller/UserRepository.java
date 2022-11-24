package com.example.zakladmechanicznyspringboot.controller;
import com.example.zakladmechanicznyspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {

    @Autowired
    static
    JdbcTemplate jdbcTemplate;

    public boolean addUserToDb(User user){
        jdbcTemplate.update("INSERT INTO User(firstName, lastName, email, password) values(?, ?, ?, ?)",
                user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        System.out.println("Dodano do bazy");
        //jezeli wszytko sie powiedzie zwracamy true,
        return true;
    }
    public static User getByIdMan(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, lastname FROM Kierownik WHERE " +
                "id = ?", BeanPropertyRowMapper.newInstance(User.class), id);

    }
    public boolean deleteMan(int id){

        User user = UserRepository.getByIdMan(id);
        if (user != null){
            jdbcTemplate.update("DELETE FROM Kierownik WHERE id=?");
            System.out.println("Manager deleted successfully");
            return true;
        }else {
            System.out.println("There is no such Manager");
            return false;
        }
    }
}
