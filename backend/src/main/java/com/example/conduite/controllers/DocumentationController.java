package com.example.conduite.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.conduite.entities.Documentation;
import com.example.conduite.services.DocumentationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documentation")
public class DocumentationController {

    @Autowired
    private DocumentationService documentationService;

    @PostMapping("/save")
    public Documentation saveDocumentation(@RequestBody Documentation documentationRequest) {
        return documentationService.saveDocumentation(documentationRequest.getProjectId(), documentationRequest.getDocumentationText());
    }

    @GetMapping("/current/{projectId}")
    public ResponseEntity<List<Documentation>> getCurrentDocumentation(@PathVariable Long projectId) {
        try {
            List<Documentation> documentation = documentationService.getDocumentation(projectId);
            return ResponseEntity.ok(documentation);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
