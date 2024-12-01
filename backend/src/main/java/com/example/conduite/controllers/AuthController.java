package com.example.conduite.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.conduite.services.AppUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    

    @Autowired
    private AppUserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, String> userDTO) {
        Map<String, String> response = new HashMap<>();

        // Validate if all required fields are provided
        if (userDTO.get("name") == null || userDTO.get("name").isEmpty()) {
            response.put("message", "Name is required.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 400 Bad Request
        }
        if (userDTO.get("email") == null || userDTO.get("email").isEmpty()) {
            response.put("message", "Email is required.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 400 Bad Request
        }
        if (userDTO.get("role") == null || userDTO.get("role").isEmpty()) {
            response.put("message", "Role is required.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 400 Bad Request
        }
        if (userDTO.get("password") == null || userDTO.get("password").isEmpty()) {
            response.put("message", "Password is required.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 400 Bad Request
        }

        try {
            // Extract fields from the request body (name, email, role, and password)
            String name = userDTO.get("name");
            String email = userDTO.get("email");
            String role = userDTO.get("role");
            String password = userDTO.get("password");

            // Call AppUserService to register the user
            userService.registerUser(name, email, role, password);

            // Send response indicating success
            response.put("message", "User registered successfully.");
            return ResponseEntity.ok(response); // 200 OK
        } catch (Exception e) {
            // In case of an error, return an internal server error response
            response.put("message", "Error during registration: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error
        }
    }

     // Simple login endpoint
     @PostMapping("/login")
     public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> credentials) {
         Map<String, String> response = new HashMap<>();
     
         // Extract email and password from the request body
         String email = credentials.get("email");
         String password = credentials.get("password");
     
         System.out.println("Login attempt with email: " + email + " and password: " + password);  // Log the incoming credentials
     
         // Validate user credentials
         boolean isValidUser = userService.validateUserCredentials(email, password);
     
         if (isValidUser) {
             response.put("message", "Login successful.");
             return ResponseEntity.ok(response);  // 200 OK
         } else {
             response.put("message", "Invalid email or password.");
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);  // 401 Unauthorized
         }
     }
     

    // Optionally, you can add an endpoint to clear the user table (for testing or maintenance)
    @DeleteMapping("/clear")
    public ResponseEntity<Map<String, String>> clearTable() {
        Map<String, String> response = new HashMap<>();
        try {
            userService.clearTable();
            response.put("message", "Table cleared successfully.");
            return ResponseEntity.ok(response); // 200 OK
        } catch (Exception e) {
            response.put("message", "Error clearing the table: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error
        }
    }
}
