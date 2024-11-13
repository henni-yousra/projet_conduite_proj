-- psql -h pgsql -f create.sql 

CREATE SCHEMA IF NOT EXISTS dbConduiteProj;

CREATE SEQUENCE appuser_seq START WITH 1 INCREMENT BY 1; -- Optional, as SERIAL creates an implicit sequence
CREATE SEQUENCE project_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE dbConduiteProj.appuser(
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
    --userrole varchar(20) constraint role_check check (userrole in ('chef', 'prog'))
);


CREATE TABLE dbConduiteProj.project (
    proj_id SERIAL PRIMARY KEY,
    projname VARCHAR(255) NOT NULL,
    projdesc VARCHAR(255) NOT NULL
);

ALTER TABLE project ALTER COLUMN proj_id SET DATA TYPE integer USING proj_id::integer;

--ALTER TABLE appuser ALTER COLUMN appuser_id SET DATA TYPE integer USING appuser_id::integer;

ALTER TABLE dbConduiteProj.appuser ALTER COLUMN user_id SET DATA TYPE bigint;
