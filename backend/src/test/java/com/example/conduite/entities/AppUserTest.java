package com.example.conduite.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppUserTest {

    private AppUser appUser;

    @BeforeEach
    void setUp() {
        appUser = new AppUser("Abdou", "abdou@email.com", "MEMBER", "password123");
    }

    @Test
    void testAppUserConstructor() {
        assertNotNull(appUser);
        assertEquals("Abdou", appUser.getName());
        assertEquals("abdou@email.com", appUser.getEmail());
        assertEquals("MEMBER", appUser.getRole());
        assertEquals("password123", appUser.getPassword());
    }

    @Test
    void testSetId() {
        appUser.setId(1L);
        assertEquals(1L, appUser.getId());
    }

    @Test
    void testSetName() {
        appUser.setName("Theophyl");
        assertEquals("Theophyl", appUser.getName());
    }

    @Test
    void testSetEmail() {
        appUser.setEmail("theo@example.com");
        assertEquals("theo@example.com", appUser.getEmail());
    }

    @Test
    void testSetRole() {
        appUser.setRole("user");
        assertEquals("user", appUser.getRole());
    }

    @Test
    void testSetPassword() {
        appUser.setPassword("newpassword");
        assertEquals("newpassword", appUser.getPassword());
    }
}
