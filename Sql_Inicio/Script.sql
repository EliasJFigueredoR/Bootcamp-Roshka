
/* 
 * 1. Top clientes con más facturas
 */

select c.id, c.nombre, c.apellido, COUNT(f.id) as "Catidad_Facturas"
from cliente c 
join factura f on c.id= f.cliente_id
group by c.id, c.nombre, c.apellido
order by "Catidad_Facturas" Desc;

/* 
 * 2. Los clientes que más gastaron
 */

select c.nombre, c.apellido, cast(SUM(fd.cantidad * p.precio) as int) as "Gasto"
from cliente c 
join factura f on c.id= f.cliente_id
join factura_detalle fd on f.id = fd.factura_id 
join producto p on p.id = fd.producto_id 
group by c.nombre, c.apellido
order by "Gasto" Desc;


/* 
 * 3. Top monedas más utilizadas
 */

select m.nombre, COUNT(f.moneda_id ) as "Veces_Utilizada"
from moneda m  
join factura f on f.moneda_id = m.id 
group by m.nombre, m.id
order by "Veces_Utilizada" Desc;

/* 
 * 4. Top proveedor de productos
 */

select p.nombre , COUNT(p2.proveedor_id) as "Veces_Proveida"
from proveedor p
join  producto p2  on p.id = p2.proveedor_id 
group by p.id 
order by "Veces_Proveida" Desc;

select p.nombre , cast(SUM(fd.cantidad) as int) as "CantProductosVendidos"
from proveedor p
join  producto p2  on p.id = p2.proveedor_id
join factura_detalle fd on p2.id = fd.producto_id 
group by p.id
order by "CantProductosVendidos" Desc;

select p.nombre , cast(SUM(fd.cantidad * p2.precio) as int) "Ganancias"
from proveedor p
join  producto p2  on p.id = p2.proveedor_id
join factura_detalle fd on p2.id = fd.producto_id 
group by  p.nombre, p.id
order by "Ganancias" Desc;

/*
 * 5.Productos más vendidos
 */
select p.nombre , cast(SUM(fd.cantidad) as int) as "Cant_vendida"
from producto p
join factura_detalle fd on fd.producto_id = p.id 
group by p.nombre, p.id
order by "Cant_vendida" Desc;	

/*
 * 6.Productos menos vendidos
 */
select p.nombre , cast(SUM(fd.cantidad) as int) as "Cant_vendida"
from producto p
join factura_detalle fd on fd.producto_id = p.id 
group by p.nombre, p.id
order by "Cant_vendida" ASC;

/*
 * 7. Consulta que muestre fecha de emisión de factura, nombre y apellido del cliente, 
 * nombres de productos de esa factura, cantidades compradas, 
 * nombre de tipo de factura de una factura específica
 */

select f.fecha_emision as "Fecha_Emsión",
	   c.nombre as "Nombre_Cliente",
	   c.apellido as "Apellido_Cliente",
	   p.nombre as "Nombre Producto",
	   SUM(fd.cantidad) as "Cantidad_Producto",
	   ft.nombre as "Tipo_Factura"
from cliente c 
join factura f on c.id= f.cliente_id
join factura_detalle fd on f.id = fd.factura_id 
join producto p on p.id = fd.producto_id 
join factura_tipo ft on ft.id = f.factura_tipo_id 
group by c.id, c.nombre, c.apellido, f.fecha_emision, p.nombre, ft.nombre 
order by "Cantidad_Producto" Desc;

/*
 * 8. Montos de facturas ordenadas según totales 
 */
select f.id, cast(SUM(fd.cantidad * p.precio ) as int) as "TotalFactura"
from producto p 
join factura_detalle fd on fd.producto_id = p.id 
join factura f on f.id = fd.factura_id 
group by f.id
order by "TotalFactura" Desc;

/*
 * 9. Mostrar el iva 10% de los montos totales de facturas (suponer que todos los productos tienen IVA 10%)
 */

select f.id, cast(SUM(fd.cantidad * p.precio) as int) as "TotalSinIva", SUM(fd.cantidad * p.precio)/11 as "Iva",
cast(SUM(fd.cantidad * p.precio) as int) + SUM(fd.cantidad * p.precio)*0.1 as "Total"
from producto p 
join factura_detalle fd on fd.producto_id = p.id 
join factura f on f.id = fd.factura_id 
group by f.id
order by "TotalFactura" Desc;

