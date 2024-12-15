package com.example.conduite.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.conduite.entities.Issue;

@Repository
public interface IssueRepo extends JpaRepository<Issue, Long> {
}