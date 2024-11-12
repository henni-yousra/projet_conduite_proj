package com.example.conduite.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "appuser")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appuser_seq")
    @SequenceGenerator(name = "appuser_seq", sequenceName = "appuser_seq", allocationSize = 1)
    private int id;

    private String name;
    private String email;

    public AppUser() {}

    public AppUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
