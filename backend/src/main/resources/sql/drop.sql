--psql -h pgsql -f drop.sql 

DROP SCHEMA IF EXISTS dbConduiteProj CASCADE;

Drop table if exists dbConduiteProj.appuser cascade;
Drop table if exists dbConduiteProj.project cascade;
DROP SEQUENCE IF EXISTS appuser_seq;
DROP SEQUENCE IF EXISTS project_seq;