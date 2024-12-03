package com.example.conduite.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "appuser", schema = "dbconduiteproj")
public class AppUser {
    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appuser_seq")
    //@SequenceGenerator(name = "appuser_seq", sequenceName = "appuser_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.IDENTITY) 
    //@Column(name = "user_id")
    //private int id;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
	private long id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "password")
    private String password;

    public AppUser() {}

    public AppUser(String name, String email , String role, String password) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
    }

public Long getId() {
    return this.id;
}

// Getter and setter for name

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

// Getter and setter for email
public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

// Getter and setter for password
public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

// Getter and setter for role
public String getRole() {
    return role;
}

public void setRole(String role) {
    this.role = role;


}

}
