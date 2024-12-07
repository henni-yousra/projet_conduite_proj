package com.example.conduite.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.conduite.entities.AppUser;
import com.example.conduite.repos.AppUserRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Service
public class AppUserService {
    
    @PersistenceContext
    private EntityManager entityManager;

    public AppUser findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT u FROM AppUser u WHERE u.email = :email", AppUser.class)
                                .setParameter("email", email)
                                .getSingleResult();
        } catch (Exception e) {
            return null;  // Handle cases where no user is found
        }
    }

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
    public void addPersonToDatabase(String name, String email, String role, String password) {
        System.out.println("Adding a new person to the table...");
        
        // Check if the email already exists in the database
        if (emailExists(email)) {
            System.out.println("Error: Email already exists in the database.");
            return;  // Prevent adding the person if email exists
        }
        
        String sql = "INSERT INTO dbConduiteProj.appuser (name, email, role) VALUES (?,?,?)";
        
        // Execute the insert query
        jdbcTemplate.update(sql, name, email, role, password);
        System.out.println("Person added to the database: Name - " + name + ", Email - " + email + ", Role - " + role + ", Password - " + password);
    }

    @Autowired
    private AppUserRepo appUserRepo;

    @Transactional
    public AppUser registerUser(String name, String email, String role, String password) {
        // Check if the email already exists
        if (emailExists(email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Create and save the user
        AppUser user = new AppUser(name, email, role, password);
        return appUserRepo.save(user);
    }

    // Check if email exists in the database
    private boolean emailExists(String email) {
        return appUserRepo.findByEmail(email) != null;
    }

    public boolean validateUserCredentials(String email, String password) {
        AppUser user = appUserRepo.findByEmail(email);
        if (user != null) {
            // Use .equals() to compare the strings
            if (password.equals(user.getPassword())) {
                return true;  // Valid user
            }
        }
        return false;  // Invalid credentials
    }

    public List<AppUser> findAllById(String[] parts) {
        JSONArray jsonArray = new JSONArray();
        for (String part : parts) {
            jsonArray.add(part);
        }

        List<Long> ids = new ArrayList<>();
        for (Object obj : jsonArray) {
            ids.add(Long.parseLong((String) obj));
        }
        
        List<AppUser> users = new ArrayList<>();
        for (Long id : ids) {
            AppUser user = appUserRepo.findById(id).orElse(null);
            if (user != null) {
                users.add(user);
            }
        }

        return users;
    }
    
}
