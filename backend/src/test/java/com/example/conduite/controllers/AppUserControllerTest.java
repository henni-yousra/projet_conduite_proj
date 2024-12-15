package com.example.conduite.controllers;

import com.example.conduite.entities.AppUser;
import com.example.conduite.services.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class AppUserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private AppUserController appUserController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appUserController).build();
    }

    @Test
    public void testGetAllAppUsers() throws Exception {
        AppUser user = new AppUser("JC", "jc@example.com", "MEMBER", "password");
        when(appUserService.getAllAppUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/appusers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("JC"))
                .andExpect(jsonPath("$[0].email").value("john@example.com"));
    }
    
}
