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

    public void addMembersToProject(Long projId, List<AppUser> users) {   
        if (users == null || users.isEmpty()) {
            System.out.println("No members to add.");
            return;
        }
        if (!projectRepository.existsById(projId)) {
            System.out.println("Project not found");
            return;
        }

        System.out.println("Incoming users: " + users);
        for (AppUser user : users) {
            System.out.println("User role: " + user.getRole());
        }

        /* StringBuilder sql = new StringBuilder("INSERT INTO dbConduiteProj.projectmembers (project_id, user_id, role) VALUES ");
        StringBuilder values = new StringBuilder(); */
        //ArrayList<String> params = new ArrayList<>();

        for (AppUser user : users) {
            //values.append("(?,?,?),");
            //values.append("(" + projId + "," + user.getId() +  "," + "'" + user.getRole() + "'" + "),");
            /* params.add(projId.toString());
            params.add(user.getId().toString());
            params.add(user.getRole()); */
            jdbcTemplate.update("INSERT INTO dbConduiteProj.projectmembers (project_id, user_id) VALUES (?,?) ;", projId, user.getId());
        }

        // Remove the trailing comma
        /* values.setLength(values.length() - 1);
        System.out.println("Values: " + values);
        System.out.println("Values length: " + values.length());

        sql.append(values);
        sql.append(";");

        System.out.println("SQL: " + sql); */

        // check if the users arent already in the project
        /* Project project = projectRepository.findById(projId).get();
        List<AppUser> members = project.getMembers();
        for (AppUser user : users) {
            if (members.contains(user)) {
                System.out.println("User already in the project: " + user.getName());
                return;
            }
        } */
        
        /* for(String param: params) {
            System.out.println("Param: " + param);
        } */
        //jdbcTemplate.update(sql, params.toArray(new String[params.size()]));
        System.out.println("Members added to the project: " + projectRepository.findById(projId).get().getName());
    }

}
