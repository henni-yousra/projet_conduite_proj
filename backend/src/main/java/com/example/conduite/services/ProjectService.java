package com.example.conduite.services;

import java.util.List;

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

    public void addMembersToProject(Long projId, List<AppUser> users) {
        /* String Values = "";
        for (AppUser user : users) {
            Values += "(" + projId + "," + user.getId() +  "," + user.getRole() + "),";
        }

        String sql = "INSERT INTO dbConduiteProj.projectmembers (project_id, user_id, role) VALUES" +  Values + ";";
        jdbcTemplate.update(sql);  
        System.out.println("Members added to the project: " + users.size()); */    
        if (users == null || users.isEmpty()) {
            System.out.println("No members to add.");
            return;
        }

        StringBuilder sql = new StringBuilder("INSERT INTO dbConduiteProj.projectmembers (project_id, user_id, role) VALUES ");
        StringBuilder values = new StringBuilder();

        for (AppUser user : users) {
            values.append("(?, ?, ?),");
        }

        // Remove the trailing comma
        values.setLength(values.length() - 1);

        sql.append(values);

        // Prepare the parameters
        Object[] params = new Object[users.size() * 3];
        int index = 0;
        for (AppUser user : users) {
            params[index++] = projId;
            params[index++] = user.getId();
            params[index++] = user.getRole();
        }

        jdbcTemplate.update(sql.toString(), params);
        System.out.println("Members added to the project: " + users.size());
    }

    public List<AppUser> getUsersByIds(String string) {
        // this string is of the form : [{"id":34,"name":"Odysseus","email":"odysseus@example.com","role":"SCRUM_MASTER","password":"w","selected":true},{"id":35,"name":"Dok","email":"dok@example.com","role":"MEMBER","password":"w","selected":true},{"id":36,"name":"Darwin","email":"darwin@example.com","role":"MEMBER","password":"w","selected":true},{"id":37,"name":"Wolf","email":"wolf@example.com","role":"MEMBER","password":"w","selected":true}]

        // remove the square brackets
        string = string.substring(1, string.length() - 1);

        // split the string by commas
        String[] parts = string.split(",");
        List<AppUser> users = appUserService.findAllById(parts);

        return users;
    }

}
