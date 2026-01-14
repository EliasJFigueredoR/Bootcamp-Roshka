
CREATE SEQUENCE ejercicio7.lector_id_lector_seq;

CREATE TABLE ejercicio7.Lector (
                id_lector NUMERIC NOT NULL DEFAULT nextval('ejercicio7.lector_id_lector_seq'),
                nombre VARCHAR NOT NULL,
                CONSTRAINT lector_pk PRIMARY KEY (id_lector)
);


ALTER SEQUENCE ejercicio7.lector_id_lector_seq OWNED BY ejercicio7.Lector.id_lector;

CREATE SEQUENCE ejercicio7.editorial_id_editorial_seq;

CREATE TABLE ejercicio7.Editorial (
                id_editorial NUMERIC NOT NULL DEFAULT nextval('ejercicio7.editorial_id_editorial_seq'),
                nombre VARCHAR NOT NULL,
                CONSTRAINT editorial_pk PRIMARY KEY (id_editorial)
);


ALTER SEQUENCE ejercicio7.editorial_id_editorial_seq OWNED BY ejercicio7.Editorial.id_editorial;

CREATE SEQUENCE ejercicio7.autor_id_autor_seq;

CREATE TABLE ejercicio7.Autor (
                id_autor NUMERIC NOT NULL DEFAULT nextval('ejercicio7.autor_id_autor_seq'),
                nombre VARCHAR NOT NULL,
                CONSTRAINT autor_pk PRIMARY KEY (id_autor)
);


ALTER SEQUENCE ejercicio7.autor_id_autor_seq OWNED BY ejercicio7.Autor.id_autor;

CREATE SEQUENCE ejercicio7.libro_id_libro_seq;

CREATE TABLE ejercicio7.Libro (
                id_libro NUMERIC NOT NULL DEFAULT nextval('ejercicio7.libro_id_libro_seq'),
                codigo_libro VARCHAR NOT NULL,
                Titulo VARCHAR NOT NULL,
                id_autor NUMERIC NOT NULL,
                CONSTRAINT libro_pk PRIMARY KEY (id_libro)
);


ALTER SEQUENCE ejercicio7.libro_id_libro_seq OWNED BY ejercicio7.Libro.id_libro;

CREATE SEQUENCE ejercicio7.ejemplar_id_ejemplar_seq;

CREATE TABLE ejercicio7.Ejemplar (
                id_ejemplar NUMERIC NOT NULL DEFAULT nextval('ejercicio7.ejemplar_id_ejemplar_seq'),
                id_editorial NUMERIC NOT NULL,
                id_libro NUMERIC NOT NULL,
                CONSTRAINT ejemplar_pk PRIMARY KEY (id_ejemplar)
);


ALTER SEQUENCE ejercicio7.ejemplar_id_ejemplar_seq OWNED BY ejercicio7.Ejemplar.id_ejemplar;

CREATE SEQUENCE ejercicio7.prestamos_libros_id_prestamo_seq;

CREATE SEQUENCE ejercicio7.prestamos_libros_fecha_dev_seq;

CREATE TABLE ejercicio7.Prestamos_Libros (
                id_prestamo NUMERIC NOT NULL DEFAULT nextval('ejercicio7.prestamos_libros_id_prestamo_seq'),
                id_lector NUMERIC NOT NULL,
                fecha_dev DATE NOT NULL DEFAULT nextval('ejercicio7.prestamos_libros_fecha_dev_seq'),
                CONSTRAINT prestamos_libros_pk PRIMARY KEY (id_prestamo)
);


ALTER SEQUENCE ejercicio7.prestamos_libros_id_prestamo_seq OWNED BY ejercicio7.Prestamos_Libros.id_prestamo;

ALTER SEQUENCE ejercicio7.prestamos_libros_fecha_dev_seq OWNED BY ejercicio7.Prestamos_Libros.fecha_dev;

CREATE TABLE ejercicio7.Detalle_Prestamo (
                id_ejemplar NUMERIC NOT NULL,
                id_prestamo NUMERIC NOT NULL,
                CONSTRAINT detalle_prestamo_pk PRIMARY KEY (id_ejemplar, id_prestamo)
);


ALTER TABLE ejercicio7.Prestamos_Libros ADD CONSTRAINT lector_prestamos_libros_fk
FOREIGN KEY (id_lector)
REFERENCES ejercicio7.Lector (id_lector)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio7.Ejemplar ADD CONSTRAINT editorial_ejemplar_fk
FOREIGN KEY (id_editorial)
REFERENCES ejercicio7.Editorial (id_editorial)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio7.Libro ADD CONSTRAINT autor_libro_fk
FOREIGN KEY (id_autor)
REFERENCES ejercicio7.Autor (id_autor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio7.Ejemplar ADD CONSTRAINT libro_ejemplar_fk
FOREIGN KEY (id_libro)
REFERENCES ejercicio7.Libro (id_libro)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio7.Detalle_Prestamo ADD CONSTRAINT ejemplar_detalle_prestamo_fk
FOREIGN KEY (id_ejemplar)
REFERENCES ejercicio7.Ejemplar (id_ejemplar)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio7.Detalle_Prestamo ADD CONSTRAINT prestamos_libros_detalle_prestamo_fk
FOREIGN KEY (id_prestamo)
REFERENCES ejercicio7.Prestamos_Libros (id_prestamo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;