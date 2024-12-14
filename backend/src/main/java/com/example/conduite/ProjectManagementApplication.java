package com.example.conduite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.EntityManagerFactory;

@SpringBootApplication(scanBasePackages = "com.example.conduite")
public class ProjectManagementApplication {

	@Autowired
    private EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

	// Clear Hibernate's second-level cache on startup
    @EventListener(ApplicationReadyEvent.class)
    public void clearCache() {
        if (entityManagerFactory != null) {
            entityManagerFactory.getCache().evictAll();
        } else {
            System.out.println("EntityManagerFactory is null, cache clearing skipped.");
        }
    }


}
