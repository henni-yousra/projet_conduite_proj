package com.example.conduite.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.example.conduite.entities.AppUser;
import com.example.conduite.entities.Project;

public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AppUserController appUserController;

    @Mock
    private ProjectController projectController;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    void testShowMainDashboard() throws Exception {
        AppUser user1 = new AppUser("Jayjay", "jc@example.com", "MEMBER", "password");
        Project project1 = new Project("Test Project", "Description of the test project");

        when(appUserController.getAllAppUsers()).thenReturn(Arrays.asList(user1));
        when(projectController.getAllProjects()).thenReturn(Arrays.asList(project1));

        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("appUsers"))
            .andExpect(model().attributeExists("projects"))
            .andExpect(view().name("index"));

        verify(appUserController, times(1)).getAllAppUsers();
        verify(projectController, times(1)).getAllProjects();
    }


}
