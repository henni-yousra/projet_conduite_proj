package com.example.conduite.services;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.conduite.entities.AppUser;
import com.example.conduite.entities.Project;
import com.example.conduite.repos.AppUserRepo;
import com.example.conduite.repos.ProjectRepo;

@Service
public class ProjectService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    public void setTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private ProjectRepo projectRepository;

    public Project getProjectById(Long projId) {
        return projectRepository.findById(projId).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    @Transactional
    public void clearTable() {
        String sql = "DELETE FROM dbConduiteProj.project";
        jdbcTemplate.update(sql);
    }

    public void addProjectToDatabase(String name, String description) {
        System.out.println("Adding a new project to the table...!");
        String sql = "INSERT INTO dbConduiteProj.project (projname, projdesc) VALUES (?,?)";

        // Execute the insert query
        jdbcTemplate.update(sql, name, description);
        System.out.println("Project added to the database: Name - " + name + ", Description - " + description);
    }

    public void deleteProject(Long projId) {
        
        String sql = "DELETE INTO dbConduiteProj.project WHERE projid = ?";
        jdbcTemplate.update(sql, projId);
        System.out.println("Project deleted from the database: ID - " + projId);
    }

}
