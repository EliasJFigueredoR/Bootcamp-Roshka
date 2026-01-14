
/* 
 * Reporte Matricula
 */

--Crear estructura--

CREATE SEQUENCE ejercicio6.seccion_id_seccion_seq;

CREATE TABLE ejercicio6.seccion (
                id_seccion NUMERIC NOT NULL DEFAULT nextval('ejercicio6.seccion_id_seccion_seq'),
                nombre VARCHAR NOT NULL,
                CONSTRAINT seccion_pk PRIMARY KEY (id_seccion)
);


ALTER SEQUENCE ejercicio6.seccion_id_seccion_seq OWNED BY ejercicio6.seccion.id_seccion;

CREATE SEQUENCE ejercicio6.oficina_id_oficina_seq;

CREATE TABLE ejercicio6.Oficina (
                id_oficina NUMERIC NOT NULL DEFAULT nextval('ejercicio6.oficina_id_oficina_seq'),
                codigo_oficina VARCHAR NOT NULL,
                CONSTRAINT oficina_pk PRIMARY KEY (id_oficina)
);


ALTER SEQUENCE ejercicio6.oficina_id_oficina_seq OWNED BY ejercicio6.Oficina.id_oficina;

CREATE SEQUENCE ejercicio6.docente_id_docente_seq;

CREATE TABLE ejercicio6.Docente (
                id_docente NUMERIC NOT NULL DEFAULT nextval('ejercicio6.docente_id_docente_seq'),
                nombre VARCHAR NOT NULL,
                id_oficina NUMERIC NOT NULL,
                CONSTRAINT docente_pk PRIMARY KEY (id_docente)
);


ALTER SEQUENCE ejercicio6.docente_id_docente_seq OWNED BY ejercicio6.Docente.id_docente;

CREATE SEQUENCE ejercicio6.curso_id_curso_seq;

CREATE TABLE ejercicio6.curso (
                id_curso NUMERIC NOT NULL DEFAULT nextval('ejercicio6.curso_id_curso_seq'),
                nombre VARCHAR NOT NULL,
                codigo_curso VARCHAR NOT NULL,
                CONSTRAINT curso_pk PRIMARY KEY (id_curso)
);


ALTER SEQUENCE ejercicio6.curso_id_curso_seq OWNED BY ejercicio6.curso.id_curso;

CREATE SEQUENCE ejercicio6.clase_id_clase_seq;

CREATE TABLE ejercicio6.Clase (
                id_clase NUMERIC NOT NULL DEFAULT nextval('ejercicio6.clase_id_clase_seq'),
                id_curso NUMERIC NOT NULL,
                id_seccion NUMERIC NOT NULL,
                id_docente NUMERIC NOT NULL,
                CONSTRAINT clase_pk PRIMARY KEY (id_clase)
);


ALTER SEQUENCE ejercicio6.clase_id_clase_seq OWNED BY ejercicio6.Clase.id_clase;

CREATE SEQUENCE ejercicio6.especialidad_id_especialidad_seq;

CREATE TABLE ejercicio6.Especialidad (
                id_especialidad NUMERIC NOT NULL DEFAULT nextval('ejercicio6.especialidad_id_especialidad_seq'),
                nombre VARCHAR NOT NULL,
                CONSTRAINT especialidad_pk PRIMARY KEY (id_especialidad)
);


ALTER SEQUENCE ejercicio6.especialidad_id_especialidad_seq OWNED BY ejercicio6.Especialidad.id_especialidad;

CREATE SEQUENCE ejercicio6.alumno_id_alumno_seq;

CREATE TABLE ejercicio6.Alumno (
                id_alumno NUMERIC NOT NULL DEFAULT nextval('ejercicio6.alumno_id_alumno_seq'),
                codigo_alumno VARCHAR NOT NULL,
                nombre VARCHAR NOT NULL,
                id_especialidad NUMERIC NOT NULL,
                CONSTRAINT alumno_pk PRIMARY KEY (id_alumno)
);


ALTER SEQUENCE ejercicio6.alumno_id_alumno_seq OWNED BY ejercicio6.Alumno.id_alumno;

CREATE SEQUENCE ejercicio6.reporte_matricula_id_reporte_seq;

CREATE TABLE ejercicio6.Reporte_Matricula (
                id_reporte NUMERIC NOT NULL DEFAULT nextval('ejercicio6.reporte_matricula_id_reporte_seq'),
                id_alumno NUMERIC NOT NULL,
                CONSTRAINT reporte_matricula_pk PRIMARY KEY (id_reporte)
);


ALTER SEQUENCE ejercicio6.reporte_matricula_id_reporte_seq OWNED BY ejercicio6.Reporte_Matricula.id_reporte;

CREATE TABLE ejercicio6.Reporte_detalle (
                id_reporte NUMERIC NOT NULL,
                id_clase NUMERIC NOT NULL,
                CONSTRAINT reporte_detalle_pk PRIMARY KEY (id_reporte, id_clase)
);


ALTER TABLE ejercicio6.Clase ADD CONSTRAINT seccion_clase_fk
FOREIGN KEY (id_seccion)
REFERENCES ejercicio6.seccion (id_seccion)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio6.Docente ADD CONSTRAINT oficina_docente_fk
FOREIGN KEY (id_oficina)
REFERENCES ejercicio6.Oficina (id_oficina)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio6.Clase ADD CONSTRAINT docente_clase_fk
FOREIGN KEY (id_docente)
REFERENCES ejercicio6.Docente (id_docente)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio6.Clase ADD CONSTRAINT curso_clase_fk
FOREIGN KEY (id_curso)
REFERENCES ejercicio6.curso (id_curso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio6.Reporte_detalle ADD CONSTRAINT clase_reporte_detalle_fk
FOREIGN KEY (id_clase)
REFERENCES ejercicio6.Clase (id_clase)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio6.Alumno ADD CONSTRAINT especialidad_alumno_fk
FOREIGN KEY (id_especialidad)
REFERENCES ejercicio6.Especialidad (id_especialidad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio6.Reporte_Matricula ADD CONSTRAINT alumno_reporte_matricula_fk
FOREIGN KEY (id_alumno)
REFERENCES ejercicio6.Alumno (id_alumno)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio6.Reporte_detalle ADD CONSTRAINT reporte_matricula_reporte_detalle_fk
FOREIGN KEY (id_reporte)
REFERENCES ejercicio6.Reporte_Matricula (id_reporte)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

--Carar datos--
-- 1. CARGA DE ESPECIALIDADES
INSERT INTO ejercicio6.Especialidad (id_especialidad, nombre) VALUES 
(1, 'Industrial'),
(2, 'Sistemas'),
(3, 'Civil'),
(4, 'Electrónica');

-- 2. CARGA DE OFICINAS
INSERT INTO ejercicio6.Oficina (id_oficina, codigo_oficina) VALUES 
(1, 'CB-214'),
(2, 'CB-110'),
(3, 'CB-120'),
(4, 'SC-220'),
(5, 'AD-101'),
(6, 'LB-304');

-- 3. CARGA DE SECCIONES
INSERT INTO ejercicio6.seccion (id_seccion, nombre) VALUES 
(1, 'U'),
(2, 'W'),
(3, 'V'),
(4, 'A'),
(5, 'B');

-- 4. CARGA DE CURSOS
INSERT INTO ejercicio6.curso (id_curso, codigo_curso, nombre) VALUES 
(1, 'MA123', 'Matemática 2'),
(2, 'QU514', 'Física Química'),
(3, 'AU521', 'Descriptiva'),
(4, 'PA714', 'Investigación 1'),
(5, 'AU511', 'Dibujo'),
(6, 'PR100', 'Algoritmos'),
(7, 'BD200', 'Base de Datos');

-- 5. CARGA DE DOCENTES (Depende de Oficina)
INSERT INTO ejercicio6.Docente (id_docente, nombre, id_oficina) VALUES 
(1, 'Carlos Arambulo', 1),  
(2, 'Petra Rondinel', 2),   
(3, 'Víctor Moncada', 3),   
(4, 'Cesar Fernadez', 4),  
(5, 'Ana Gómez', 5),        
(6, 'Juan Pérez', 6);       

-- 6. CARGA DE ALUMNOS 
INSERT INTO ejercicio6.Alumno (id_alumno, codigo_alumno, nombre, id_especialidad) VALUES 
(1, '382145A', 'Luis Zuloaga', 1), 
(2, '360247k', 'Raúl Rojas', 2),   
(3, '440123B', 'Maria Lopez', 3),  
(4, '550987C', 'Pedro Diaz', 4);   

-- 7. CARGA DE CLASES 
INSERT INTO ejercicio6.Clase (id_clase, id_curso, id_seccion, id_docente) VALUES 
-- Clases de Luis
(1, 1, 1, 1), 
(2, 2, 1, 2), 
(3, 3, 2, 3), 
-- Clases de Raúl
(4, 4, 3, 4), 
(5, 1, 3, 1), 
(6, 5, 1, 3), 
(7, 6, 4, 5),
(8, 7, 5, 6); 

-- 8. CARGA DE CABECERA DE REPORTE (Matrícula)
INSERT INTO ejercicio6.Reporte_Matricula (id_reporte, id_alumno) VALUES 
(1, 1), 
(2, 2), 
(3, 3), 
(4, 4);

-- 9. CARGA DE DETALLE DEL REPORTE
INSERT INTO ejercicio6.Reporte_detalle (id_reporte, id_clase) VALUES 
-- Detalle Luis 
(1, 1),
(1, 2),
(1, 3),
-- Detalle Raúl 
(2, 4),
(2, 5),
(2, 6),
(3, 7),
(4, 8);

SELECT setval('ejercicio6.especialidad_id_especialidad_seq', (SELECT MAX(id_especialidad) FROM ejercicio6.Especialidad));
SELECT setval('ejercicio6.oficina_id_oficina_seq', (SELECT MAX(id_oficina) FROM ejercicio6.Oficina));
SELECT setval('ejercicio6.seccion_id_seccion_seq', (SELECT MAX(id_seccion) FROM ejercicio6.seccion));
SELECT setval('ejercicio6.curso_id_curso_seq', (SELECT MAX(id_curso) FROM ejercicio6.curso));
SELECT setval('ejercicio6.docente_id_docente_seq', (SELECT MAX(id_docente) FROM ejercicio6.Docente));
SELECT setval('ejercicio6.alumno_id_alumno_seq', (SELECT MAX(id_alumno) FROM ejercicio6.Alumno));
SELECT setval('ejercicio6.clase_id_clase_seq', (SELECT MAX(id_clase) FROM ejercicio6.Clase));
SELECT setval('ejercicio6.reporte_matricula_id_reporte_seq', (SELECT MAX(id_reporte) FROM ejercicio6.Reporte_Matricula));	

SELECT * FROM ejercicio6.Especialidad;
SELECT * FROM ejercicio6.Oficina;
SELECT * FROM ejercicio6.seccion;
SELECT * FROM ejercicio6.curso;
SELECT * FROM ejercicio6.Docente;
SELECT * FROM ejercicio6.Alumno;
SELECT * FROM ejercicio6.Clase;
SELECT * FROM ejercicio6.Reporte_Matricula;
SELECT * FROM ejercicio6.Reporte_detalle;