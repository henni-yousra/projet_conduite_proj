package com.example.conduite.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.conduite.entities.AppUser;
import com.example.conduite.entities.Project;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private AppUserController appUserController;  // Injecting AppUserController

    @Autowired
    private ProjectController projectController;  // Injecting ProjectController
    
    
    private List<Project> projects = new ArrayList<>();

    public MainController() {
        
    }

    @GetMapping({"/", "/index"})
    public String showMainDashboard(Model model) {
        model.addAttribute("appUsers", appUserController.getAllAppUsers());
        model.addAttribute("projects", projectController.getAllProjects());
        return "index";
    }

    @GetMapping("/projects/addProject")
    public String createProjectForm() {
        // creates the createProject HTML page
        return "projects/addProject";
    }

    @GetMapping("/appusers/addUser")
    public String createAppUserForm() {
        // creates the createAppUser HTML page
        return "appusers/addUser";
    }

    // Delegates creating a new AppUser to the AppUserController
    @PostMapping("/appusers/addUser")
    public String addAppUser(@RequestParam String name, @RequestParam String email, @RequestParam String role, @RequestParam String password) {
        AppUser appUser = appUserController.addAppUser(name, email, role, password);  // Calls method from AppUserController
        return "redirect:/viewUser/" + appUser.getId();  // Redirect back to the main dashboard
    }

    // Delegates creating a new Project to the ProjectController
    @PostMapping("/projects/addProject")
    public String addProject(@RequestParam String name, @RequestParam String description) {
        Project project = projectController.addProject(name, description);  // Calls method from ProjectController
        return "redirect:/viewProject/" + project.getId();  // Redirect to the viewProject page with the new project's ID
    }

    @GetMapping("/viewProject/{projId}")
    public String viewProject(@PathVariable("projId") Long id, Model model) {
        // redirected to this page to display the project details
        System.out.println("Viewing project with ID: " + id);
        Project project = projectController.getProjectById(id);  
        if (project != null) {
            model.addAttribute("project", project);
            return "projects/viewProject";
        } else {
            return "error/404";
        }
    }

    @GetMapping("/viewUser/{userId}")
    public String viewUser(@PathVariable("userId") Long id, Model model) {
        // redirected to this page to display the user details
        System.out.println("Viewing user with ID: " + id);
        AppUser appUser = appUserController.getAppUserById(id);  
        if (appUser != null) {
            model.addAttribute("appUser", appUser);
            return "appusers/viewUser";
        } else {
            return "error/404";
        }
    }

    /* @PostMapping("/deleteProject/{id}")
    @Transactional
    public String deleteProject(@PathVariable Long id) {
        projectController.deleteProject(id);
        return "redirect:/";
    } */
}
