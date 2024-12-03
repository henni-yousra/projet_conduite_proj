-- psql -h pgsql -f create.sql 

CREATE SCHEMA IF NOT EXISTS dbConduiteProj;

CREATE SEQUENCE appuser_seq START WITH 1 INCREMENT BY 1; -- Optional, as SERIAL creates an implicit sequence
CREATE SEQUENCE project_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE dbConduiteProj.appuser(
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role varchar(10) constraint role_check check (role in ('chef', 'prog')) NOT NULL default 'prog'
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
    role VARCHAR(20) CHECK(role IN ('chef', 'prog')) NOT NULL,
    PRIMARY KEY (project_id, user_id),
    FOREIGN KEY (project_id) REFERENCES dbConduiteProj.project(proj_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES dbConduiteProj.appuser(user_id) ON DELETE CASCADE
);



-- can later write sql queries to get the name of a project from the project table, by joining on the project name
--ALTER TABLE dbConduiteProj.appuser ADD project varchar constraint fk_proj references dbConduiteProj.project(proj_id);

-- add the leader to a project
--ALTER TABLE dbConduiteProj.project ADD leader varchar constraint fk_leader references dbConduiteProj.appuser(user_id);


