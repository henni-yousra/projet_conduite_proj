package com.example.conduite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.conduite.entities.AppUser;
import com.example.conduite.repos.AppUserRepo;

@Service
public class AppUserService {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void clearTable() {
        String sql = "DELETE FROM dbConduiteProj.appuser";
        jdbcTemplate.update(sql);
    }

    @Transactional
    public void addPersonToDatabase(String name, String email) {
        System.out.println("Adding a new person to the table...!");
        String sql = "INSERT INTO dbConduiteProj.appuser (name, email) VALUES (?,?)";

        // Execute the insert query
        jdbcTemplate.update(sql, name, email);
        System.out.println("Person added to the database: Name - " + name + ", Email - " + email + ", Role - ");
    }
    @Autowired
    private AppUserRepo appUserRepo;

    @Transactional
    public AppUser registerUser(String name, String email, String role, String password) {
        AppUser user = new AppUser(name, email, role, password);
        return appUserRepo.save(user);
    }

    public boolean validateUserCredentials(String email, String password) {
        AppUser user = appUserRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;  // Valid user
        }
        return false;  // Invalid credentials
    }




}
