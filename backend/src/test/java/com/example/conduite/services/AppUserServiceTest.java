package com.example.conduite.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.conduite.entities.AppUser;
import com.example.conduite.repos.AppUserRepo;


public class AppUserServiceTest {

    @Mock
    private AppUserRepo appUserRepo;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AppUserService appUserService;

    private AppUser user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        // Initialize a test example for the user tests
        user = new AppUser();
        user.setId(1L);
        user.setName("TestUser");
        user.setEmail("user@email");
        user.setPassword("password");
        user.setRole("MEMBER");
        appUserService.setTemplate(jdbcTemplate);
    }

    @Test
    public void testaddAppUser() {
        when(appUserRepo.save(any(AppUser.class))).thenReturn(user);

        AppUser addedUser = appUserService.addAppUser("Theo", "theo@", "MEMBER", "password");

        assertNotNull(addedUser);
        assertEquals("Theo", addedUser.getName());
        assertEquals("theo@", addedUser.getEmail());
        assertEquals("MEMBER", addedUser.getRole());
        assertEquals("password", addedUser.getPassword());
        verify(appUserRepo, times(1)).save(any(AppUser.class));
    }

    @Test
    public void testemailExists() {
        when(appUserRepo.findByEmail("user@email")).thenReturn(user);

        boolean exists = appUserService.emailExists("user@email");

        assertTrue(exists);
        verify(appUserRepo, times(1)).findByEmail("user@email");
    }

    @Test
    public void testvalidateUserCredentials() {
        when(appUserRepo.findByEmail("user@email")).thenReturn(user);

        boolean valid = appUserService.validateUserCredentials("user@email", "password");

        assertTrue(valid);
        verify(appUserRepo, times(1)).findByEmail("user@email");
    }

    @Test
    public void testgetAppUserById() {
        when(appUserRepo.getReferenceById(1L)).thenReturn(user);

        AppUser foundUser = appUserService.getAppUserById(1L);

        assertNotNull(foundUser);
        assertEquals("TestUser", foundUser.getName());
        verify(appUserRepo, times(1)).getReferenceById(1L);
    }

    @AfterEach
    public void testCleanup() {
        appUserService.clearTable();
        verify(appUserRepo, times(1)).deleteAll();
    }

}
