-- psql -h pgsql -f create.sql 

CREATE SCHEMA IF NOT EXISTS dbConduiteProj;

CREATE SEQUENCE appuser_seq START WITH 1 INCREMENT BY 1; -- Optional, as SERIAL creates an implicit sequence
CREATE SEQUENCE project_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE dbConduiteProj.appuser(
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    userrole varchar(20) constraint role_check check (userrole in ('SCRUM_MASTER', 'MEMBER')) NOT NULL default 'MEMBER'
);

CREATE TABLE dbConduiteProj.project (
    proj_id SERIAL PRIMARY KEY,
    projname VARCHAR(255) NOT NULL,
    projdesc VARCHAR(255) NOT NULL
);

-- Table to store the list of members for each project
CREATE TABLE dbConduiteProj.project_members_list (
    project_id INT NOT NULL,
    user_id INT NOT NULL,
    member_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (project_id) REFERENCES dbConduiteProj.project(proj_id),
    FOREIGN KEY (user_id) REFERENCES dbConduiteProj.appuser(user_id)
);


-- Create the projectmembers table to associate users with projects and define roles (including 'chef' for leader)
CREATE TABLE dbConduiteProj.projectmembers (
    project_id INT NOT NULL,
    user_id INT NOT NULL,
    role VARCHAR(20) CHECK(role IN ('SCRUM_MASTER', 'MEMBER')) NOT NULL,
    PRIMARY KEY (project_id, user_id),
    FOREIGN KEY (project_id) REFERENCES dbConduiteProj.project(proj_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES dbConduiteProj.appuser(user_id) ON DELETE CASCADE
);

CREATE TABLE dbConduiteProj.issue (
    issue_id INT NOT NULL,
    issue_name VARCHAR(255),
    issue_description VARCHAR(255)
);

-- CREATE TABLE dbConduiteProj.issues (
--     id INT NOT NULL,
--     description VARCHAR(255),
--     name VARCHAR(255),
--     project_id INT NOT NULL
-- );

CREATE TABLE dbConduiteProj.projectissues (
    project_id INT NOT NULL,
    issue_id INT NOT NULL
);

-- Tasks Table
    CREATE TABLE dbConduiteProj.tasks(
        task_id SERIAL PRIMARY KEY,
        issue_id INT NOT NULL,  
        project_id INT NOT NULL, 
        description VARCHAR(255) NOT NULL,
        dod VARCHAR(255) NOT NULL,
        state VARCHAR(20) CHECK(state IN ('to do', 'doing', 'done')) NOT NULL,
        dependency VARCHAR(255),
        FOREIGN KEY (issue_id) REFERENCES dbConduiteProj.issues(issue_id) ON DELETE CASCADE,
        FOREIGN KEY (project_id) REFERENCES dbConduiteProj.project(proj_id) ON DELETE CASCADE
   );

 	

-- can later write sql queries to get the name of a project from the project table, by joining on the project name
--ALTER TABLE dbConduiteProj.appuser ADD project varchar constraint fk_proj references dbConduiteProj.project(proj_id);

-- add the leader to a project
--ALTER TABLE dbConduiteProj.project ADD leader varchar constraint fk_leader references dbConduiteProj.appuser(user_id);


