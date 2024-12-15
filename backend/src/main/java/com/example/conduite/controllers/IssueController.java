// package com.example.conduite.controllers;

// import com.example.conduite.entities.Issue;
// import com.example.conduite.services.IssueService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/issues")
// @CrossOrigin(origins = "http://localhost:4200") // Allow CORS from the frontend

// public class IssueController {

//     @Autowired
//     private IssueService issueService;

//     @PostMapping("/create")
//     public ResponseEntity<?> createIssue(@RequestBody Issue issue) {
//         try {
//             Issue createdIssue = issueService.saveIssue(issue);
//             return ResponseEntity.ok(createdIssue);
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                                 .body("Error creating issue: " + e.getMessage());
//         }
//     }

// }
