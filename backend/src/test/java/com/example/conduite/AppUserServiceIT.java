package com.example.conduite;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.conduite.entities.AppUser;
import com.example.conduite.services.AppUserService;

import jakarta.transaction.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AppUserServiceIT {

    @Autowired
    private AppUserService appUserService;

    /* @Autowired
    public AppUserServiceIT(AppUserService appUserService) {
        this.appUserService = appUserService;
    } */

    /* @Transactional
    @Test
    public void testGetAllAppUsers() throws Exception {
        // put users in the database
        this.appUserService.addAppUser("TestU", "testu@", "MEMBER", "password");
        this.appUserService.addAppUser("SecondTestU", "stestu@", "MEMBER", "password");
        final List<AppUser> appUsers = this.appUserService.getAllAppUsers();
        assertThat(appUsers.size()).isEqualTo(2);
    } */

    /* @Test
    public void testAddAppUser() throws Exception {
        
    } */
}