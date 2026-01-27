package ejercicio.springboot.hibernate.dto.request;

public class DetallePrestamoRequestDto {

    private long idLibro;

    private long idEditorial;

    private long cantidad;

    public DetallePrestamoRequestDto(long idLibro,
                                     long idEditorial,
                                     long cantidad) {
        this.idLibro = idLibro;
        this.idEditorial = idEditorial;
        this.cantidad = cantidad;
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

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }
}
