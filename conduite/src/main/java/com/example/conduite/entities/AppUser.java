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
@Table(name = "appuser", schema = "dbConduiteProj")
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

    /* @Column(name = "userrole")
    private String role; */

    public AppUser() {}

    public AppUser(String name, String email/* , String role */) {
        this.name = name;
        this.email = email;
        //this.role = role;
    }

    /* public int getId() {
        return id;
    } */

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    /* public String getRole() {
        return role;
    }
 */
}
