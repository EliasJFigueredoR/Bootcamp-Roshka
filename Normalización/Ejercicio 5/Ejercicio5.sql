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
                cantidad NUMERIC NOT NULL,
                CONSTRAINT libro_pk PRIMARY KEY (id_libro)
);


ALTER SEQUENCE ejercicio5.libro_id_libro_seq OWNED BY ejercicio5.Libro.id_libro;

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

CREATE SEQUENCE ejercicio5.colegio_profesor_id_colegio_profesor_seq;

CREATE TABLE ejercicio5.Colegio_Profesor (
                id_colegio_profesor NUMERIC NOT NULL DEFAULT nextval('ejercicio5.colegio_profesor_id_colegio_profesor_seq'),
                id_profesor NUMERIC NOT NULL,
                id_colegio NUMERIC NOT NULL,
                CONSTRAINT colegio_profesor_pk PRIMARY KEY (id_colegio_profesor)
);


ALTER SEQUENCE ejercicio5.colegio_profesor_id_colegio_profesor_seq OWNED BY ejercicio5.Colegio_Profesor.id_colegio_profesor;

CREATE SEQUENCE ejercicio5.prestamos_libros_id_prestamos_seq;

CREATE TABLE ejercicio5.Prestamos_Libros (
                id_prestamos NUMERIC NOT NULL DEFAULT nextval('ejercicio5.prestamos_libros_id_prestamos_seq'),
                Fecha_pestamo DATE NOT NULL,
                id_colegio_profesor NUMERIC NOT NULL,
                id_curso NUMERIC NOT NULL,
                id_asignatura NUMERIC NOT NULL,
                id_aula NUMERIC NOT NULL,
                CONSTRAINT prestamos_libros_pk PRIMARY KEY (id_prestamos)
);


ALTER SEQUENCE ejercicio5.prestamos_libros_id_prestamos_seq OWNED BY ejercicio5.Prestamos_Libros.id_prestamos;

CREATE TABLE ejercicio5.Detalle_Prestamos (
                id_prestamos NUMERIC NOT NULL,
                id_libro NUMERIC NOT NULL,
                id_editorial NUMERIC NOT NULL,
                cantidad NUMERIC NOT NULL,
                CONSTRAINT detalle_prestamos_pk PRIMARY KEY (id_prestamos, id_libro, id_editorial)
);


ALTER TABLE ejercicio5.Prestamos_Libros ADD CONSTRAINT aula_prestamos_libros_fk
FOREIGN KEY (id_aula)
REFERENCES ejercicio5.Aula (id_aula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Detalle_Prestamos ADD CONSTRAINT editorial_detalle_prestamos_fk
FOREIGN KEY (id_editorial)
REFERENCES ejercicio5.Editorial (id_editorial)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Detalle_Prestamos ADD CONSTRAINT libro_detalle_prestamos_fk
FOREIGN KEY (id_libro)
REFERENCES ejercicio5.Libro (id_libro)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Prestamos_Libros ADD CONSTRAINT curso_prestamos_libros_fk
FOREIGN KEY (id_curso)
REFERENCES ejercicio5.Curso (id_curso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Prestamos_Libros ADD CONSTRAINT asignatura_habilidad_prestamos_libros_fk
FOREIGN KEY (id_asignatura)
REFERENCES ejercicio5.Asignatura_Habilidad (id_asignatura)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Colegio_Profesor ADD CONSTRAINT profesor_colegio_profesor_fk
FOREIGN KEY (id_profesor)
REFERENCES ejercicio5.Profesor (id_profesor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Colegio_Profesor ADD CONSTRAINT colegio_colegio_profesor_fk
FOREIGN KEY (id_colegio)
REFERENCES ejercicio5.Colegio (id_colegio)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio5.Prestamos_Libros ADD CONSTRAINT colegio_profesor_prestamos_libros_fk
FOREIGN KEY (id_colegio_profesor)
REFERENCES ejercicio5.Colegio_Profesor (id_colegio_profesor)
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

-- 1. INSERCIÓN DE DATOS MAESTROS 

-- Insertar Colegios
INSERT INTO ejercicio5.Colegio (nombre) VALUES 
('C.P Cervantes'),
('C.P Quevedo');

-- Insertar Aulas
INSERT INTO ejercicio5.Aula (nombre) VALUES 
('1.A01'),
('1.B01'),
('2.B01');

-- Insertar Cursos
INSERT INTO ejercicio5.Curso (nombre) VALUES 
('1er Grado'),
('2do Grado');

-- Insertar Editoriales
INSERT INTO ejercicio5.Editorial (nombre) VALUES 
('Graó'),
('Técnicas Rubio'),
('Prentice Hall'),
('Temas de Hoy');

-- Insertar Libros
INSERT INTO ejercicio5.Libro (nombre) VALUES 
('Aprender y enseñar en educación infantil'),
('Preescolar Rubio, N56'),
('Educación Infantil N9'),
('Saber educar: guía para Padres y Profesores');

-- Insertar Profesores
INSERT INTO ejercicio5.Profesor (nombre) VALUES 
('Juan Pérez'),
('Alicia García'),
('Andrés Fernández'),
('Juan Méndez');

-- Insertar Asignaturas / Habilidades
INSERT INTO ejercicio5.Asignatura_Habilidad (nombre) VALUES 
('Pensamiento Lógico'),
('Escritura'),
('Pensamiento Numérico'),
('Pensamiento Espacial, Temporal y causal'),
('Ingles');


-- 2. INSERCIÓN DE TABLAS INTERMEDIAS Y DEPENDIENTES




-- 5. CONSULTAS DE VERIFICACIÓN
=
SELECT * FROM ejercicio5.Colegio;
SELECT * FROM ejercicio5.Aula;
SELECT * FROM ejercicio5.Curso;
SELECT * FROM ejercicio5.Editorial;
SELECT * FROM ejercicio5.Libro;
SELECT * FROM ejercicio5.Profesor;





