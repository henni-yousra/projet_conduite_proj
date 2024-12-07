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

    @PostMapping("/{id}/addMembers")
    public ResponseEntity<Map<String, String>> addMembers(@RequestBody Map<String, String> membersToAdd) {
        System.out.println("Adding members to the project...");
        Map<String, String> response = new HashMap<>();
        Long id = Long.valueOf(membersToAdd.get("projectId"));
        Project project = projectRepo.getReferenceById(id);
        // append this list to the project's members list
        
        List<AppUser> users = projectService.getUsersByIds(membersToAdd.get("members"));



        project.addMembers(users);
        System.out.println("Members to add to the project: " + users);
        projectService.addMembersToProject(Long.valueOf(project.getId()), users);
        projectRepo.save(project);
        response.put("message", "Members added successfully.");
        return ResponseEntity.ok(response);
    }


    /* public void addMembers(@RequestParam Long id, @RequestParam Long userId) {
        Project project = projectRepo.getReferenceById(id);
        AppUser user = entityManager.getReference(AppUser.class, userId);
        project.addMember(user);
        projectRepo.save(project);
    } */



    /* @GetMapping("/members")
    public List<AppUser> getMembers(@RequestParam Long id) {
        Project project = projectRepo.getReferenceById(id);
        System.out.println("Project found: " + project.getName());
        return project.projectMembers();
    } */

/*     @PostMapping("/deleteProject/{id}")
    @Transactional
    public String deleteProject(@PathVariable Long id) {
        projectRepo.deleteById(id);
        return "redirect:/index";
    } */
}
