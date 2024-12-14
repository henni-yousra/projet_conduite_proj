package com.example.conduite.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.conduite.entities.AppUser;
import com.example.conduite.entities.Project;
import com.example.conduite.repos.ProjectRepo;

@Service
public class ProjectService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    public void setTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    public Project addProjectToDatabase(String name, String description) {
        Project project = new Project(name, description);
        projectRepo.save(project);
        return project;
    }

    public ResponseEntity<Map<String, Object>> getMembers(Long id) {
        Project project = projectRepo.getReferenceById(id);
        System.out.println("Project ID: " + project.getId());
        Map<String, Object> response = new HashMap<>();
        ArrayList<AppUser> members = new ArrayList<>(project.projectMembers());
        System.out.println("Members of the project: " + members);
        response.put("members", members);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, String>> addMembersToProject(Long id,List<AppUser> users) {
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

    public ResponseEntity<Map<String, String>> removeMembersFromProject(Long id, List<AppUser> users) {
        System.out.println("Removing members from the project...");
        Map<String, String> response = new HashMap<>();
        Project project = projectRepo.getReferenceById(id);
        // append this list to the project's members list
        project.removeMembers(users);
        System.out.println("Members to remove from the project: " + users);
        System.out.println("Project ID: " + project.getId());
        /* saves the modified project to the database
         * because of @ManyToMany @JoinTable
         */
        projectRepo.save(project);
        response.put("message", "Members removed successfully.");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, String>> deleteProject(Long id) {
        Map<String, String> response = new HashMap<>();
        System.out.println("Deleting project with ID: " + id);
        projectRepo.deleteById(id);
        response.put("message", "Project deleted successfully.");
        return ResponseEntity.ok(response);
    }
}
