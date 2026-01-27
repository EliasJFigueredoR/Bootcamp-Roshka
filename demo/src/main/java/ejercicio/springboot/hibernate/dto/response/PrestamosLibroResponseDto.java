package ejercicio.springboot.hibernate.dto.response;

import java.time.LocalDate;
import java.util.List;

public class PrestamosLibroResponseDto {

    private long id;
    private LocalDate fechaPestamo;

    private long idColegioProfesor;

    private long idColegio;
    private final String nombreColegio;

    private long idProfesor;
    private final String nombreProfesor;

    private long idCurso;
    private final String nombreCurso;

    private long idAsignatura;
    private final String nombreAsignatura;

    private long  idAula;
    private final String nombreAula;

    private List<DetallePrestamoResponseDto> detalles;

    public PrestamosLibroResponseDto(long id,
                                     LocalDate fechaPestamo,
                                     long idColegioProfesor,
                                     long idColegio, String nombreColegio,
                                     long idProfesor, String nombreProfesor,
                                     long idCurso, String nombreCurso,
                                     long idAsignatura,
                                     String nombreAsignatura,
                                     long idAula,
                                     String nombreAula,
                                     List<DetallePrestamoResponseDto> detalles) {
        this.id = id;
        this.fechaPestamo = fechaPestamo;
        this.idColegioProfesor = idColegioProfesor;
        this.idColegio = idColegio;
        this.nombreColegio = nombreColegio;
        this.idProfesor = idProfesor;
        this.nombreProfesor = nombreProfesor;
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.idAula = idAula;
        this.nombreAula = nombreAula;
        this.detalles = detalles;
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

    public long getIdColegioProfesor() {
        return idColegioProfesor;
    }

    public void setIdColegioProfesor(long idColegioProfesor) {
        this.idColegioProfesor = idColegioProfesor;
    }

    public long getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(long idColegio) {
        this.idColegio = idColegio;
    }

    public String getNombreColegio() {
        return nombreColegio;
    }

    public long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public long getIdAula() {
        return idAula;
    }

    public void setIdAula(long string) {
        this.idAula = string;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public List<DetallePrestamoResponseDto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePrestamoResponseDto> detalles) {
        this.detalles = detalles;
    }
}
