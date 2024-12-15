package com.example.conduite.controllers;

import com.example.conduite.entities.AppUser;
import com.example.conduite.entities.Issue;
import com.example.conduite.entities.Project;
import com.example.conduite.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    public void testGetAllProjects() throws Exception {
        Project project = new Project("Project A", "Description of Project A");
        when(projectService.getAllProjects()).thenReturn(List.of(project));

        mockMvc.perform(get("/projects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Project A"))
                .andExpect(jsonPath("$[0].description").value("Description of Project A"));
    }

    @Test
    public void testGetMembers() throws Exception {
        Map<String, Object> members = Map.of("members", List.of(new AppUser("JC", "jc@example.com", "MEMBER", "password")));
        when(projectService.getMembers(1L)).thenReturn(ResponseEntity.ok(members));

        mockMvc.perform(get("/projects/1/getMembers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.members[0].name").value("JC"))
                .andExpect(jsonPath("$.members[0].email").value("jc@example.com"));
    }

    @Test
    public void testAddMembersToProject() throws Exception {
        Map<String, String> response = Map.of("message", "Members added successfully.");
        when(projectService.addMembersToProject(eq(1L), anyList())).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(post("/projects/1/addMembers")
                .content("[{\"name\":\"JC\",\"email\":\"jc@example.com\"}]")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Members added successfully."));
    }

    @Test
    public void testRemoveMembersFromProject() throws Exception {
        Map<String, String> response = Map.of("message", "Members removed successfully.");
        when(projectService.removeMembersFromProject(eq(1L), anyList())).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(post("/projects/1/removeMembers")
                .content("[{\"name\":\"JC\",\"email\":\"jc@example.com\"}]")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Members removed successfully."));
    }

    @Test
    public void testDeleteProject() throws Exception {
        Map<String, String> response = Map.of("message", "Project deleted successfully.");
        when(projectService.deleteProject(1L)).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(delete("/projects/deleteProject/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Project deleted successfully."));
    }

    @Test
    public void testGetIssues() throws Exception {
        when(projectService.getIssues(1L)).thenReturn(ResponseEntity.ok(Map.of("issues", List.of(new Issue("Bug Fix", "High priority bug")))));

        mockMvc.perform(get("/projects/1/getIssues")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.issues[0].issue_name").value("Bug Fix"))
            .andExpect(jsonPath("$.issues[0].issue_desc").value("High priority bug"));
    }

    @Test
    public void testAddIssues() throws Exception {
        Map<String, String> response = Map.of("message", "Issues added successfully.");
        when(projectService.addIssuesToProject(eq(1L), anyList())).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(post("/projects/1/addIssues")
                .content("[{\"name\":\"Bug Fix\",\"description\":\"High priority bug\"}]")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Issues added successfully."));
    }
}
