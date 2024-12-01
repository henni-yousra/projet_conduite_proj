package com.example.conduite.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.conduite.entities.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    
}
