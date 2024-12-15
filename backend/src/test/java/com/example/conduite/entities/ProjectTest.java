package com.example.conduite.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectTest {

    private Project project;
    private AppUser appUser1;
    private AppUser appUser2;

    @BeforeEach
    void setUp() {
        appUser1 = new AppUser("Yli", "yli@example.com", "MEMBER", "password123");
        appUser2 = new AppUser("Theo", "theo@example.com", "user", "password123");
        project = new Project("Project A", "This is a test project.");
    }

    @Test
    void testProjectConstructor() {
        assertNotNull(project);
        assertEquals("Project A", project.getName());
        assertEquals("This is a test project.", project.getDescription());
        assertTrue(project.projectMembers().isEmpty());
    }

    @Test
    void testAddMembers() {
        project.addMembers(Arrays.asList(appUser1, appUser2));
        assertEquals(2, project.projectMembers().size());
        assertTrue(project.projectMembers().contains(appUser1));
        assertTrue(project.projectMembers().contains(appUser2));
    }

    @Test
    void testRemoveMembers() {
        project.addMembers(Arrays.asList(appUser1, appUser2));
        project.removeMembers(Arrays.asList(appUser1));
        assertEquals(1, project.projectMembers().size());
        assertFalse(project.projectMembers().contains(appUser1));
        assertTrue(project.projectMembers().contains(appUser2));
    }

    @Test
    void testAddIssues() {
        Issue issue = new Issue("Issue 1", "Description of Issue 1");
        project.addIssues(Arrays.asList(issue));
        assertEquals(1, project.getIssues().size());
        assertTrue(project.getIssues().contains(issue));
    }

    @Test
    void testRemoveIssues() {
        Issue issue = new Issue("Issue 1", "Description of Issue 1");
        project.addIssues(Arrays.asList(issue));
        project.removeIssues(Arrays.asList(issue));
        assertTrue(project.getIssues().isEmpty());
    }

    @Test
    void testSetName() {
        project.setName("Updated Project");
        assertEquals("Updated Project", project.getName());
    }

    @Test
    void testSetDescription() {
        project.setDescription("Updated project description.");
        assertEquals("Updated project description.", project.getDescription());
    }
}
