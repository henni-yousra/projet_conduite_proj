package com.example.conduite.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Documentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String documentationText;
    
    private Long projectId;


    public Documentation() {
    }


    public Documentation(Long projectId, String documentationText) {
        this.projectId = projectId;
        this.documentationText = documentationText;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentationText() {
        return documentationText;
    }

    public void setDocumentationText(String documentationText) {
        this.documentationText = documentationText;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
