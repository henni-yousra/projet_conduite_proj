package com.example.conduite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
