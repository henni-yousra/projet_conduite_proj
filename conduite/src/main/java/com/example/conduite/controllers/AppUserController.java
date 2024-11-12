package com.example.conduite.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.conduite.entities.AppUser;
import com.example.conduite.repos.AppUserRepo;
import com.example.conduite.services.AppUserService;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("/appusers")
public class AppUserController {
        private static final Logger logger = LoggerFactory.getLogger(AppUserController.class);

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private EntityManager entityManager;


    @PostConstruct
    public void init() {
        // Delete all persons when the application starts
        appUserRepo.deleteAll();
        System.out.println("All persons have been deleted.");
    }

    //@GetMapping("/all")
    public List<AppUser> getAllAppUsers() {
        return appUserRepo.findAll();  // Fetch all AppUsers from the database
    }


    
    //@PostMapping("/addUser")
    @Transactional
    public ResponseEntity<String> addAppUser(String name, String email) {
        System.out.println("Adding a new person to the table...");
        // Add person to the database
        AppUser appUser = new AppUser(name,email);
        appUserRepo.save(appUser);
        appUserService.addPersonToDatabase(name, email);
        System.out.println("Person added to the database");
        return ResponseEntity.status(HttpStatus.CREATED).body("Person added successfully");
    }

}
