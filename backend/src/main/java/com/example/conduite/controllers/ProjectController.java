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
import org.springframework.web.bind.annotation.DeleteMapping;
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
        return projectService.getAllProjects();  // Fetch all Projects from the database
    }

    //@GetMapping("")
    public Project getProjectById(@RequestParam Long id) {
        return projectService.getProjectById(id);
    }

    //@PostMapping("/addProject")
    @Transactional
    public Project addProject(@RequestParam String name, @RequestParam String description) {
        System.out.println("Adding a new project to the table...");
        return projectService.addProjectToDatabase(name, description);
    }

    @GetMapping("/{id}/getMembers")
    public ResponseEntity<Map<String, Object>> getMembers(@PathVariable Long id) {
        return projectService.getMembers(id);
    }

    @PostMapping("/{id}/addMembers")
    public ResponseEntity<Map<String, String>> addMembersToProject(@PathVariable Long id, @RequestBody List<AppUser> users) {
        return projectService.addMembersToProject(id, users);
    }

    //removeMembers
    @PostMapping("/{id}/removeMembers")
    public ResponseEntity<Map<String, String>> removeMembersFromProject(@PathVariable Long id, @RequestBody List<AppUser> users) {
        return projectService.removeMembersFromProject(id, users);
    }

    @DeleteMapping("/deleteProject/{id}")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }
}
