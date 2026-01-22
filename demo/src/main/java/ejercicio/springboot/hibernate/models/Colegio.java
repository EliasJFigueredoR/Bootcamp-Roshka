package ejercicio.springboot.hibernate.models;

import jakarta.persistence.*;


@Entity
@Table(name = "colegio", schema = "ejercicio5")
public class Colegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colegio", nullable = false)
    private long id;

    @Column(name = "nombre", nullable = false, length = Integer.MAX_VALUE)
    private String nombre;

    public Colegio() {
    }

    public Colegio(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "\nColegio:\n\t-" + id + " " +  nombre;
    }
}