package com.example.conduite.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.conduite.entities.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    
}
