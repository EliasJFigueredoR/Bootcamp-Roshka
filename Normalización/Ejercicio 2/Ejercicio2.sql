
/* 
 * Ejercicio Factyra de compra Venta
 * 
 */

----- Crear las tablas -------


CREATE SEQUENCE ejercicio2.categoria_producto_id_categoria_seq;

CREATE TABLE ejercicio2.Categoria_Producto (
                ID_CATEGORIA NUMERIC NOT NULL DEFAULT nextval('ejercicio2.categoria_producto_id_categoria_seq'),
                CATEGORIA VARCHAR NOT NULL,
                CONSTRAINT categoria_producto_pk PRIMARY KEY (ID_CATEGORIA)
);


ALTER SEQUENCE ejercicio2.categoria_producto_id_categoria_seq OWNED BY ejercicio2.Categoria_Producto.ID_CATEGORIA;

CREATE SEQUENCE ejercicio2.ciudad_id_ciudad_seq;

CREATE TABLE ejercicio2.Ciudad (
                ID_CIUDAD NUMERIC NOT NULL DEFAULT nextval('ejercicio2.ciudad_id_ciudad_seq'),
                CIUDAD_CLIENTE VARCHAR NOT NULL,
                CONSTRAINT ciudad_pk PRIMARY KEY (ID_CIUDAD)
);


ALTER SEQUENCE ejercicio2.ciudad_id_ciudad_seq OWNED BY ejercicio2.Ciudad.ID_CIUDAD;

CREATE SEQUENCE ejercicio2.producto_id_prod_seq;

CREATE TABLE ejercicio2.Producto (
                ID_PROD NUMERIC NOT NULL DEFAULT nextval('ejercicio2.producto_id_prod_seq'),
                COD_PROD VARCHAR NOT NULL,
                DECRIPCION VARCHAR NOT NULL,
                VAL_UNIT NUMERIC NOT NULL,
                ID_CATEGORIA NUMERIC NOT NULL,
                CONSTRAINT producto_pk PRIMARY KEY (ID_PROD)
);


ALTER SEQUENCE ejercicio2.producto_id_prod_seq OWNED BY ejercicio2.Producto.ID_PROD;

CREATE SEQUENCE ejercicio2.cliente_id_cliente_seq;

CREATE TABLE ejercicio2.Cliente (
                ID_CLIENTE NUMERIC NOT NULL DEFAULT nextval('ejercicio2.cliente_id_cliente_seq'),
                NOM_CLIENTE VARCHAR NOT NULL,
                RIF_CLIENTE VARCHAR NOT NULL,
                CONSTRAINT cliente_pk PRIMARY KEY (ID_CLIENTE)
);


ALTER SEQUENCE ejercicio2.cliente_id_cliente_seq OWNED BY ejercicio2.Cliente.ID_CLIENTE;

CREATE SEQUENCE ejercicio2.direccion_id_direccion_seq;

CREATE TABLE ejercicio2.Direccion (
                ID_DIRECCION NUMERIC NOT NULL DEFAULT nextval('ejercicio2.direccion_id_direccion_seq'),
                DIR_CLIENTE VARCHAR NOT NULL,
                ID_CIUDAD NUMERIC NOT NULL,
                ID_CLIENTE NUMERIC NOT NULL,
                CONSTRAINT direccion_pk PRIMARY KEY (ID_DIRECCION)
);


ALTER SEQUENCE ejercicio2.direccion_id_direccion_seq OWNED BY ejercicio2.Direccion.ID_DIRECCION;

CREATE SEQUENCE ejercicio2.telefono_id_telefono_seq;

CREATE TABLE ejercicio2.Telefono (
                ID_TELEFONO NUMERIC NOT NULL DEFAULT nextval('ejercicio2.telefono_id_telefono_seq'),
                TELEF_CLIENTE NUMERIC NOT NULL,
                ID_CLIENTE NUMERIC NOT NULL,
                CONSTRAINT telefono_pk PRIMARY KEY (ID_TELEFONO)
);


ALTER SEQUENCE ejercicio2.telefono_id_telefono_seq OWNED BY ejercicio2.Telefono.ID_TELEFONO;

CREATE TABLE ejercicio2.Factura (
                NUM_FAC NUMERIC NOT NULL,
                FECHA_FAC TIMESTAMP NOT NULL,
                CANT_PROD NUMERIC NOT NULL,
                ID_CLIENTE NUMERIC NOT NULL,
                ID_PROD NUMERIC NOT NULL,
                CONSTRAINT factura_pk PRIMARY KEY (NUM_FAC)
);


ALTER TABLE ejercicio2.Producto ADD CONSTRAINT categoria_producto_producto_fk
FOREIGN KEY (ID_CATEGORIA)
REFERENCES ejercicio2.Categoria_Producto (ID_CATEGORIA)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio2.Direccion ADD CONSTRAINT ciudad_direccion_fk
FOREIGN KEY (ID_CIUDAD)
REFERENCES ejercicio2.Ciudad (ID_CIUDAD)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio2.Factura ADD CONSTRAINT producto_factura_fk
FOREIGN KEY (ID_PROD)
REFERENCES ejercicio2.Producto (ID_PROD)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio2.Factura ADD CONSTRAINT cliente_factura_fk
FOREIGN KEY (ID_CLIENTE)
REFERENCES ejercicio2.Cliente (ID_CLIENTE)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio2.Telefono ADD CONSTRAINT cliente_telefono_fk
FOREIGN KEY (ID_CLIENTE)
REFERENCES ejercicio2.Cliente (ID_CLIENTE)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ejercicio2.Direccion ADD CONSTRAINT cliente_direccion_fk
FOREIGN KEY (ID_CLIENTE)
REFERENCES ejercicio2.Cliente (ID_CLIENTE)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;



---Cargar los datos---

--1. Categorias 
INSERT INTO Categoria_Producto (CATEGORIA) VALUES ('Electrónica');
INSERT INTO Categoria_Producto (CATEGORIA) VALUES ('Hogar');
INSERT INTO Categoria_Producto (CATEGORIA) VALUES ('Ropa y Calzado');

--2. Ciudades 
INSERT INTO Ciudad (CIUDAD_CLIENTE) VALUES ('Asunción');
INSERT INTO Ciudad (CIUDAD_CLIENTE) VALUES ('Ciudad del Este');
INSERT INTO Ciudad (CIUDAD_CLIENTE) VALUES ('Encarnación');


--3. Productos 
INSERT INTO Producto (COD_PROD, DECRIPCION, VAL_UNIT, ID_CATEGORIA) 
VALUES ('LPT-001', 'Laptop Gamer 16GB RAM', 1200.50, 1);
INSERT INTO Producto (COD_PROD, DECRIPCION, VAL_UNIT, ID_CATEGORIA) 
VALUES ('SOF-002', 'Sofá de 3 cuerpos', 450.00, 2);
INSERT INTO Producto (COD_PROD, DECRIPCION, VAL_UNIT, ID_CATEGORIA) 
VALUES ('ZAP-003', 'Zapatillas deportivas', 85.90, 3);


--4. Insertar Clientes
INSERT INTO Cliente (NOM_CLIENTE, RIF_CLIENTE) 
VALUES ('Juan Pérez', 'V-12345678');
INSERT INTO Cliente (NOM_CLIENTE, RIF_CLIENTE) 
VALUES ('María Gómez', 'V-87654321');
INSERT INTO Cliente (NOM_CLIENTE, RIF_CLIENTE) 
VALUES ('Tecno Solutions SA', 'J-99999999');



-- 5. Insertar Facturas   
INSERT INTO Factura (NUM_FAC, FECHA_FAC, CANT_PROD, ID_CLIENTE, ID_PROD) 
VALUES (1001, '2023-10-01 10:30:00', 1, 1, 1);
INSERT INTO Factura (NUM_FAC, FECHA_FAC, CANT_PROD, ID_CLIENTE, ID_PROD) 
VALUES (1002, '2023-10-02 14:15:00', 2, 2, 3);
INSERT INTO Factura (NUM_FAC, FECHA_FAC, CANT_PROD, ID_CLIENTE, ID_PROD) 
VALUES (1003, '2023-10-05 09:00:00', 5, 3, 2);


-- 6. Telefonos
INSERT INTO Telefono (TELEF_CLIENTE,ID_CLIENTE) 
VALUES (0971553023, 1);
INSERT INTO Telefono (TELEF_CLIENTE,ID_CLIENTE) 
VALUES (0971543043, 2);
INSERT INTO Telefono (TELEF_CLIENTE,ID_CLIENTE) 
VALUES (0972593023, 3);


-- 7. Dirección
INSERT INTO Direccion (DIR_CLIENTE, ID_CIUDAD, ID_CLIENTE) 
VALUES ('Dir1',1,1);
INSERT INTO Direccion (DIR_CLIENTE, ID_CIUDAD, ID_CLIENTE) 
VALUES ('Dir2',1,2);
INSERT INTO Direccion (DIR_CLIENTE, ID_CIUDAD, ID_CLIENTE) 
VALUES ('Dir3',1,3);

--Mostrar los datos--

select * 
from categoria_producto cp;

select * 
from ciudad c;

select * 
from factura f;

select * 
from cliente c ;

select * 
from telefono f;

select * 
from producto p;

select * 
from direccion d;


