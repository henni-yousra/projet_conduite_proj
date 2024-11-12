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

import com.example.conduite.entities.Project;
import com.example.conduite.repos.ProjectRepo;
import com.example.conduite.services.ProjectService;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;



@RestController
@RequestMapping("/projects")
public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EntityManager entityManager;


    @PostConstruct
    public void init() {
        // Delete all persons when the application starts
        projectRepo.deleteAll();
        System.out.println("All projects have been deleted.");
    }

    //@GetMapping("/add")
    public List<Project> getAllProjects() {
        return projectRepo.findAll();  // Fetch all Projects from the database
    }


    //@PostMapping("/addProject")
    @Transactional
    public ResponseEntity<String> addProject(String name, String description) {
        System.out.println("Adding a new project to the table...");
        // Add person to the database
        Project project = new Project(name, description);
        projectRepo.save(project);
        System.out.println("--going to db--");
        projectService.addProjectToDatabase(name, description);
        System.out.println("Project added to the database");
        return ResponseEntity.status(HttpStatus.CREATED).body("Project added successfully");
    }

}
