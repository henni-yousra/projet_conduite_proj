package com.example.conduite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.conduite.entities.Documentation;
import com.example.conduite.repos.DocumentationRepository;

import java.util.List;
import java.util.Optional;


@Service
public class DocumentationService {

    @Autowired
    private DocumentationRepository documentationRepository;

    public Documentation saveDocumentation(Long projectId, String documentationText) {
        Documentation documentation = new Documentation(projectId, documentationText);
        return documentationRepository.save(documentation);
    }
    

    public List<Documentation> getDocumentation(Long projectId) {
        return documentationRepository.findByProjectId(projectId);
    }
}
