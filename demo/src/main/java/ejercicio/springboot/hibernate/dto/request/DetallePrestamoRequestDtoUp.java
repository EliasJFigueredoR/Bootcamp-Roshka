package ejercicio.springboot.hibernate.dto.request;

public class DetallePrestamoRequestDtoUp {

    private long cantidad;

    public DetallePrestamoRequestDtoUp(long cantidad) {
        this.cantidad = cantidad;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }
}
