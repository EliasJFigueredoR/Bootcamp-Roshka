package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class DetallePrestamoId implements Serializable {
    private static final long serialVersionUID = 6073051191439652022L;
    @Column(name = "id_prestamos", nullable = false)
    private BigDecimal idPrestamos;

    @Column(name = "id_libro", nullable = false)
    private BigDecimal idLibro;

    @Column(name = "id_editorial", nullable = false)
    private BigDecimal idEditorial;

    public BigDecimal getIdPrestamos() {
        return idPrestamos;
    }

    public void setIdPrestamos(BigDecimal idPrestamos) {
        this.idPrestamos = idPrestamos;
    }

    public BigDecimal getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(BigDecimal idLibro) {
        this.idLibro = idLibro;
    }

    public BigDecimal getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(BigDecimal idEditorial) {
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