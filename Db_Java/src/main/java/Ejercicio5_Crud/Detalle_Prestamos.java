package Ejercicio5_Crud;

public class Detalle_Prestamos {
    private int id_prestamos;
    private int id_ejemplar;

    public Detalle_Prestamos(){}

    public Detalle_Prestamos(int id_prestamos, int id_ejemplar) {
        this.id_prestamos = id_prestamos;
        this.id_ejemplar = id_ejemplar;
    }

    public int getId_prestamos() {
        return id_prestamos;
    }

    public void setId_prestamos(int id_prestamos) {
        this.id_prestamos = id_prestamos;
    }

    public int getId_ejemplar() {
        return id_ejemplar;
    }

    public void setId_ejemplar(int id_ejemplar) {
        this.id_ejemplar = id_ejemplar;
    }
}
