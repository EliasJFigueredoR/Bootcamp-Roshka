package ejercicio.springboot.hibernate.models;

import jakarta.persistence.*;

@Entity
@Table(name = "editorial", schema = "ejercicio5")
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_editorial", nullable = false)
    private long id;

    @Column(name = "nombre", nullable = false, length = Integer.MAX_VALUE)
    private String nombre;

    public Editorial() {
    }

    @Override
    public String toString() {
        return "\nEditorial:\n\t-" + id + " " +  nombre;
    }

    public Editorial(String nombre) {
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

}