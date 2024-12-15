package com.example.conduite.repos;

import com.example.conduite.entities.Documentation;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentationRepository extends JpaRepository<Documentation, Long> {
    List<Documentation> findByProjectId(Long projectId);
}
