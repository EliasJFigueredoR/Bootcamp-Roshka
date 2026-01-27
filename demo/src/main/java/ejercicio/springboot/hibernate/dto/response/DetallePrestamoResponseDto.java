package ejercicio.springboot.hibernate.dto.response;

public class DetallePrestamoResponseDto {

    private long idPrestamos;

    private long idLibro;

    private long idEditorial;

    private final String nombreLibro;

    private final String nombreEditorial;

    private long cantidad;

    public DetallePrestamoResponseDto(long idPrestamos,
                                      long idLibro,
                                      long idEditorial,
                                      String NombreLibro,
                                      String NombreEditorial,
                                      long cantidad) {
        this.idPrestamos = idPrestamos;
        this.idLibro = idLibro;
        this.idEditorial = idEditorial;
        this.nombreLibro = NombreLibro;
        this.nombreEditorial = NombreEditorial;
        this.cantidad = cantidad;
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

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        nombreLibro = nombreLibro;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        nombreEditorial = nombreEditorial;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }
}
