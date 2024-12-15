package com.example.conduite.services;

import com.example.conduite.entities.AppUser;
import com.example.conduite.entities.Issue;
import com.example.conduite.entities.Project;
import com.example.conduite.repos.ProjectRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepo projectRepo;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ProjectService projectService;

    private Project project;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        project = new Project();
        project.setId(1L);
        project.setName("Test Project");
        project.setDescription("Test Description");
    }

    @Test
    void testGetAllProjects() {
        List<Project> projects = Arrays.asList(project);
        when(projectRepo.findAll()).thenReturn(projects);

        List<Project> result = projectService.getAllProjects();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(projectRepo, times(1)).findAll();
    }

    @Test
    void testGetProjectById() {
        when(projectRepo.findById(1L)).thenReturn(Optional.of(project));

        Project result = projectService.getProjectById(1L);

        assertNotNull(result);
        assertEquals("Test Project", result.getName());
        verify(projectRepo, times(1)).findById(1L);
    }

    @Test
    void testAddProjectToDatabase() {
        when(projectRepo.save(any(Project.class))).thenReturn(project);

        Project result = projectService.addProjectToDatabase("New Project", "New Description");

        assertNotNull(result);
        assertEquals("New Project", result.getName());
        assertEquals("New Description", result.getDescription());
        verify(projectRepo, times(1)).save(any(Project.class));
    }

    @Test
    void testGetMembers() {
        List<AppUser> members = Arrays.asList(new AppUser("Yli", "Yli@example.com", "MEMBER", "password"));
        project.addMembers(members);

        when(projectRepo.getReferenceById(1L)).thenReturn(project);

        ResponseEntity<Map<String, Object>> response = projectService.getMembers(1L);

        assertNotNull(response);
        assertEquals(1, ((List<?>) response.getBody().get("members")).size());
        verify(projectRepo, times(1)).getReferenceById(1L);
    }

    @Test
    void testAddMembersToProject() {
        List<AppUser> membersToAdd = Arrays.asList(new AppUser("Yli", "Yli@example.com", "MEMBER", "password"));

        when(projectRepo.getReferenceById(1L)).thenReturn(project);
        when(projectRepo.save(any(Project.class))).thenReturn(project);

        ResponseEntity<Map<String, String>> response = projectService.addMembersToProject(1L, membersToAdd);

        assertNotNull(response);
        assertEquals("Members added successfully.", response.getBody().get("message"));
        verify(projectRepo, times(1)).getReferenceById(1L);
        verify(projectRepo, times(1)).save(project);
    }

    @Test
    void testRemoveMembersFromProject() {
        List<AppUser> membersToRemove = Arrays.asList(new AppUser("Yli", "Yli@example.com", "MEMBER", "password"));
        project.addMembers(membersToRemove);

        when(projectRepo.getReferenceById(1L)).thenReturn(project);
        when(projectRepo.save(any(Project.class))).thenReturn(project);

        ResponseEntity<Map<String, String>> response = projectService.removeMembersFromProject(1L, membersToRemove);

        assertNotNull(response);
        assertEquals("Members removed successfully.", response.getBody().get("message"));
        verify(projectRepo, times(1)).getReferenceById(1L);
        verify(projectRepo, times(1)).save(project);
    }


    @Test
    void testAddIssuesToProject() {
        List<Issue> issuesToAdd = Arrays.asList(new Issue("Issue 1", "Description of Issue 1"));

        when(projectRepo.getReferenceById(1L)).thenReturn(project);
        when(projectRepo.save(any(Project.class))).thenReturn(project);

        ResponseEntity<Map<String, String>> response = projectService.addIssuesToProject(1L, issuesToAdd);

        assertNotNull(response);
        assertEquals("Issues added successfully.", response.getBody().get("message"));
        verify(projectRepo, times(1)).getReferenceById(1L);
        verify(projectRepo, times(1)).save(project);
    }

    @Test
    void testDeleteProject() {
        doNothing().when(projectRepo).deleteById(1L);

        ResponseEntity<Map<String, String>> response = projectService.deleteProject(1L);

        assertNotNull(response);
        assertEquals("Project deleted successfully.", response.getBody().get("message"));
        verify(projectRepo, times(1)).deleteById(1L);
    }

    @AfterEach
    public void testCleanup() {
        projectService.clearTable();
        verify(projectRepo, times(1)).deleteAll();
    }
}
