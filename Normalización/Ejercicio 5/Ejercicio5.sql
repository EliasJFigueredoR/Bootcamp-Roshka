/* 
 * Prestamo de libros
 */

--Crear estructura--


CREATE SEQUENCE ejercicio5.aula_id_aula_seq;

CREATE TABLE ejercicio5.Aula (
                id_aula NUMERIC NOT NULL DEFAULT nextval('ejercicio5.aula_id_aula_seq'),
                nombre VARCHAR NOT NULL,
                CONSTRAINT aula_pk PRIMARY KEY (id_aula)
);


ALTER SEQUENCE ejercicio5.aula_id_aula_seq OWNED BY ejercicio5.Aula.id_aula;

CREATE SEQUENCE ejercicio5.editorial_id_editorial_seq;

CREATE TABLE ejercicio5.Editorial (
                id_editorial NUMERIC NOT NULL DEFAULT nextval('ejercicio5.editorial_id_editorial_seq'),
                nombre VARCHAR NOT NULL,
                CONSTRAINT editorial_pk PRIMARY KEY (id_editorial)
);


ALTER SEQUENCE ejercicio5.editorial_id_editorial_seq OWNED BY ejercicio5.Editorial.id_editorial;

CREATE SEQUENCE ejercicio5.libro_id_libro_seq;

CREATE TABLE ejercicio5.Libro (
                id_libro NUMERIC NOT NULL DEFAULT nextval('ejercicio5.libro_id_libro_seq'),
                nombre VARCHAR NOT NULL,
                CONSTRAINT libro_pk PRIMARY KEY (id_libro)
);


ALTER SEQUENCE ejercicio5.libro_id_libro_seq OWNED BY ejercicio5.Libro.id_libro;

CREATE SEQUENCE ejercicio5.ejemplar_id_ejemplar_seq;

CREATE TABLE ejercicio5.Ejemplar (
                id_ejemplar NUMERIC NOT NULL DEFAULT nextval('ejercicio5.ejemplar_id_ejemplar_seq'),
                id_editorial NUMERIC NOT NULL,
                id_libro NUMERIC NOT NULL,
                CONSTRAINT ejemplar_pk PRIMARY KEY (id_ejemplar)
);


ALTER SEQUENCE ejercicio5.ejemplar_id_ejemplar_seq OWNED BY ejercicio5.Ejemplar.id_ejemplar;

CREATE SEQUENCE ejercicio5.curso_id_curso_seq;

CREATE TABLE ejercicio5.Curso (
                id_curso NUMERIC NOT NULL DEFAULT nextval('ejercicio5.curso_id_curso_seq'),
                nombre VARCHAR NOT NULL,
                CONSTRAINT curso_pk PRIMARY KEY (id_curso)
);


ALTER SEQUENCE ejercicio5.curso_id_curso_seq OWNED BY ejercicio5.Curso.id_curso;

CREATE SEQUENCE ejercicio5.asignatura_habilidad_id_asignatura_seq;

CREATE TABLE ejercicio5.Asignatura_Habilidad (
                id_asignatura NUMERIC NOT NULL DEFAULT nextval('ejercicio5.asignatura_habilidad_id_asignatura_seq'),
                nombre VARCHAR NOT NULL,
                CONSTRAINT asignatura_habilidad_pk PRIMARY KEY (id_asignatura)
);


ALTER SEQUENCE ejercicio5.asignatura_habilidad_id_asignatura_seq OWNED BY ejercicio5.Asignatura_Habilidad.id_asignatura;

CREATE SEQUENCE ejercicio5.profesor_id_profesor_seq;

CREATE TABLE ejercicio5.Profesor (
                id_profesor NUMERIC NOT NULL DEFAULT nextval('ejercicio5.profesor_id_profesor_seq'),
                nombre VARCHAR NOT NULL,
                CONSTRAINT profesor_pk PRIMARY KEY (id_profesor)
);


ALTER SEQUENCE ejercicio5.profesor_id_profesor_seq OWNED BY ejercicio5.Profesor.id_profesor;

CREATE SEQUENCE ejercicio5.colegio_id_colegio_seq;

CREATE TABLE ejercicio5.Colegio (
                id_colegio NUMERIC NOT NULL DEFAULT nextval('ejercicio5.colegio_id_colegio_seq'),
                nombre VARCHAR NOT NULL,
                CONSTRAINT colegio_pk PRIMARY KEY (id_colegio)
);


ALTER SEQUENCE ejercicio5.colegio_id_colegio_seq OWNED BY ejercicio5.Colegio.id_colegio;

CREATE TABLE ejercicio5.Asignatura_Profeso_Colegio (
                id_asignatura NUMERIC NOT NULL,
                id_colegio NUMERIC NOT NULL,
                id_profesor NUMERIC NOT NULL,
                CONSTRAINT asignatura_profeso_colegio_pk PRIMARY KEY (id_asignatura, id_colegio, id_profesor)
);


CREATE TABLE ejercicio5.Aula_colegio_curso (
                id_aula NUMERIC NOT NULL,
                id_colegio NUMERIC NOT NULL,
                id_curso NUMERIC NOT NULL,
                CONSTRAINT aula_colegio_curso_pk PRIMARY KEY (id_aula, id_colegio, id_curso)
);


CREATE SEQUENCE ejercicio5.prestamos_libros_id_prestamos_seq;

CREATE TABLE ejercicio5.Prestamos_Libros (
                id_prestamos NUMERIC NOT NULL DEFAULT nextval('ejercicio5.prestamos_libros_id_prestamos_seq'),
                Fecha_pestamo DATE NOT NULL,
                id_profesor NUMERIC NOT NULL,
                CONSTRAINT prestamos_libros_pk PRIMARY KEY (id_prestamos)
);


ALTER SEQUENCE ejercicio5.prestamos_libros_id_prestamos_seq OWNED BY ejercicio5.Prestamos_Libros.id_prestamos;

CREATE TABLE ejercicio5.Detalle_Prestamos (
                id_prestamos NUMERIC NOT NULL,
                id_ejemplar NUMERIC NOT NULL,
                CONSTRAINT detalle_prestamos_pk PRIMARY KEY (id_prestamos, id_ejemplar)
);


ALTER TABLE ejercicio5.Aula_colegio_curso ADD CONSTRAINT aula_aula_colegio_fk
FOREIGN KEY (id_aula)
REFERENCES ejercicio5.Aula (id_aula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Ejemplar ADD CONSTRAINT editorial_ejemplar_fk
FOREIGN KEY (id_editorial)
REFERENCES ejercicio5.Editorial (id_editorial)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Ejemplar ADD CONSTRAINT libro_ejemplar_fk
FOREIGN KEY (id_libro)
REFERENCES ejercicio5.Libro (id_libro)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Detalle_Prestamos ADD CONSTRAINT ejemplar_detalle_prestamos_fk
FOREIGN KEY (id_ejemplar)
REFERENCES ejercicio5.Ejemplar (id_ejemplar)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Aula_colegio_curso ADD CONSTRAINT curso_aula_colegio_fk
FOREIGN KEY (id_curso)
REFERENCES ejercicio5.Curso (id_curso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Asignatura_Profeso_Colegio ADD CONSTRAINT asignatura_habilidad_asignatura_profesor_fk
FOREIGN KEY (id_asignatura)
REFERENCES ejercicio5.Asignatura_Habilidad (id_asignatura)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Prestamos_Libros ADD CONSTRAINT profesor_prestamos_libros_fk
FOREIGN KEY (id_profesor)
REFERENCES ejercicio5.Profesor (id_profesor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Asignatura_Profeso_Colegio ADD CONSTRAINT profesor_asignatura_profesor_fk
FOREIGN KEY (id_profesor)
REFERENCES ejercicio5.Profesor (id_profesor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Aula_colegio_curso ADD CONSTRAINT colegio_aula_colegio_fk
FOREIGN KEY (id_colegio)
REFERENCES ejercicio5.Colegio (id_colegio)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Asignatura_Profeso_Colegio ADD CONSTRAINT colegio_asignatura_profesor_fk
FOREIGN KEY (id_colegio)
REFERENCES ejercicio5.Colegio (id_colegio)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Detalle_Prestamos ADD CONSTRAINT prestamos_libros_detalle_prestamos_fk
FOREIGN KEY (id_prestamos)
REFERENCES ejercicio5.Prestamos_Libros (id_prestamos)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

--Agregar datos

-- 1. Insertar Colegios
INSERT INTO ejercicio5.Colegio (nombre) VALUES 
('C.P Cervantes'),
('C.P Quevedo');

-- 2. Insertar Aulas
INSERT INTO ejercicio5.Aula (nombre) VALUES 
('1.A01'),
('1.B01'),
('2.B01');

-- 3. Insertar Cursos 
INSERT INTO ejercicio5.Curso (nombre) VALUES 
('1er Grado'),
('2do Grado');

-- 4. Insertar Editoriales
INSERT INTO ejercicio5.Editorial (nombre) VALUES 
('Graó'),
('Técnicas Rubio'),
('Prentice Hall'),
('Temas de Hoy');

-- 5. Insertar Libros
INSERT INTO ejercicio5.Libro (nombre) VALUES 
('Aprender y enseñar en educación infantil'),
('Preescolar Rubio, N56'),
('Educación Infantil N9'),
('Saber educar: guía para Padres y Profesores');

-- 6. Insertar Profesores
INSERT INTO ejercicio5.Profesor (nombre) VALUES 
('Juan Pérez'),
('Alicia García'),
('Andrés Fernández'),
('Juan Méndez');

-- 7. Insertar Asignaturas / Habilidades
INSERT INTO ejercicio5.Asignatura_Habilidad (nombre) VALUES 
('Pensamiento Lógico'),
('Escritura'),
('Pensamiento Numérico'),
('Pensamiento Espacial, Temporal y causal'),
('Ingles');

-- 8. Insertar Ejemplares 
INSERT INTO ejercicio5.Ejemplar (id_editorial, id_libro) VALUES 
(1, 1),
(2, 2), 
(3, 3), 
(4, 4); 

-- 9. Insertar Relación Aula - Colegio - Curso
INSERT INTO ejercicio5.Aula_colegio_curso (id_aula, id_colegio, id_curso) VALUES 
(1, 1, 1), 
(2, 1, 1), 
(1, 1, 2),
(3, 2, 1); 

-- 10. Insertar Relación Asignatura - Profesor - Colegio

INSERT INTO ejercicio5.Asignatura_Profeso_Colegio (id_profesor, id_colegio, id_asignatura) VALUES 
(1, 1, 1),
(1, 1, 2), 
(1, 1, 3), 

(2, 1, 4), 
(2, 1, 3),

(3, 1, 2), 
(3, 1, 5), 

(4, 2, 1), 
(4, 2, 3); 

-- 11. Insertar Préstamos 

INSERT INTO ejercicio5.Prestamos_Libros (id_prestamos, Fecha_pestamo, id_profesor) VALUES 
(1, '2010-09-09', 1),
(2, '2010-05-05', 1), 
(3, '2010-05-05', 1), 
(4, '2010-05-06', 2), 
(5, '2010-05-06', 2), 
(6, '2010-09-09', 3), 
(7, '2010-05-05', 3), 
(8, '2010-12-18', 4), 
(9, '2010-05-06', 4); 

-- 12. Insertar Detalle Préstamos 
INSERT INTO ejercicio5.Detalle_Prestamos (id_prestamos, id_ejemplar) VALUES 
(1, 1), 
(2, 2), 
(3, 1), 
(4, 3), 
(5, 1), 
(6, 1), 
(7, 4), 
(8, 4), 
(9, 1); 


--Mostrar las tablas
SELECT * FROM Libro;

SELECT * FROM Editorial;

SELECT * FROM Ejemplar;

SELECT * FROM Detalle_Prestamos;

SELECT * FROM Prestamos_Libros;

SELECT * FROM Profesor;

SELECT * FROM Asignatura_Habilidad;

SELECT * FROM Asignatura_Profeso_Colegio;

SELECT * FROM Colegio;

SELECT * FROM Aula_colegio_curso;

SELECT * FROM Curso;

SELECT * FROM Aula;




