--INSERT INTO project (id, projname, projdesc) VALUES (1, 'Panda', 'A pandemonium');
--INSERT INTO project (id, projname, projdesc) VALUES (2, 'Penguin', 'A penguin colony');
--INSERT INTO project (id, projname, projdesc) VALUES (3, 'Polar Bear', 'A polar bear family');

--INSERT INTO dbConduiteProj.project (projname, projdesc) VALUES ('Panda', 'A pandemonium');
--INSERT INTO dbConduiteProj.project (projname, projdesc) VALUES ('Penguin', 'A penguin colony');
--INSERT INTO dbConduiteProj.project (projname, projdesc) VALUES ('Polar Bear', 'A polar bear family');

--insert into dbConduiteProj.appuser(id,email,name,userrole) values (1, 'doktor@gmail.com','Doktor','chef');
--select * from dbConduiteProj.appuser

insert into dbConduiteProj.appuser(id,name,email,userrole) values (1, 'Doktor', 'doktor@gmail.com','chef');
