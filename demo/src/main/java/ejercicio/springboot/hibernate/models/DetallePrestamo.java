package ejercicio.springboot.hibernate.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

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
    private long cantidad;

    public DetallePrestamo() {
    }

    public DetallePrestamo(PrestamosLibro idPrestamos, Libro idLibro, Editorial idEditorial, long cantidad) {
        this.idPrestamos = idPrestamos;
        this.idLibro = idLibro;
        this.idEditorial = idEditorial;
        this.cantidad = cantidad;
        this.id = new DetallePrestamoId(idPrestamos.getId(), idLibro.getId(), idEditorial.getId());
    }

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

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "\nDetallePrestamo:" + id + " " +
                " " + idPrestamos +
                " " + idLibro +
                " " + idEditorial +
                " " + cantidad;
    }
}