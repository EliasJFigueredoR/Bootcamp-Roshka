package ejercicio.springboot.hibernate.models;

import jakarta.persistence.*;

@Entity
@Table(name = "profesor", schema = "ejercicio5")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor", nullable = false)
    private long id;

    @Column(name = "nombre", nullable = false, length = Integer.MAX_VALUE)
    private String nombre;

    public Profesor() {
    }

    @Override
    public String toString() {
        return "\nProfesor:\n\t-" + id + " " +  nombre;
    }

    public Profesor(String nombre) {
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