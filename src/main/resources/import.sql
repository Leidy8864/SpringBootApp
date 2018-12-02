/* Populate tables */
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Andres', 'Guzman', '108672', 'profesor@bolsadeideas.com', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('John', 'Doe', '108672', 'john.doe@gmail.com', '2017-08-02', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Linus', 'Torvalds', '108672', 'linus.torvalds@gmail.com','');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Jane', 'Doe', '108672', 'jane.doe@gmail.com', '2017-08-04', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Rasmus', 'Lerdorf', '108672', 'rasmus.lerdorf@gmail.com','');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Erich', 'Gamma', '108672', 'erich.gamma@gmail.com', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Richard', 'Helm', '108672', 'richard.helm@gmail.com', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Ralph', 'Johnson', '108672', 'ralph.johnson@gmail.com', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('John', 'Vlissides', '108672', 'john.vlissides@gmail.com','');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('James', 'Gosling', '108672', 'james.gosling@gmail.com','');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Bruce', 'Lee', '108672', 'bruce.lee@gmail.com', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Johnny', 'Doe', '108672', 'johnny.doe@gmail.com', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('John', 'Roe', '108672', 'john.roe@gmail.com', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Jane', 'Roe', '108672', 'jane.roe@gmail.com', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Richard', 'Doe', '108672', 'richard.doe@gmail.com', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Janie', 'Doe', '108672', 'janie.doe@gmail.com', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Phillip', 'Webb', '108672', 'phillip.webb@gmail.com','');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Stephane', 'Nicoll', '108672', 'stephane.nicoll@gmail.com','');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Sam', 'Brannen', '108672', 'sam.brannen@gmail.com', '2017-08-19', '');  
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Juergen', 'Hoeller', '108672', 'juergen.Hoeller@gmail.com', ''); 
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Janie', 'Roe', '108672', 'janie.roe@gmail.com','');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('John', 'Smith', '108672', 'john.smith@gmail.com','');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Joe', 'Bloggs', '108672', 'joe.bloggs@gmail.com', '');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('John', 'Stiles', '108672', 'john.stiles@gmail.com','');
INSERT INTO alumnos (nombre, apellido, codigo, email, foto) VALUES('Richard', 'Roe', '108672', 'stiles.roe@gmail.com','');

/* Populate tabla cursos */
INSERT INTO cursos (nombre, precio, creditos) VALUES('DISEÑO DE INTERFACES DE PROGRAMACIÓN', 650, '4');
INSERT INTO cursos (nombre, precio, creditos) VALUES('INVESTIGACIÓN DE MERCADOS', 250, '2');
INSERT INTO cursos (nombre, precio, creditos) VALUES('BASE DE DATOS AVANZADAS', 420, '4');
INSERT INTO cursos (nombre, precio, creditos) VALUES('SISTEMAS OPERATIVOS', 210, '3');
INSERT INTO cursos (nombre, precio, creditos) VALUES('MEJORA CONTINUA EN EL DISEÑO', 310, '3');
INSERT INTO cursos (nombre, precio, creditos) VALUES('DESARROLLO DE APLICACIONES EN INTERNET', 330, '4');
INSERT INTO cursos (nombre, precio, creditos) VALUES('INGENIERÍA DE REQUERIMIENTOS Y DISEÑO DE SOFTWARE', 350, '3');

/* Creamos algunas matriculas */
INSERT INTO matriculas (create_at, ciclo, anio, semestre, alumno_id) VALUES( NOW(),'1', '2018', '1', 1);
INSERT INTO matriculas_items ( matricula_id, curso_id) VALUES(1, 1);
INSERT INTO matriculas_items ( matricula_id, curso_id) VALUES(1, 4);
INSERT INTO matriculas_items ( matricula_id, curso_id) VALUES(1, 5);
INSERT INTO matriculas_items ( matricula_id, curso_id) VALUES(1, 7);

INSERT INTO matriculas (create_at, ciclo, anio, semestre, alumno_id) VALUES( NOW(),'1', '2018', '1', 2);
INSERT INTO matriculas_items ( matricula_id, curso_id) VALUES(2, 1);
INSERT INTO matriculas_items ( matricula_id, curso_id) VALUES(2, 3);
INSERT INTO matriculas_items ( matricula_id, curso_id) VALUES(2, 5);
INSERT INTO matriculas_items ( matricula_id, curso_id) VALUES(2, 7);