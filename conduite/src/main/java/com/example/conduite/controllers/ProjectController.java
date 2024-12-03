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
        // Delete all projects when the application starts
        //projectRepo.deleteAll();
        //projectService.clearTable();
        System.out.println("All projects have been deleted.");
    }

    @GetMapping("")
    public List<Project> getAllProjects() {
        //http://localhost:5000/projects/viewProjects
        return projectRepo.findAll();  // Fetch all Projects from the database
    }

    //@GetMapping("")
    public Project getProjectById(@RequestParam Long id) {
        Project project = projectRepo.getReferenceById(id);
        System.out.println("Project found: " + project.getName());
        return project;
    }

    //@PostMapping("/addProject")
    @Transactional
    public Project addProject(@RequestParam String name, @RequestParam String description) {
        System.out.println("Adding a new project to the table...");
        Project project = new Project(name, description);
        projectRepo.save(project);
        return project;
    }

/*     @PostMapping("/deleteProject/{id}")
    @Transactional
    public String deleteProject(@PathVariable Long id) {
        projectRepo.deleteById(id);
        return "redirect:/index";
    } */
}
