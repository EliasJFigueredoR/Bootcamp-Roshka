package com.example.demo;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_prestamos", schema = "ejercicio5")
public class DetallePrestamo {
    @EmbeddedId
    private DetallePrestamoId id;

    @MapsId("idPrestamos")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_prestamos", nullable = false)
    private PrestamosLibro idPrestamos;

    @MapsId("idLibro")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro idLibro;

    @MapsId("idEditorial")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_editorial", nullable = false)
    private Editorial idEditorial;

    @Column(name = "cantidad", nullable = false)
    private BigDecimal cantidad;

    public DetallePrestamoId getId() {
        return id;
    }

    public void setId(DetallePrestamoId id) {
        this.id = id;
    }

    public PrestamosLibro getIdPrestamos() {
        return idPrestamos;
    }

    public void setIdPrestamos(PrestamosLibro idPrestamos) {
        this.idPrestamos = idPrestamos;
    }

    public Libro getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Libro idLibro) {
        this.idLibro = idLibro;
    }

    public Editorial getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(Editorial idEditorial) {
        this.idEditorial = idEditorial;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

}