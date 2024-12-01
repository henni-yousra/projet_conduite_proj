package com.example.conduite.entities;

import java.util.List;

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
      name = "project_user", 
      joinColumns = @JoinColumn(name = "proj_id"), 
      inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<AppUser> members;  // List of users assigned to the project

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
