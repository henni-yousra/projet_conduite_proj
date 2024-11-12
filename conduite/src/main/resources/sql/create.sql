-- psql -h pgsql -f create.sql 

CREATE SCHEMA IF NOT EXISTS dbConduiteProj;

create table dbConduiteProj.appuser(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE SEQUENCE appuser_seq START WITH 1 INCREMENT BY 1; -- Optional, as SERIAL creates an implicit sequence
CREATE SEQUENCE project_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE dbConduiteProj.project (
    id SERIAL PRIMARY KEY,
    projname VARCHAR(255) NOT NULL,
    projdesc VARCHAR(255) NOT NULL
);
ALTER TABLE project ALTER COLUMN proj_id SET DATA TYPE integer USING proj_id::integer;
