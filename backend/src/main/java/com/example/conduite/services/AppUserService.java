package com.example.conduite.services;

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

    @Autowired
    private AppUserRepo appUserRepo;

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
        appUserRepo.deleteAll();
        /* String sql = "DELETE FROM dbConduiteProj.appuser";
        jdbcTemplate.update(sql); */
    }

    public List<AppUser> getAllAppUsers() {
        //http://localhost:5000/appusers/viewUsers
        return appUserRepo.findAll();  // Fetch all AppUsers from the database
    }

    public AppUser addAppUser(String name, String email, String role, String password) {
        AppUser appUser = new AppUser(name,email, role, password);
        appUserRepo.save(appUser);
        return appUser;
    }

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
    public boolean emailExists(String email) {
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


    public AppUser getAppUserById(Long id) {
        AppUser appUser = appUserRepo.getReferenceById(id);
        System.out.println("Person found: " + appUser.getName());
        return appUser;
    }

    // TODO: list of projects that the user is a part of
    /* public List<Project> getProjectsByAppUserId(Long id) {
        AppUser appUser = appUserRepo.getReferenceById(id);
        System.out.println("Person found: " + appUser.getName());
        return appUser.getProjects();
    } */
    
}
