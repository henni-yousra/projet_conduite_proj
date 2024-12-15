package com.example.conduite.controllers;

import com.example.conduite.services.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    /* @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserService userService;

    private Map<String, String> validUser;

    @BeforeEach
    void setUp() {
        validUser = new HashMap<>();
        validUser.put("name", "JC");
        validUser.put("email", "jc@example.com");
        validUser.put("role", "USER");
        validUser.put("password", "securepassword");
    } */


}
