package com.example.conduite.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "project", schema = "dbconduiteproj")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proj_id")
    private int id;

    @Column(name = "projname")
    private String name;

    @Column(name = "projdesc")
    private String description;

    @ManyToMany
    @JoinTable(
        name = "projectmembers",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    //@JsonIgnore // Prevents cyclic serialization, because there is a circular reference between Project and AppUser; Projects in AppUser and AppUsers in Project
    @JsonManagedReference
    private List<AppUser> members;  // List of users assigned to the project

    /* TODO :
     * Issues
     *  list of issues 
     * private List<<String,String,String>> issues;
     * Tasks
     *  list of tasks linked to the an issue
     * private List<String,<String,String,String>> tasks;
     * Documentation
     *  project documentation
     */

    public Project() {}

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<AppUser> projectMembers() {
        return members;
    }
    
    public void addMembers(List<AppUser> users) {
        members.addAll(users);
    }

    public void removeMembers(List<AppUser> users) {
        members.removeAll(users);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
