package com.example.conduite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addPersonToDatabase(String name, String email) {
        String sql = "INSERT INTO appuser (name, email) VALUES (?,?)";

        // Execute the insert query
        jdbcTemplate.update(sql,name, email);
        System.out.println("Person added to the database: Name - " + name + ", Email - " + email);
    }
}
