package com.example.conduite.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.conduite.entities.Issue;
import com.example.conduite.repos.IssueRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Service
public class IssueService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IssueRepo issueRepo;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void clearTable() {
        issueRepo.deleteAll();
    }

    public List<Issue> getAllIssues() {
        return issueRepo.findAll();  
    }

    public Issue addIssue(String issue_name, String issue_desc ) {
        Issue issue = new Issue(issue_name, issue_desc);
        issueRepo.save(issue);
        return issue;
    }

    public Issue getIssueById(@RequestParam Long id) {
        Issue issue = issueRepo.getReferenceById(id);
        System.out.println("Issue found: " + issue.getIssue_name());
        return issue
    }
}