package com.example.conduite.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
@Table(name = "issue", schema = "dbconduiteproj")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Long issue_id;

    @Column(name = "issue_name")
    private String issue_name;
    @Column(name = "issue_description")
    private String issue_desc;

    public Issue() {}

    public Issue(String issue_name, String issue_desc) {
        this.issue_name = issue_name;
        this.issue_desc = issue_desc;
    }

    public void setId(Long idToSet){
        this.issue_id = idToSet;
    }

    public void setIssueName(String name){
        this.issue_name = name;
    }

    public void setIssueDesc(String desc){
        this.issue_desc = desc;
    }

    public Long getIssue_id() {
        return this.issue_id;
    }

    public String getIssue_name() {
        return this.issue_name;
    }

    public String getIssue_desc() {
        return this.issue_desc;
    }
    
}