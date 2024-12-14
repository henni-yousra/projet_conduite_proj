package com.example.conduite.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.conduite.entities.AppUser;
import com.example.conduite.entities.Project;
import com.example.conduite.repos.AppUserRepo;
import com.example.conduite.services.AppUserService;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("/appusers")
public class AppUserController {
    private static final Logger logger = LoggerFactory.getLogger(AppUserController.class);

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        // Delete all persons when the application starts
        //appUserRepo.deleteAll();
        System.out.println("All persons have been deleted.");
    }

    @GetMapping("")
    public List<AppUser> getAllAppUsers() {
        //http://localhost:5000/appusers/viewUsers
        return appUserService.getAllAppUsers();  // Fetch all AppUsers from the database
    }
    
    //@PostMapping("/addUser")
    @Transactional
    public AppUser addAppUser(String name, String email, String role, String password) {
        System.out.println("Adding a new person to the table...");
        return appUserService.addAppUser(name, email, role, password);
    }

    public AppUser getAppUserById(@RequestParam Long id) {
        return appUserService.getAppUserById(id);
    }

    // TODO: list of projects that the user is a part of
    /* public List<Project> getProjectsByAppUserId(@RequestParam Long id) {
        return appUserService.getProjectsByAppUserId(id);
    } */

}
