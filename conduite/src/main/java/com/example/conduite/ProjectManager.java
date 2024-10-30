package com.example.conduite;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProjectManager {
    private List<Project> projects = new ArrayList<>();

    public ProjectManager() {
    }

    @GetMapping("/")
    public String showDashboard(Model model) {
        model.addAttribute("projects", projects);
        return "index";
    }

    @GetMapping("/projects/createProject")
    public String createProjectForm() {
        // creates the createProject HTML page
        return "projects/createProject";
    }

    @PostMapping("/saveProject")
    public String saveProject(@RequestParam String name, @RequestParam String description) {
        // Retrieves the project name and description and adds it to the list of projects
        projects.add(new Project(name, description));
        return "redirect:/";
    }

    /*
     * TODO : view project => open a new page that will display the project info
     * + project edit and update
     */

    @PostMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable int id, RedirectAttributes redirectAttributes) {
        // Will receive the project id to delete and will remove it from the list of projects
        if (id >= 0 && id < projects.size()) {
            projects.remove(id);
            redirectAttributes.addFlashAttribute("message", "Project deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Project not found!");
        }
        return "redirect:/";
    }
}
