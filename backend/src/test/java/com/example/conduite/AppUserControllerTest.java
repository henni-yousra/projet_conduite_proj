package com.example.conduite;

import com.example.conduite.controllers.AppUserController;
import com.example.conduite.entities.AppUser;
import com.example.conduite.repos.AppUserRepo;
import com.example.conduite.services.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private AppUserController appUserController;

    @Test
    public void testGetAllAppUsers() throws Exception {
        mockMvc.perform(get("/appusers"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddAppUser() throws Exception {
        AppUser appUser = new AppUser("TestUser", "test@example.com", "SCRUM_MASTER", "password");
        when(appUserService.addAppUser(any(), any(), any(), any())).thenReturn(appUser);

        mockMvc.perform(post("/appusers/addUser")
                        .param("name", "TestUser")
                        .param("email", "test@example.com")
                        .param("role", "SCRUM_MASTER")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TestUser"));
    }
}
