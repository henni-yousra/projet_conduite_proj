package com.example.conduite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.conduite.entities.Project;
import com.example.conduite.repos.ProjectRepo;

@Service
public class ProjectService {
    private JdbcTemplate jdbcTemplate;

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

}
