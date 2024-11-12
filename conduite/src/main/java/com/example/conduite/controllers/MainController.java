package com.example.conduite.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    // Delegates creating a new AppUser to the AppUserController
    @PostMapping("/addAppUser")
    public String addAppUser(@RequestParam String name, @RequestParam String email) {
        appUserController.addAppUser(name, email);  // Calls method from AppUserController
        return "redirect:/";  // Redirect back to the main dashboard
    }

    // Delegates creating a new Project to the ProjectController
    @PostMapping("/projects/addProject")
    public String addProject(@RequestParam String name, @RequestParam String description) {
        projectController.addProject(name, description);  // Calls method from ProjectController
        return "redirect:/";  // Redirect back to the main dashboard
    }
}
