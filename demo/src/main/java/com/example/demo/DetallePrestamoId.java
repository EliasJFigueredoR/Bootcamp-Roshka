package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetallePrestamoId implements Serializable {
    private static final long serialVersionUID = 6073051191439652022L;
    @Column(name = "id_prestamos", nullable = false)
    private long idPrestamos;

    @Column(name = "id_libro", nullable = false)
    private long idLibro;

    @Column(name = "id_editorial", nullable = false)
    private long idEditorial;

    public DetallePrestamoId() {
    }

    public DetallePrestamoId(long idPrestamos, long idLibro, long idEditorial) {
        this.idPrestamos = idPrestamos;
        this.idLibro = idLibro;
        this.idEditorial = idEditorial;
    }

    public long getIdPrestamos() {
        return idPrestamos;
    }

    public void setIdPrestamos(long idPrestamos) {
        this.idPrestamos = idPrestamos;
    }

    public long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(long idLibro) {
        this.idLibro = idLibro;
    }

    public long getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(long idEditorial) {
        this.idEditorial = idEditorial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePrestamoId entity = (DetallePrestamoId) o;
        return Objects.equals(this.idPrestamos, entity.idPrestamos) &&
                Objects.equals(this.idLibro, entity.idLibro) &&
                Objects.equals(this.idEditorial, entity.idEditorial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrestamos, idLibro, idEditorial);
    }
}