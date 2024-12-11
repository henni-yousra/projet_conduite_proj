package com.example.conduite.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.conduite.entities.AppUser;
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

    @Autowired
    private AppUserController appUserController;

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

    @GetMapping("/{id}/getMembers")
    public ResponseEntity<Map<String, Object>> getMembers(@PathVariable Long id) {
        Project project = projectRepo.getReferenceById(id);
        System.out.println("Project ID: " + project.getId());
        Map<String, Object> response = new HashMap<>();
        System.out.println("Members of the project: " + project.projectMembers());
        response.put("members", project.projectMembers());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/addMembers")
    public ResponseEntity<Map<String, String>> addMembersToProject(@PathVariable Long id, @RequestBody List<AppUser> users) {
        System.out.println("Adding members to the project...");
        Map<String, String> response = new HashMap<>();
        Project project = projectRepo.getReferenceById(id);
        // append this list to the project's members list
        project.addMembers(users);
        System.out.println("Members to add to the project: " + users);
        System.out.println("Project ID: " + project.getId());
        /* saves the modified project to the database
         * because of @ManyToMany @JoinTable
         */
        projectRepo.save(project);
        response.put("message", "Members added successfully.");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/deleteProject/{id}")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteProject(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        System.out.println("Deleting project with ID: " + id);
        projectService.deleteProject(id);
        response.put("message", "Members added successfully.");
        return ResponseEntity.ok(response);
    }
}
