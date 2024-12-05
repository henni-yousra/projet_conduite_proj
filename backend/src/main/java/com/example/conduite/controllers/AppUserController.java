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
        //appUserRepo.deleteAll();
        System.out.println("All persons have been deleted.");
    }

    @GetMapping("")
    public List<AppUser> getAllAppUsers() {
        //http://localhost:5000/appusers/viewUsers
        return appUserRepo.findAll();  // Fetch all AppUsers from the database
    }
    
    //@PostMapping("/addUser")
    @Transactional
    public AppUser addAppUser(String name, String email, String role, String password) {
        System.out.println("Adding a new person to the table...");
        // Add person to the database
        AppUser appUser = new AppUser(name,email, role, password);
        appUserRepo.save(appUser); // TODO : this line was causing problems, without it the code runs but it is needed to send the data to the mainController
        return appUser;
    }

    public AppUser getAppUserById(@RequestParam Long id) {
        AppUser appUser = appUserRepo.getReferenceById(id);
        System.out.println("Person found: " + appUser.getName());
        return appUser;
    }

}
