package ejercicio.springboot.hibernate.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "prestamos_libros", schema = "ejercicio5")
public class PrestamosLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamos", nullable = false)
    private long id;

    @Column(name = "fecha_pestamo", nullable = false)
    private LocalDate fechaPestamo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_colegio_profesor", nullable = false)
    private ColegioProfesor idColegioProfesor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso idCurso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_asignatura", nullable = false)
    private AsignaturaHabilidad idAsignatura;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_aula", nullable = false)
    private Aula idAula;

    @OneToMany(mappedBy = "idPrestamos", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DetallePrestamo> detalles;

    public PrestamosLibro() {
    }

    public PrestamosLibro(LocalDate fechaPestamo, ColegioProfesor idColegioProfesor, Curso idCurso, AsignaturaHabilidad idAsignatura, Aula idAula) {
        this.fechaPestamo = fechaPestamo;
        this.idColegioProfesor = idColegioProfesor;
        this.idCurso = idCurso;
        this.idAsignatura = idAsignatura;
        this.idAula = idAula;
    }

    @Override
    public String toString() {
        return "\nPrestamosLibro: " + id +
                " Fecha Prestamo: " + fechaPestamo +
                " " + idColegioProfesor +
                " " + idCurso +
                " " + idAsignatura +
                " " + idAula;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFechaPestamo() {
        return fechaPestamo;
    }

    public void setFechaPestamo(LocalDate fechaPestamo) {
        this.fechaPestamo = fechaPestamo;
    }

    public ColegioProfesor getIdColegioProfesor() {
        return idColegioProfesor;
    }

    public void setIdColegioProfesor(ColegioProfesor idColegioProfesor) {
        this.idColegioProfesor = idColegioProfesor;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public AsignaturaHabilidad getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(AsignaturaHabilidad idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Aula getIdAula() {
        return idAula;
    }

    public void setIdAula(Aula idAula) {
        this.idAula = idAula;
    }

    public List<DetallePrestamo> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePrestamo> detalles) {
        this.detalles = detalles;
    }
}