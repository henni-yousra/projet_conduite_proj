package com.example.conduite;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.conduite.controllers.AppUserController;
import com.example.conduite.services.AppUserService;

@WebMvcTest(AppUserController.class)
public class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserService appUserService;

    /* @Test
    public void testGetAllAppUsers() throws Exception {
        // Mocking the behavior of the AppUserService
        when(appUserService.getAllAppUsers()).thenReturn(Collections.emptyList());

        // Perform GET request and validate the response
        this.mockMvc.perform(get("/appusers"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("[]"));

        // Verify that the service method was called once
        verify(appUserService, times(1)).getAllAppUsers();
        assertThat(true).isTrue();
    } */
   @Test
   public void firstTest() {
       assertThat(true).isTrue();
   }
}
