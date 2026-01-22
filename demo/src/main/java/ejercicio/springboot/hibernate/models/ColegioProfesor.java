package ejercicio.springboot.hibernate.models;

import jakarta.persistence.*;


@Entity
@Table(name = "colegio_profesor", schema = "ejercicio5")
public class ColegioProfesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colegio_profesor", nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor idProfesor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_colegio", nullable = false)
    private Colegio idColegio;

    public ColegioProfesor() {
    }

    public ColegioProfesor(Profesor idProfesor, Colegio idColegio) {
        this.idProfesor = idProfesor;
        this.idColegio = idColegio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Profesor getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesor idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Colegio getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Colegio idColegio) {
        this.idColegio = idColegio;
    }

    @Override
    public String toString() {
        return "" + idProfesor + idColegio;
    }
}